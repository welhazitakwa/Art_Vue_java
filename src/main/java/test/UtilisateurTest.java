package test;

import com.mysql.cj.util.TimeUtil;
import models.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Random;


public class UtilisateurTest {

//    public static String createCaptchaValue(){
//        Random random = new Random();
//        int length=7+(Math.abs(random.nextInt()) % 3);
//        StringBuffer captchaStrBuffer = new StringBuffer() ;
//        for (int i=0; i<length;i++) {
//            int baseCharacterNumber = Math.abs(random.nextInt()%62);
//            int characterNumber = 0 ;
//            if(baseCharacterNumber < 26) {
//                characterNumber= 65 + baseCharacterNumber ;
//            } else if (baseCharacterNumber < 52) {
//                characterNumber = 97 + (baseCharacterNumber - 26);
//            }else {
//                characterNumber = 48 + (baseCharacterNumber-52);
//            }
//            captchaStrBuffer.append((char)characterNumber);
//        }
//        return captchaStrBuffer.toString();
//    }
    public static void main(String[] args){
        // MyDataBase d =  MyDataBase.getInstance();
      /*  for (int i=0; i<5;i++) {
            System.out.println("el captcha lma7noun");
            System.out.println(createCaptchaValue());
        }*/
        UtilisateurService utilisateurService = new UtilisateurService();
       // System.out.println(utilisateurService.getUtilisateurById(12).getCin());
/*
      //   Tester la récupération d'un utilisateur par son identifiant
        try {
            int idUtilisateur = 1; // Remplacez 1 par l'identifiant de l'utilisateur que vous souhaitez récupérer
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(idUtilisateur);
            System.out.println(utilisateur);

//            if (utilisateur != null) {
//                System.out.println("Utilisateur trouvé : ");
//                System.out.println("ID : " + utilisateur.getId());
//                System.out.println("Nom : " + utilisateur.getNom());
//                System.out.println("Prénom : " + utilisateur.getPrenom());
//                System.out.println("Email : " + utilisateur.getEmail());
//            } else {
//                System.out.println("Aucun utilisateur trouvé avec l'identifiant " + idUtilisateur);
//            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de l'utilisateur : " + e.getMessage());
        }*/

        UtilisateurService user1 = new UtilisateurService();
        /*try {
            System.out.println(user1.checkEmail("hamzaOuelzi@gmail.com"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        // System.out.println("mlkjhytredfghjklmlllllllllllllll"+ user1.nbArtistes());
  /*    try {
         user1.ajouter(new Utilisateur( "Ferjeni", "Nourhen","Nourhen.ferjeni@gmail.com",92404237,
                 "Nourhen", 12852360,"123.",1,"imaage ", "femme",new java.sql.Date(28022005), "addddd",0));
        } catch (SQLException s){
            System.out.println(s.getMessage());
        }*/

//        try {
//            user1.modifier(new Utilisateur(
//                    12, "ccccccc", "cccccc","cccccc@gmail.com",
//                    11111111, "ccccccc", 11111111,"cccccc ", "ccccccccc",
//                    new java.sql.Date(280922005), "ccccccccccc"));
//        } catch (SQLException s){
//            System.out.println(s.getMessage());
//        }

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

//        try {
//            user1.nouveauMDP("123.","takwawelhazi2@gmail.com");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }}


