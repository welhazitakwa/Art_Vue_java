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

//-------------------------Ajout Catégorie -----------------------------------------------

    @Override
    public void AjouterCategorie(Categorie categorie) throws SQLException {
        String sql = "insert into categorie (nomCategorie) " +
                "values('" + categorie.getNomCategorie() + "')";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
    }

    //---------------------------------Modifier categorie---------------------------------
    @Override
    public void ModifierCategorie(Categorie categorie) throws SQLException {
            String sql = "update categorie set nomCategorie = ? where idCategorie = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, categorie.getNomCategorie());
                preparedStatement.setInt(2, categorie.getIdCategorie());
                preparedStatement.executeUpdate();
            }

    }

//-----------------------------Supprimer catégorie-------------------------------------------

    @Override
    public void SupprimerCategorie(int id) throws SQLException {
        String req = "DELETE FROM `categorie` WHERE idCategorie=?";
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
                cat.setIdCategorie(rs.getInt("idCategorie"));
                cat.setNomCategorie(rs.getString("nomCategorie"));
                listeCategories.add(cat);
            }
        }
        return listeCategories;
    }

    @Override
    public Categorie getCategorieByNom(String nomCategorie) throws SQLException {
        String sql = "SELECT * FROM categorie WHERE nomCategorie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nomCategorie);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Categorie categorie = new Categorie();
                    categorie.setIdCategorie(rs.getInt("idCategorie"));
                    categorie.setNomCategorie(rs.getString("nomCategorie"));
                    return categorie;
                }
            }
        }
        return null;
    }

    @Override
    public Categorie getCategorieById(int id) throws SQLException {
        String sql = "SELECT * FROM categorie WHERE idCategorie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Categorie categorie = new Categorie();
                    categorie.setIdCategorie(rs.getInt("idCategorie"));
                    categorie.setNomCategorie(rs.getString("nomCategorie"));
                    return categorie;
                }
            }
        }
        return null; // Si aucune catégorie correspondante n'est trouvée
    }
}
