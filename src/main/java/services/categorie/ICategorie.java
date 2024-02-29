package services.categorie;

import models.Categorie;

import java.sql.SQLException;
import java.util.List;

public interface ICategorie <T> {
    void AjouterCategorie (T t ) throws SQLException;
    void ModifierCategorie (T t ) throws SQLException;
    void SupprimerCategorie (int id ) throws SQLException;
    List<T> AfficherCategorie ( ) throws SQLException;
    Categorie getCategorieById(int id) throws SQLException;
    int getNombreCategories() throws SQLException;
    Categorie getCategorieByNom(String nomCategorie) throws SQLException;

}
