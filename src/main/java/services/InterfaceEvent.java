package services;



import models.evenement;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceEvent <T>{
    int ajouterEvent (T t,int id) throws SQLException;
    int modifierEvent (T t,int id) throws SQLException ;
    int supprimerEvent (int id) throws SQLException ;
    List<T> listAllEvent () throws SQLException ;
    List<T> listAllEventcalender () throws SQLException ;
    List<String> listAllEventUser (int id) throws SQLException ;
    evenement getEventById(int id) throws SQLException;
    int inscricreEvent(int idu,int idE) throws SQLException;
    int nombredeutulisateurparevent(int id) throws SQLException;
    evenement getEventBynom(String nom) throws SQLException;
}
