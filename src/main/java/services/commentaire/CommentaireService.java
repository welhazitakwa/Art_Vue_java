package services.commentaire;

import models.Commentaire;
import models.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import utils.MyDataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
                "'" + java.sql.Date.valueOf(LocalDate.now()) + "'," +
                "'" + commentaire.getClient_id() + "'," +
                "'" + commentaire.getOeuvre_id() + "')";

        Statement satatement = connection.createStatement();
        satatement.executeUpdate(req);
    }

    @Override
    public void modifier(Commentaire commentaire) throws SQLException {
        String sql= "update commentaire set commentaire = ?, date_commentaire = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, commentaire.getCommentaire());
        preparedStatement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        preparedStatement.setInt(3, commentaire.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM `commentaire` WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql) ;
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Commentaire> getCommentsByOeuvre(int id) throws SQLException {
        String sql = "SELECT * FROM commentaire WHERE oeuvre_id = "+id;
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Commentaire> list = new ArrayList<>();
        while (rs.next()) {
            Commentaire commentaire = new Commentaire();
            commentaire.setId(rs.getInt("id"));
            commentaire.setCommentaire(rs.getString("commentaire"));
            commentaire.setDate_commentaire(rs.getDate("date_commentaire"));
            commentaire.setClient_id(rs.getInt("client_id"));
            commentaire.setClient_id(rs.getInt("oeuvre_id"));

            list.add(commentaire);
        }
        return list;
    }


}
