package moukodoum.mathieu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

public class FenetrePrincipale extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public FenetrePrincipale() {
        setTitle("Gestion de Location");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Titre", "Auteur"}, 0);
        table = new JTable(tableModel);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnSupprimer = new JButton("Supprimer");
        JPanel panelBoutons = new JPanel();
        panelBoutons.add(btnAjouter);
        panelBoutons.add(btnSupprimer);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.SOUTH);

        btnAjouter.addActionListener(this::ajouterObjet);
        btnSupprimer.addActionListener(this::supprimerObjet);

        chargerObjets();
    }

    private void ajouterObjet(ActionEvent e) {
        JTextField titreField = new JTextField();
        JTextField auteurField = new JTextField();
        Object[] message = {
            "Titre:", titreField,
            "Auteur:", auteurField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Ajouter un Objet", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String titre = titreField.getText();
                String auteur = auteurField.getText();
                ObjetLocation objet = new ObjetLocation(titre, auteur);
                GestionFichier.ajouterObjet(objet);
                tableModel.addRow(new Object[]{titre, auteur});
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void supprimerObjet(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String titre = (String) tableModel.getValueAt(selectedRow, 0);
            try {
                GestionFichier.supprimerObjet(titre);
                tableModel.removeRow(selectedRow);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la suppression: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un objet à supprimer.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void chargerObjets() {
        try {
            List<ObjetLocation> objets = GestionFichier.lireObjets();
            for (ObjetLocation objet : objets) {
                tableModel.addRow(new Object[]{objet.getTitre(), objet.getAuteur()});
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des objets: " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FenetrePrincipale().setVisible(true));
    }
}



