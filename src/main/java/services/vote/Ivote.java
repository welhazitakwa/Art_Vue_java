package services.vote;

import java.sql.SQLException;
import java.util.List;

public interface Ivote <T>{
    void AjouterVote (T t ) throws SQLException;
    void ModifierVote(T t ) throws SQLException;
    void SupprimerVote(int id ) throws SQLException;
    List<T> AfficherVote( ) throws SQLException;
}
