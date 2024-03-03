package services.oeuvreArt;

import models.OeuvreArt;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOeuvreArt <T>{
    void AjouterOeuvreArt (T t ) throws SQLException;
    void ModifierOeuvreArt (T t ) throws SQLException;
    void SupprimerOeuvreArt (int id ) throws SQLException;
    List<OeuvreArt> getAllOeuvreArtByCategorie(String categorie) throws SQLException;
    List<OeuvreArt> getAllOeuvreArtByArtistes(int idArtiste) throws SQLException;
    List<OeuvreArt> rechercherParArtiste(String nomArtiste) throws SQLException;
    List<OeuvreArt> getOeuvresByTitre(String titre) throws SQLException;
    List<OeuvreArt> AfficherOeuvreArt() throws SQLException;
    int nombreOeuvresArt() throws SQLException;
    int nombreOeuvresArtParArtiste(int idArtiste) throws SQLException;
    int nombreOeuvresArtParCategorie(String categorie) throws SQLException;
    OeuvreArt AfficherOeuvreArtById(int id) throws SQLException;
    Map<String, Integer> nombreOeuvresArtParToutesCategories() throws SQLException;
    List<OeuvreArt> getAllOeuvreArtByArtisteAndCategory(int idArtiste, String categorie)throws SQLException;



}
