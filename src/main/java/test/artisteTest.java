package test;

import models.Artiste;
import models.Utilisateur;
import services.utilisateur.ArtisteService;
import services.utilisateur.UtilisateurService;

import java.sql.SQLException;

public class artisteTest {
    public static void main(String[] args) {
        ArtisteService art1 = new ArtisteService() ;
       try {
           art1.ajouter(new Utilisateur("artisto","artprenom","artiste@gmail.com","artiste","123."));
       } catch (SQLException s){
           System.out.println(s.getMessage());
       }

    }
}
