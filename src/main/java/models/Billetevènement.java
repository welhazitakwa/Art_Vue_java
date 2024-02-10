package models;

public class Billetevènement {
    private int id_event;
    private float prix;

    public Billetevènement(int id_event, float prix) {
        this.id_event = id_event;
        this.prix = prix;
    }

    public Billetevènement(float prix) {
        this.prix = prix;
    }

    public Billetevènement() {

    }

    public  int getId_event() {
        return id_event;
    }

    public  float getPrix() {
        return prix;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "billetevènement{" +
                "id_event=" + id_event +
                ", prix=" + prix +
                '}';
    }
}
