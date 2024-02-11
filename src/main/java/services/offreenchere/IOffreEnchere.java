package services.offreenchere;

import java.sql.SQLException;
import java.util.List;

public interface IOffreEnchere<O> {
    void AjouterOffreEnchere (O o ) throws SQLException;
    void ModifierOffreEnchere (O o ) throws SQLException;
    void SupprimerOffreEnchere (int id ) throws SQLException;
    List<O> AfficherOffreEnchere ( ) throws SQLException;
}
