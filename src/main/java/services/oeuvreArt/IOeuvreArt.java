package services.oeuvreArt;

import models.OeuvreArt;

import java.sql.SQLException;
import java.util.List;

public interface IOeuvreArt <T>{
    void AjouterOeuvreArt (T t ) throws SQLException;
    void ModifierOeuvreArt (T t ) throws SQLException;
    void SupprimerOeuvreArt (int id ) throws SQLException;
    public List<OeuvreArt> getAllOeuvreArtByCategorie(String categorie) throws SQLException;
    public List<OeuvreArt> getAllOeuvreArtByArtistes(int idArtiste) throws SQLException;
    List<OeuvreArt> AfficherOeuvreArt() throws SQLException;
    public int nombreOeuvresArt() throws SQLException;
    public int nombreOeuvresArtParArtiste(int idArtiste) throws SQLException;
    public int nombreOeuvresArtParCategorie(String categorie) throws SQLException;
    OeuvreArt AfficherOeuvreArtById(int id) throws SQLException;



}
