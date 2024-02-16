package services.oeuvreArt;

import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import utils.MyDataBase;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class OeuvreArtService implements IOeuvreArt<OeuvreArt> {

    private Connection connection;
    public OeuvreArtService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void AjouterOeuvreArt(OeuvreArt oeuvreArt) throws SQLException {
        String sql = "INSERT INTO oeuvreart (image, titre, description, dateAjout, prixVente, id_Categorie, status, artiste) "
                +"VALUES ('" + oeuvreArt.getImage() + "', '" + oeuvreArt.getTitre() + "', '" + oeuvreArt.getDescription() +
                "', '" + oeuvreArt.getDateAjout() + "', '" + oeuvreArt.getPrixVente() + "', '" + oeuvreArt.getCategorie().getIdCategorie() +
                "', '" + oeuvreArt.getStatus() + "', '" + oeuvreArt.getArtiste() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }


    @Override
    public void ModifierOeuvreArt(OeuvreArt oeuvreArt) throws SQLException {
        // Récupérer l'ID de la catégorie à partir de l'objet Categorie
        int idCategorie = oeuvreArt.getCategorie().getIdCategorie();

        String sql = "UPDATE oeuvreart SET image = ?, titre = ?, description = ?, prixVente = ?, id_Categorie = ?, status = ?, artiste = ? WHERE idOeuvreArt = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, oeuvreArt.getImage());
        preparedStatement.setString(2, oeuvreArt.getTitre());
        preparedStatement.setString(3, oeuvreArt.getDescription());
        preparedStatement.setFloat(4, oeuvreArt.getPrixVente());
        preparedStatement.setInt(5, idCategorie);
        preparedStatement.setString(6, oeuvreArt.getStatus());
        preparedStatement.setString(7, oeuvreArt.getArtiste());
        preparedStatement.setInt(8, oeuvreArt.getId());
        preparedStatement.executeUpdate();
    }


    @Override
    public void SupprimerOeuvreArt(int id) throws SQLException {
        String sql = "DELETE FROM `oeuvreart` WHERE idOeuvreArt=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<OeuvreArt> AfficherOeuvreArt() throws SQLException {
        String sql = "select * from oeuvreart " ;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql) ;
        List <OeuvreArt> list = new ArrayList<>();
        while (rs.next()) {
            OeuvreArt Oa = new OeuvreArt();
            Oa.setId(rs.getInt("idOeuvreArt"));
            Oa.setImage(rs.getString("image"));
            Oa.setTitre(rs.getString("titre"));
            Oa.setDescription(rs.getString("description"));
            Oa.setDateAjout(rs.getDate("dateAjout"));
            Oa.setPrixVente(rs.getFloat("prixVente"));
            //Oa.setCategorie(rs.getInt("id_categorie"));
            Oa.setStatus(rs.getString("status"));
            Oa.setArtiste(rs.getString("artiste"));
            list.add(Oa) ;
        }
        return list;
    }

    @Override
    public OeuvreArt AfficherOeuvreArtById(int idOeuvreArt) throws SQLException {
        String sql = "SELECT * FROM oeuvreart WHERE idOeuvreArt = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idOeuvreArt);
        ResultSet rs = statement.executeQuery();

        OeuvreArt Oa = null;
        if (rs.next()) {
            Oa = new OeuvreArt();
            Oa.setId(rs.getInt("idOeuvreArt"));
            Oa.setImage(rs.getString("image"));
            Oa.setTitre(rs.getString("titre"));
            Oa.setDescription(rs.getString("description"));
            Oa.setDateAjout(rs.getDate("dateAjout"));
            Oa.setPrixVente(rs.getFloat("prixVente"));
            //Oa.setCategorie(rs.getString("categorie"));
            Oa.setStatus(rs.getString("status"));
            Oa.setArtiste(rs.getString("artiste"));
        }

        rs.close();
        statement.close();
        return Oa;
    }

    @Override
    public List<OeuvreArt> getAllOeuvreArt() throws SQLException {
        String sql = "SELECT O.*, C.idCategorie, C.nomCategorie " +
                "FROM oeuvreart O " +
                "JOIN categorie C ON O.id_Categorie = C.idCategorie";

        List<OeuvreArt> oeuvres = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                OeuvreArt oeuvre = new OeuvreArt();
                oeuvre.setId(rs.getInt("idOeuvreArt"));
                oeuvre.setImage(rs.getString("image"));
                oeuvre.setTitre(rs.getString("titre"));
                oeuvre.setDescription(rs.getString("description"));
                oeuvre.setDateAjout(rs.getDate("dateAjout"));
                oeuvre.setPrixVente(rs.getFloat("prixVente"));

                // Créer un objet Categorie
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(rs.getInt("idCategorie"));
                categorie.setNomCategorie(rs.getString("nomCategorie"));

                // Définir la catégorie de l'œuvre d'art
                oeuvre.setCategorie(categorie);

                oeuvre.setStatus(rs.getString("status"));
                oeuvre.setArtiste(rs.getString("artiste"));

                oeuvres.add(oeuvre);
            }
        }

        return oeuvres;
    }

    @Override
    public List<OeuvreArt> getAllOeuvreArtByCategorie(String categorie) throws SQLException {
        String sql = "SELECT O.*, C.idCategorie, C.nomCategorie " +
                "FROM oeuvreart O " +
                "JOIN categorie C ON O.id_Categorie = C.idCategorie " +
                "WHERE C.nomCategorie = ?";

        List<OeuvreArt> oeuvres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categorie);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OeuvreArt oeuvre = new OeuvreArt();
                    oeuvre.setId(rs.getInt("idOeuvreArt"));
                    oeuvre.setImage(rs.getString("image"));
                    oeuvre.setTitre(rs.getString("titre"));
                    oeuvre.setDescription(rs.getString("description"));
                    oeuvre.setDateAjout(rs.getDate("dateAjout"));
                    oeuvre.setPrixVente(rs.getFloat("prixVente"));

                    Categorie categorieObj = new Categorie();
                    categorieObj.setIdCategorie(rs.getInt("idCategorie"));
                    categorieObj.setNomCategorie(rs.getString("nomCategorie"));

                    oeuvre.setCategorie(categorieObj);
                    oeuvre.setStatus(rs.getString("status"));
                    oeuvre.setArtiste(rs.getString("artiste"));

                    oeuvres.add(oeuvre);
                }
            }
        }
        return oeuvres;
    }


    //public List<OeuvreArt> getAllOeuvreArtWithArtistes() throws SQLException {}





}
