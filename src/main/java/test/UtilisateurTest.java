package test;

import com.mysql.cj.util.TimeUtil;
import models.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;

import java.sql.Date;
import java.sql.SQLException;


public class UtilisateurTest {
    public static void main(String[] args) throws SQLException {
        // MyDataBase d =  MyDataBase.getInstance();
        UtilisateurService user1 = new UtilisateurService();
   /* try {
         user1.ajouter(new Utilisateur( "Ouelhazi", "jalila","jalila@gmail.com",67458920,
                 "jalila", 12852360,"123.",1,"imaage ", "femme",new java.sql.Date(28022005), "addddd",0));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }*/
        /*
        try {
            user1.modifier(new Utilisateur(1, "edit", "test","cryptage@gmail.com",
                    12367009, "2cvcv3AFT", "88123.","imcaage ", "gencvre",
                    new java.sql.Date(280922005), "acvcvddd"));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }*/

       /* try {
            user1.supprimer(4);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } */

       /* try {
            System.out.println(user1.listAll());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

      /*  if (user1.checkExistingUser("123.","$2a$10$ykx8XEfAW1RprUgCdGrA1.kASs9vVlTcLzcIej8kpGYajq2ll9pFy")) {
            System.out.println("Password is correct!");
        } else {
            System.out.println("Incorrect password!");
        }*/

    //user1.validateLogin("2330AFT","123.");
   // user1.validateLogin("2330AFT","1888823.");

        try {
            user1.register(new Utilisateur( "hamza","ouelhazi","hamza@gmail.com", "hamza", "123."));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }



    }
      }
