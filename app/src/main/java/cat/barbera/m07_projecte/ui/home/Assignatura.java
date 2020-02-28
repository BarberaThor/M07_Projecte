package cat.barbera.m07_projecte.ui.home;

public class Assignatura {

    private String title;
    private String info;
    private String autor;

    private final int imageResource;

    public Assignatura(String title, String info, int imageResource, String autor) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
        this.autor = autor;
    }

    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImageResource() {

        return imageResource;
    }

    String getAutor(){
        return autor;
    }
}
