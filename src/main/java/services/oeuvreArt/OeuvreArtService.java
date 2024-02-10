package services.oeuvreArt;

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
        String sql = "INSERT INTO oeuvreart (image, titre, description, dateAjout, prixVente, categorie, status, artiste) "
                +"VALUES ('" + oeuvreArt.getImage() + "', '" + oeuvreArt.getTitre() + "', '" + oeuvreArt.getDescription() +
                "', '" + oeuvreArt.getDateAjout() + "', '" + oeuvreArt.getPrixVente() + "', '" + oeuvreArt.getCategorie() +
                "', '" + oeuvreArt.getStatus() + "', '" + oeuvreArt.getArtiste() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

    }


    @Override
    public void ModifierOeuvreArt(OeuvreArt oeuvreArt) throws SQLException {
            String sql= "update oeuvreart set image= ?, titre = ? ,description = ?, prixVente = ? , " +
                    "categorie = ? ,status = ?, artiste = ? where idOeuvreArt = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, oeuvreArt.getImage());
            preparedStatement.setString(2, oeuvreArt.getTitre());
            preparedStatement.setString(3, oeuvreArt.getDescription());
            preparedStatement.setFloat(4, oeuvreArt.getPrixVente());
            preparedStatement.setString(5,oeuvreArt.getCategorie());
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
            Oa.setCategorie(rs.getString("categorie"));
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
            Oa.setCategorie(rs.getString("categorie"));
            Oa.setStatus(rs.getString("status"));
            Oa.setArtiste(rs.getString("artiste"));
        }

        rs.close();
        statement.close();
        return Oa;
    }

}
