package cat.barbera.m07_projecte.ui.chats;

public class Missatge {
    private String misstage;
    private String urlFoto;
    private String nom;
    private String type_message;
    private String fotoPerfil;

    public Missatge() {

    }

    public Missatge(String misstage, String nom, String type_message, String fotoPerfil) {
        this.misstage = misstage;
        this.nom = nom;
        this.type_message = type_message;
        this.fotoPerfil = fotoPerfil;
    }

    public Missatge(String misstage, String urlFoto, String nom, String type_message, String fotoPerfil) {
        this.misstage = misstage;
        this.urlFoto = urlFoto;
        this.nom = nom;
        this.type_message = type_message;
        this.fotoPerfil = fotoPerfil;
    }

    public String getMisstage() {
        return misstage;
    }

    public void setMisstage(String misstage) {
        this.misstage = misstage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }
}
