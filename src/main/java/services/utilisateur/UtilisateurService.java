package services.utilisateur;

import models.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utils.MyDataBase;

public class UtilisateurService implements IUtilisateur<Utilisateur>{

    private Connection connection;
    public UtilisateurService (){
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
            String req= "INSERT INTO utilisateur(nom, prenom, email, numTel, login, mdp, image, genre, dateNaissance, adresse)"
                                        +
                    "values('" + utilisateur.getNom() + "'," +
                    "'" + utilisateur.getPrenom() + "'," +
                    "'" + utilisateur.getEmail() + "'," +
                    "'" + utilisateur.getNumTel() + "'," +
                    "'" + utilisateur.getLogin() + "'," +
                    "'" + utilisateur.getMdp() + "'," +
                    "'" + utilisateur.getImage() + "'," +
                    "'" + utilisateur.getGenre() + "'," +
                    "'" + utilisateur.getDateNaissance() + "'," +
                    "'" + utilisateur.getAdresse() + "')";
        Statement satatement = connection.createStatement();
        satatement.executeUpdate(req);
    }

    @Override
    public void modifier(Utilisateur utilisateur) throws SQLException {
        int varrr = utilisateur.getId();
        String sql= "update utilisateur set nom= ?, prenom = ? ,email = ? , numTel = ?, login = ? , " +
                "mdp = ? ,image = ?, genre = ?, dateNaissance = ?, adresse = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, utilisateur.getNom());
        preparedStatement.setString(2, utilisateur.getPrenom());
        preparedStatement.setString(3, utilisateur.getEmail());
        preparedStatement.setInt(4, utilisateur.getNumTel());
        preparedStatement.setString(5, utilisateur.getLogin());
        preparedStatement.setString(6, utilisateur.getMdp());
        preparedStatement.setString(7, utilisateur.getImage());
        preparedStatement.setString(8, utilisateur.getGenre());
        preparedStatement.setDate(9, utilisateur.getDateNaissance());
        preparedStatement.setString(10, utilisateur.getAdresse());
        preparedStatement.setInt(11, utilisateur.getId());
        preparedStatement.executeUpdate();


    }

    @Override
    public void supprimer(int id) throws SQLException {
    String sql = "DELETE FROM `utilisateur` WHERE id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Utilisateur> listAll() throws SQLException {
        return null;
    }
}
