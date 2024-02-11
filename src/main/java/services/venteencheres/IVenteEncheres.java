package services.venteencheres;

import java.sql.SQLException;
import java.util.List;

public interface IVenteEncheres<V> {
    void AjouterVenteEncheres (V v ) throws SQLException;
    void ModifierVenteEncheres (V v ) throws SQLException;
    void SupprimerVenteEncheres (int id ) throws SQLException;
    List<V> AfficherVenteEncheres ( ) throws SQLException;
}
