package services.likes;

import utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikesService implements ILikesService {
    private Connection connection;

    public LikesService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    // Méthode pour ajouter un like à la base de données
    public void addLike(int idUtilisateur, int idOeuvreArt) throws SQLException {
        String query = "INSERT INTO likes (idUtilisateur, idOeuvreArt, estLike) VALUES (?, ?, true)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            statement.setInt(2, idOeuvreArt);
            statement.executeUpdate();
        }
    }



    // Méthode pour supprimer un like de la base de données
    public void removeLike(int idUtilisateur, int idOeuvreArt) throws SQLException {
        String query = "DELETE FROM likes WHERE idUtilisateur = ? AND idOeuvreArt = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            statement.setInt(2, idOeuvreArt);
            statement.executeUpdate();
        }
    }

    public int countLikesForArtwork(int idOeuvre) throws SQLException {
        int totalLikes = 0;
        String query = "SELECT COUNT(*) AS total_likes FROM Likes WHERE idOeuvreArt = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idOeuvre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totalLikes = resultSet.getInt("total_likes");
                }
            }
        }

        return totalLikes;
    }

    public boolean isLikedByUser(int userId, int oeuvreId) throws SQLException {
        String query = "SELECT COUNT(*) FROM likes WHERE idUtilisateur = ? AND idOeuvreArt = ? AND estLike = true";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setInt(2, oeuvreId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }


    // Méthode pour vérifier si un utilisateur a aimé une œuvre d'art
    public boolean isLiked(int idUtilisateur, int idOeuvreArt) throws SQLException {
        String query = "SELECT COUNT(*) FROM likes WHERE idUtilisateur = ? AND idOeuvreArt = ? AND estLike = true";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            statement.setInt(2, idOeuvreArt);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    // Méthode pour récupérer tous les likes d'un utilisateur
    public List<Integer> getUserLikes(int idUtilisateur) throws SQLException {
        List<Integer> likedOeuvres = new ArrayList<>();
        String query = "SELECT idOeuvreArt FROM likes WHERE idUtilisateur = ? AND estLike = true";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUtilisateur);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idOeuvreArt = resultSet.getInt("idOeuvreArt");
                    likedOeuvres.add(idOeuvreArt);
                }
            }
        }
        return likedOeuvres;
    }
}

