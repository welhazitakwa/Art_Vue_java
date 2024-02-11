package models;

public class OffreEnchere {
    private int id;
    private float montant;
    private Date date;

    public OffreEnchere() {
    }

    public OffreEnchere(int id, float montant, Date date) {
        this.id = id;
        this.montant = montant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OffreEnchere{" +
                "id=" + id +
                ", montant=" + montant +
                ", date=" + date +
                "}\n";
    }
}
