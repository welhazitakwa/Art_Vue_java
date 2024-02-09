package services.categorie;
import models.Categorie;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategorieService implements ICategorie<Categorie>{

    private Connection connection;


    public CategorieService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }


    @Override
    public void AjouterCategorie(Categorie categorie) throws SQLException {
        String sql = "insert into categorie (nomCategorie) " +
                "values('" + categorie.getNomCategorie() + "')";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
    }

    @Override
    public void ModifierCategorie(Categorie categorie) throws SQLException {
            String sql = "update categorie set nomCategorie = ? where id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, categorie.getNomCategorie());
                preparedStatement.setInt(2, categorie.getIdCategorie());
                preparedStatement.executeUpdate();
            }

    }

    @Override
    public void SupprimerCategorie(int id) throws SQLException {
        String req = "DELETE FROM `categorie` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Categorie> AfficherCategorie() throws SQLException {
        String sql = "SELECT * FROM categorie";
        List<Categorie> listeCategories = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Categorie cat = new Categorie();
                cat.setIdCategorie(rs.getInt("id"));
                cat.setNomCategorie(rs.getString("nomCategorie"));
                listeCategories.add(cat);
            }
        }
        return listeCategories;
    }
}
