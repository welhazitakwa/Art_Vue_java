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

        UtilisateurService utilisateurService = new UtilisateurService();

        // Tester la récupération d'un utilisateur par son identifiant
//        try {
//            int idUtilisateur = 1; // Remplacez 1 par l'identifiant de l'utilisateur que vous souhaitez récupérer
//            Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
//            if (utilisateur != null) {
//                System.out.println("Utilisateur trouvé : ");
//                System.out.println("ID : " + utilisateur.getId());
//                System.out.println("Nom : " + utilisateur.getNom());
//                System.out.println("Prénom : " + utilisateur.getPrenom());
//                System.out.println("Email : " + utilisateur.getEmail());
//            } else {
//                System.out.println("Aucun utilisateur trouvé avec l'identifiant " + idUtilisateur);
//            }
//        } catch (SQLException e) {
//            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
//        }

        UtilisateurService user1 = new UtilisateurService();
      /*try {
         user1.ajouter(new Utilisateur( "Ferjeni", "Nourhen","Nourhen.ferjeni@gmail.com",92404237,
                 "Nourhen", 12852360,"123.",1,"imaage ", "femme",new java.sql.Date(28022005), "addddd",0));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }

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


//        try {
//            System.out.println("lartttiiisstttessss");
//            System.out.println(user1.listAllArtistes());
//            System.out.println("cliiieennntttssss");
//            System.out.println(user1.listClients());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


      /*  if (user1.checkExistingUser("123.","$2a$10$ykx8XEfAW1RprUgCdGrA1.kASs9vVlTcLzcIej8kpGYajq2ll9pFy")) {
            System.out.println("Password is correct!");
        } else {
            System.out.println("Incorrect password!");
        }*/


        //  System.out.println(user1.validateLogin("hamdzaa","123."));
        //  user1.validateLogin("hamza2","1888823.");
/*
        try {
            user1.register(new Utilisateur( "hamza","ouelhazi","hamzaa@gmail.com", "hamzaa", "123."),"artiste");
            user1.register(new Utilisateur( "hamza","ouelhazi","hamza2@gmail.com", "hamza2", "123."),"client");

        } catch (SQLException s){
            System.out.println(s.getMessage());
        }

*/

    }}


