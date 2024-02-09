package services.Commande;

import java.sql.SQLException;
import java.util.List;

public interface ICommande<C> {
    void AjouterCommande (C c) throws SQLException;
    void ModifierCommande (C c) throws SQLException;
    void SupprimerCommande (int id ) throws SQLException;
    List<C> AfficherCommande ( ) throws SQLException;

}
