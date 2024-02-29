package services.concours;

import java.sql.SQLException;
import java.util.List;
import models.OeuvreArt;

public interface Iconcours <T>{
    //void AjouterConcours (T t ,List<OeuvreArt> oeuvresArt) throws SQLException;
    void AjouterConcours (T t ) throws SQLException;
    void ModifierConcours (T t ) throws SQLException;
    void SupprimerConcours (int id ) throws SQLException;
    List<T> AfficherConcours ( ) throws SQLException;
}
