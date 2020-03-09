package cat.barbera.m07_projecte.ui.chats;

import java.util.Map;

public class MissatgeEnviar extends Missatge {
    private Map hora;

    public MissatgeEnviar() {
    }

    public MissatgeEnviar(Map hora) {
        this.hora = hora;
    }

    public MissatgeEnviar(String misstage, String nom, String type_message, String fotoPerfil, Map hora) {
        super(misstage, nom, type_message, fotoPerfil);
        this.hora = hora;
    }

    public MissatgeEnviar(String misstage, String urlFoto, String nom, String type_message, String fotoPerfil, Map hora) {
        super(misstage, urlFoto, nom, type_message, fotoPerfil);
        this.hora = hora;
    }

    public Map getHora() {
        return hora;
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }

}
