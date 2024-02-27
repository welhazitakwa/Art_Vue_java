package services.Panier;

import java.sql.SQLException;
import java.util.List;

public interface Ipanier <P>{
    void AjouterPanier (P p) throws SQLException;
void ModifierPanier (P p) throws SQLException;
   void SupprimerPanier (int id ) throws SQLException;
  List<P> AfficherPanier ( ) throws SQLException;
}
