package cat.barbera.m07_projecte.ui.home;

public class Assignatura {

    private String title;
    private String info;

    private final int imageResource;

    public Assignatura(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
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
}
