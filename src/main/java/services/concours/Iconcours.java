package services.concours;

import java.sql.SQLException;
import java.util.List;

public interface Iconcours <T>{
    void AjouterConcours (T t ) throws SQLException;
    void ModifierConcours (T t ) throws SQLException;
    void SupprimerConcours (int id ) throws SQLException;
    List<T> AfficherConcours ( ) throws SQLException;
}
