package services.oeuvreArt;

import models.OeuvreArt;

import java.sql.SQLException;
import java.util.List;

public interface IOeuvreArt <T>{
    void AjouterOeuvreArt (T t ) throws SQLException;
    void ModifierOeuvreArt (T t ) throws SQLException;
    void SupprimerOeuvreArt (int id ) throws SQLException;
    List<T> AfficherOeuvreArt ( ) throws SQLException;
    OeuvreArt AfficherOeuvreArtById(int id) throws SQLException;
    List<OeuvreArt> getAllOeuvreArtWithCategories() throws SQLException;
}
