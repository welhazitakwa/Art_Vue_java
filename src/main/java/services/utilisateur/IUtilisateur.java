package services.utilisateur;

import models.Utilisateur;

import java.sql.SQLException;
import java.util.List;

public interface IUtilisateur<T> {

     void ajouter (T t) throws SQLException ;
     void modifier (T t) throws SQLException ;
     void supprimer (int id) throws SQLException ;
    List <T> listAll () throws SQLException ;
    public Utilisateur getUtilisateurById(int id) throws SQLException;
}
