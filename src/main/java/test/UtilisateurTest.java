package test;

import com.mysql.cj.util.TimeUtil;
import models.Utilisateur;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;

import java.sql.SQLException;
import java.util.Date;


public class UtilisateurTest {
    public static void main(String[] args) {
        //new java.util.Date().getTime()
       // MyDataBase d =  MyDataBase.getInstance();
        UtilisateurService user1 = new UtilisateurService();
        try {
            user1.ajouter(new Utilisateur(
                    "Ouelhazi", "Takwa","test@gmail.com",12365479, "233AFT", "123.","imaage ", "genre", "datee ", "addddd"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }
    }
}
