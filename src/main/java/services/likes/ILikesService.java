package services.likes;
import java.sql.SQLException;
import java.util.List;

public interface ILikesService {
    void addLike(int idUtilisateur, int idOeuvreArt) throws SQLException;
    void removeLike(int idUtilisateur, int idOeuvreArt) throws SQLException;
    boolean isLiked(int idUtilisateur, int idOeuvreArt) throws SQLException;
    List<Integer> getUserLikes(int idUtilisateur) throws SQLException;
    boolean isLikedByUser(int userId, int oeuvreId) throws SQLException;
}

