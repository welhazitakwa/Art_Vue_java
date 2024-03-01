package services.Panier;

import models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface Ipanier <P>{
    boolean AjouterPanier (P p) throws SQLException;
void ModifierPanier (P p) throws SQLException;
   void SupprimerPanier (int id ) throws SQLException;
  List<P> AfficherPanier ( ) throws SQLException;
    public List<Utilisateur> getListeClients() throws SQLException;
}
