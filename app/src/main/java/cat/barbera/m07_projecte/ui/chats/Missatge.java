package cat.barbera.m07_projecte.ui.chats;

public class Missatge {
    private String misstage;
    private String nom;
    private String hora;
    private String type_message;
    private String fotoPerfil;

    public Missatge() {

    }

    public Missatge(String misstage, String nom, String hora, String type_message, String fotoPerfil) {
        this.misstage = misstage;
        this.nom = nom;
        this.hora = hora;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
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
}
