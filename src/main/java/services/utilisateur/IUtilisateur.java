package services.utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateur<T> {

     void ajouter (T t) throws SQLException ;
     void modifier (T t) throws SQLException ;
     void supprimer (int id) throws SQLException ;
    List <T> listAll () throws SQLException ;
}
