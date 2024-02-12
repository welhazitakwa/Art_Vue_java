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
    @Override
    public void AjouterVote(Vote vote) throws SQLException {
        String sql = "INSERT INTO `vote`(`note`)"
                +"values('"+ Vote.getNote()+ "')";

        Statement statement=connection.createStatement();

        statement.executeUpdate(sql);

    }

    @Override
    public void ModifierVote(Vote vote) throws SQLException {
        String sql="UPDATE vote SET note =? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, Vote.getNote());
            preparedStatement.setInt(2, Vote.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void SupprimerVote(int id) throws SQLException {
        String req = "DELETE FROM `vote` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

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
}
