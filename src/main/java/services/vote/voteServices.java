package services.vote;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Concours;
import models.Vote;
import java.sql.*;
import utils.MyDataBase;
public  class voteServices implements Ivote <Vote>{

    private final Connection connection;

    public voteServices()
    {
        connection = MyDataBase.getInstance().getConnection();
    }


    /*-----------------------------ajouterVote CRUD-----------------------------------------*/
    @Override
    public void AjouterVote(Vote vote) throws SQLException {
        String sql = "INSERT INTO `vote`(`note`)"
                +"values('"+ Vote.getNote()+ "')";

        Statement statement=connection.createStatement();

        statement.executeUpdate(sql);

    }
/*-----------------------------MODIFIERVote CRUD-----------------------------------------*/
    @Override
    public void ModifierVote(Vote vote) throws SQLException {
        String sql="UPDATE vote SET note =? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Vote.getNote());
            preparedStatement.setInt(2, Vote.getId());
            preparedStatement.executeUpdate();
        }
    }
/*-----------------------------SupprimerVote CRUD-----------------------------------------*/
    @Override
    public void SupprimerVote(int id) throws SQLException {
        String req = "DELETE FROM `vote` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
/*-----------------------------aafficherVote CRUD-----------------------------------------*/
    @Override
    public List<Vote> AfficherVote() throws SQLException {
        String sql = "SELECT * FROM vote";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Vote> listeVote = new ArrayList<>();
        while (rs.next()) {
            Vote v = new Vote();
            v.setId(rs.getInt("id"));
            v.setNote(rs.getInt("note"));
            listeVote.add(v);
        }

        return listeVote;
    }
    /*-----------------------------ajouterVote interface-----------------------------------------*/

    public boolean enregistrerVote(int concoursId, int userId, int note) {
        // Utilisez une requête SQL INSERT pour enregistrer le vote
        String query = "INSERT INTO vote (note, concours, user) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, note);
            statement.setInt(2, concoursId);
            statement.setInt(3, userId);


            // Exécutez la requête d'insertion
            int rowsAffected = statement.executeUpdate();

            // Si une ligne a été affectée, le vote a été enregistré avec succès
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
            return false; // En cas d'échec de l'enregistrement du vote
        }
    }
}
