package services.Commande;
import models.Commande;
import models.Panier;
import models.panieroeuvre;
import java.sql.SQLException;
import java.util.List;

public interface ICommande {
    public void creerCommande(Commande commande, Panier panier) throws SQLException ;
    public void modifierCommande(Commande commande, boolean estExpediee) throws SQLException ;
    public void supprimerCommande(int id) throws SQLException;
}
