package services.utilisateur;

import models.Utilisateur;

import java.sql.Connection;
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

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Utilisateur> listAll() throws SQLException {
        return null;
    }
}
