package cat.barbera.m07_projecte.ui.chats;

public class ObjetoListView {
    private String nombre, mensaje, fecha;
    private String tipoUltimoMensaje;
    private boolean mensajeLeido;
    private int nrMensajesNoLeidos;
    private String txtSmallIcon;
    private int img;

    public ObjetoListView(String nombre, String mensaje, String fecha, String tipoUltimoMensaje, boolean mensajeLeido, int nrMensajesNoLeidos, String txtSmallIcon, int img) {
        this.nombre = nombre;
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.tipoUltimoMensaje = tipoUltimoMensaje;
        this.mensajeLeido = mensajeLeido;
        this.nrMensajesNoLeidos = nrMensajesNoLeidos;
        this.txtSmallIcon = txtSmallIcon;
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoUltimoMensaje() {
        return tipoUltimoMensaje;
    }

    public boolean isMensajeLeido() {
        return mensajeLeido;
    }

    public int getNrMensajesNoLeidos() {
        return nrMensajesNoLeidos;
    }

    public String getTxtSmallIcon() {
        return txtSmallIcon;
    }

    public int getImg() {
        return img;
    }
}
