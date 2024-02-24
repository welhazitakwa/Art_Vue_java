package models;
//import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public class Panier {
private int id;
    private Date dateAjout;

private  Utilisateur client;


    public Panier(){
    }
    public Panier(int id, Utilisateur client) {
        this.id = id;
        this.dateAjout = new Date();
        this.client=client;

    }
    public Panier(Utilisateur client) {

        this.dateAjout = new Date();
        this.client=client;

    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Utilisateur getClient() {
        return client;
    }

    public void setClient(Utilisateur client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id=" + id +
                ", dateAjout=" + dateAjout +
                ", client=" + client +
                '}';
    }
}
