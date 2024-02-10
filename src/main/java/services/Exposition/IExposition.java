package services.Exposition;

import java.sql.SQLException;
import java.util.List;

public interface IExposition<E> {
    void AjouterExposition (E e ) throws SQLException;
    void ModifierExposition (E e ) throws SQLException;
    void SupprimerExposition (int id ) throws SQLException;
    List<E> AfficherExposition ( ) throws SQLException;
}
