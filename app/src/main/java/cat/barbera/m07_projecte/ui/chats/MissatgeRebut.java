package cat.barbera.m07_projecte.ui.chats;

public class MissatgeRebut extends Missatge {
    private Long hora;

    public MissatgeRebut() {
    }

    public MissatgeRebut(Long hora) {
        this.hora = hora;
    }

    public MissatgeRebut(String misstage, String urlFoto, String nom, String type_message, String fotoPerfil, Long hora) {
        super(misstage, urlFoto, nom, type_message, fotoPerfil);
        this.hora = hora;
    }

    public Long getHora() {
        return hora;
    }

    public void setHora(Long hora) {
        this.hora = hora;
    }
}
