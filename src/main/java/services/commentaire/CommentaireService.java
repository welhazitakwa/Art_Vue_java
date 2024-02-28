package services.commentaire;

import models.Commentaire;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyDataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

public class CommentaireService implements ICommentaire<Commentaire>{
    private Connection connection;
    public CommentaireService (){
        connection = MyDataBase.getInstance().getConnection();
    }


    @Override
    public void ajouter(Commentaire commentaire) throws SQLException {
        String req= "INSERT INTO commentaire(commentaire, date_commentaire, client_id , oeuvre_id )" +
                "values('" + commentaire.getCommentaire()+ "'," +
                "'" + commentaire.getDate_commentaire() + "'," +
                "'" + commentaire.getClient_id() + "'," +
                "'" + commentaire.getOeuvre_id() + "')";

        Statement satatement = connection.createStatement();
        satatement.executeUpdate(req);
    }

    @Override
    public void modifier(Commentaire commentaire) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Commentaire> listAll() throws SQLException {
        return null;
    }
}
