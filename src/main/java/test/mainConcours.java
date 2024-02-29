package test;
import models.Concours;
import models.OeuvreArt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Utilisateur;
import models.Vote;
import services.concours.ConcoursService;
import services.vote.voteServices;
import utils.MyDataBase;
import services.concours.OeuvreConcoursService;



public class mainConcours {
    public static void main(String[] args) throws SQLException {

        ConcoursService cs = new ConcoursService();
        voteServices vs = new voteServices();
        Concours Concours = null;


       /* Concours.setTitre("Mon concours");
        Concours.setDateDebut(LocalDate.of(2023, 11, 14));
        Concours.setDateFin(LocalDate.of(2023, 12, 14));
        Concours.setDescription("Ceci est mon concours");*/

       // List<OeuvreArt> oeuvresArts = new ArrayList<>();

    /*    try {
            
            cs.AjouterConcours(Concours, oeuvresArts);
            System.out.println("Concours ajouté avec succès");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

//test CRUD vote
/*
        try {
            vs.AjouterVote(new Vote(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);

*/
      /*    try {
            vs.ModifierVote(new Vote (9,2));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        /* try {
            vs.SupprimerVote(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
      /* try {
            System.out.println(vs.AfficherVote());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
//test CRUD concours

    /*   try {
            cs.AjouterConcours(new Concours("ConcoursMa", LocalDate.of(2024, 2, 18), LocalDate.of(2024, 2, 18),
                    "qjslcfdnjvdj"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        /*try {
            cs.ModifierConcours(new Concours(27,"test", LocalDate.of(2024, 2, 18),LocalDate.of(2024, 2, 18),
                    "skl,kjcndjhb"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
      /*try {
            cs.SupprimerConcours(23);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       /**try {
            System.out.println(cs.AfficherConcours());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }**/
        
        /**int idOeuvre = 3;
        int idConcours = 21;
        try {
            Connection connection= MyDataBase.getInstance().getConnection();
            OeuvreConcoursService.ajouterOeuvreConcours(connection, idOeuvre, idConcours);
        } catch (SQLException e) {
            e.printStackTrace();
        }**/
       /* OeuvreConcoursService instance = new OeuvreConcoursService();

        // Remplacez "1" par l'identifiant du concours que vous souhaitez tester
        int concoursId = 35;

        // Appelez la méthode
        List<OeuvreArt> oeuvres = instance.getOeuvresByConcoursId(concoursId);

        // Affichez les œuvres récupérées
        for (OeuvreArt oeuvre : oeuvres) {
            System.out.println(oeuvre);
        }
           */
        }
    }
