package models;

import java.sql.Date;

public class Artiste extends Utilisateur {
    private int id ;
    private String description ;

    private String id_user;

    public Artiste(String id_user) {
              this.id_user = id_user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Artiste{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
