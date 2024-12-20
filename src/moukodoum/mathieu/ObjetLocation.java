package moukodoum.mathieu;

public class ObjetLocation {
    private String titre;
    private String auteur;

    public ObjetLocation(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    @Override
    public String toString() {
        return titre + ";" + auteur;
    }

    public static ObjetLocation fromString(String line) {
        String[] parts = line.split(";");
        return new ObjetLocation(parts[0], parts[1]);
    }
}


