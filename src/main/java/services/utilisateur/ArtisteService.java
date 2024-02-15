package services.utilisateur;

import models.Artiste;
import models.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class ArtisteService implements ISousUtilisateur <Utilisateur , Artiste> {
    private Connection connection;
    public ArtisteService (){
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {
        String req= "INSERT INTO utilisateur(nom, prenom, email,login, mdp,profil,date_inscription, etat_compte)" +

                "values('" + utilisateur.getNom() + "'," +
                "'" + utilisateur.getPrenom() + "'," +
                "'" + utilisateur.getEmail() + "'," +
                "'" + utilisateur.getLogin() + "'," +
                "'" +  BCrypt.hashpw(utilisateur.getMdp() , BCrypt.gensalt())+ "'," +
                "'" + 1 + "'," +
                "'" + java.sql.Date.valueOf(LocalDate.now()) + "'," +
                "'" + 0 + "')";
        Statement statement = connection.createStatement();
        statement.executeQuery(req) ;
        /*
        /* jbedt ekher id dakheltou fel table utilisateur
        String req2 = "SELECT id FROM utilisateur ORDER BY id DESC LIMIT 1";
        Statement statement2 = connection.createStatement();
        ResultSet rs2 = statement2.executeQuery(req2) ;
        int lastId = 0;
        while (rs2.next()) {
            lastId = rs2.getInt(1);
        }
       /*chnda5el l'id li jbedtou fel cle etrangere fel table artiste
        String req3 =  "INSERT INTO artiste(description, id_user)" +
                        /*values lezm nkamlha
                "values('" + "desc " + "'," +
                "'" + lastId + "')";
        Statement satatement3 = connection.createStatement();
        satatement3.executeUpdate(req3);
        */
    }

    @Override
    public void modifier(Utilisateur utilisateur, Artiste artiste) throws SQLException {

    }

    @Override
    public void supprimer(int id, int id_user) throws SQLException {

    }

    @Override
    public List<Utilisateur> listAll() throws SQLException {
        return null;
    }
}
