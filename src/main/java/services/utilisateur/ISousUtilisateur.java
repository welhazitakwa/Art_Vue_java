package services.utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface ISousUtilisateur<T,A> {
    void ajouter (T t) throws SQLException;
    void modifier (T t, A a) throws SQLException ;
    void supprimer (int id, int id_user) throws SQLException ;
    List<T> listAll () throws SQLException ;
}
