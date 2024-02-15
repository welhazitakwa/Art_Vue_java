package models;

public class Client extends Utilisateur {

    private int id;
    private String profession ;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", profession='" + profession + '\'' +
                '}';
    }
}
