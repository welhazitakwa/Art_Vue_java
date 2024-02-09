package services.utilisateur;

import models.Utilisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import utils.MyDataBase;

public class UtilisateurService implements IUtilisateur<Utilisateur>{

    private Connection connection;
    public UtilisateurService (){
        connection = MyDataBase
    }
    @Override
    public void ajouter(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void modifier(Utilisateur utilisateur) throws SQLException {

    }

    @Override
    public void supprimer(int id) throws SQLException {

    }

    @Override
    public List<Utilisateur> listAll() throws SQLException {
        return null;
    }
}
