package moukodoum.mathieu;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GestionFichier {
    private static final String FILE_PATH = "location.txt";

    public static void ajouterObjet(ObjetLocation objet) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(objet.toString());
            writer.newLine();
        }
    }

    public static List<ObjetLocation> lireObjets() throws IOException {
        List<ObjetLocation> objets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                objets.add(ObjetLocation.fromString(line));
            }
        }
        return objets;
    }

    public static void supprimerObjet(String titre) throws IOException {
        List<ObjetLocation> objets = lireObjets();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ObjetLocation objet : objets) {
                if (!objet.getTitre().equals(titre)) {
                    writer.write(objet.toString());
                    writer.newLine();
                }
            }
        }
    }
}
