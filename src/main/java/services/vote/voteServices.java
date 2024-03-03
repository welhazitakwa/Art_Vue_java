package services.vote;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Concours;
import models.Vote;
import java.sql.*;
import utils.MyDataBase;
public  class voteServices implements Ivote <Vote>{

    private static Connection connection;

    public voteServices()
    {
        connection = MyDataBase.getInstance().getConnection();
    }


    /*-----------------------------ajouterVote CRUD-----------------------------------------*/
    @Override
    public void AjouterVote(Vote vote) throws SQLException {
       /* String sql = "INSERT INTO `vote`(`note`)"
                +"values('"+ Vote.getNote()+ "')";

        Statement statement=connection.createStatement();

        statement.executeUpdate(sql);*/

    }
/*-----------------------------MODIFIERVote CRUD-----------------------------------------*/
    @Override
    public void ModifierVote(Vote vote) throws SQLException {
        String sql="UPDATE vote SET note =? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, vote.getNote());
            preparedStatement.setInt(2, vote.getId());
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

    public boolean enregistrerVote(int idConcours, int idOeuvre, int idUser, int note) {
        // Utilisez une requête SQL INSERT pour enregistrer le vote
        String query = "INSERT INTO `vote`( `note`, `concours`, `user`, `oeuvre`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, note);
            statement.setInt(2, idConcours);
            statement.setInt(3, idUser);
            statement.setInt(4, idOeuvre);

            // Exécutez la requête d'insertion
            int rowsAffected = statement.executeUpdate();

            // Si une ligne a été affectée, le vote a été enregistré avec succès
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
            return false; // En cas d'échec de l'enregistrement du vote
        }
    }
    /*-----------------------------------------------------------------*/
    public List<Vote> getVotesUtilisateur(int idUtilisateur) {
        List<Vote> votesUtilisateur = new ArrayList<>();

        String query = "SELECT vote.id, vote.note, concours.titre AS titreConcours, oeuvreart.titre AS titreOeuvre, oeuvreart.image AS imageOeuvre " +
                "FROM vote " +
                "JOIN concours ON vote.concours = concours.id " +
                "JOIN oeuvreart ON vote.oeuvre = oeuvreart.idOeuvreArt " +
                "WHERE vote.user = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Vote vote = new Vote();
                    vote.setId(resultSet.getInt("id"));
                    vote.setNote(resultSet.getInt("note"));
                    vote.setTitreConcours(resultSet.getString("titreConcours"));
                    vote.setTitreOeuvre(resultSet.getString("titreOeuvre"));
                    vote.setImageOeuvre(resultSet.getString("imageOeuvre"));

                    // Ajoutez d'autres attributs du vote selon votre modèle

                    votesUtilisateur.add(vote);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

        return votesUtilisateur;
    }

    /*--------------------------------------------------------*/
    public List<Vote> getVotesByUser(int userId) {
        List<Vote> votes = new ArrayList<>();

        String query = "SELECT DISTINCT vote.id, vote.note, oeuvreart.titre AS titreOeuvre, " +
                "oeuvreart.image AS imageOeuvre, concours.titre AS titreConcours " +
                "FROM vote " +
                "JOIN oeuvreart ON vote.oeuvre = oeuvreart.idOeuvreArt " +
                "JOIN oeuvre_concours ON vote.oeuvre = oeuvre_concours.id_oeuvre " +
                "JOIN concours ON oeuvre_concours.id_concours = concours.id " +
                "WHERE vote.user = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int voteId = resultSet.getInt("id");
                    int note = resultSet.getInt("note");
                    String titreOeuvre = resultSet.getString("titreOeuvre");
                    String imageOeuvre = resultSet.getString("imageOeuvre");
                    String titreConcours = resultSet.getString("titreConcours");

                    Vote vote = new Vote(voteId, note, titreOeuvre, imageOeuvre, titreConcours);
                    votes.add(vote);
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return votes;
    }
    /*--------------------------------------------------*/
    public boolean deleteVote(int voteId) throws SQLException {
        String query = "DELETE FROM vote WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, voteId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Gérez les exceptions de manière appropriée dans votre application
            throw new SQLException("Erreur lors de la suppression du vote avec l'ID " + voteId, e);
        }
    }


    public boolean editVote(int voteId, int newNote) {
        String query = "UPDATE vote SET note = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newNote);
            statement.setInt(2, voteId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
            return false;
        }
    }
    /*________________________________________*/
    public double getNoteMoyenneOeuvre(int oeuvreId, int concoursId) {
        String query = "SELECT note FROM vote WHERE oeuvre = ? AND concours = ?";
        int totalVotes = 0;
        int sumOfVotes = 0;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, oeuvreId);
            statement.setInt(2, concoursId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int note = resultSet.getInt("note");
                    sumOfVotes += note;
                    totalVotes++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

        // Assurez-vous de ne pas diviser par zéro
        if (totalVotes > 0) {
            return (double) sumOfVotes / totalVotes;
        } else {
            return 0.0; // Ou une autre valeur par défaut
        }
    }
}
