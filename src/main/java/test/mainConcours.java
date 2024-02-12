package test;
import models.Concours;

import java.sql.SQLException;
import java.time.LocalDate;

import models.Vote;
import services.concours.ConcoursService;
import services.vote.voteServices;
import utils.MyDataBase;

public class mainConcours {
    public static void main(String[] args) throws SQLException {

        ConcoursService cs = new ConcoursService();
        voteServices vs = new voteServices();
//test CRUD vote
/*
        try {
            vs.AjouterVote(new Vote(2));
        } catch (SQLException e) {
            throw new RuntimeException(e);

*/
         /* try {
            vs.ModifierVote(new Vote (2,9));
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

      /* try {
            cs.AjouterConcours(new Concours("Concours2", new java.sql.Date(29022005),new java.sql.Date(28022005),
                    "qjslcfdnjvdj"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
       /* try {
            cs.ModifierConcours(new Concours(2,"Concours2", new java.sql.Date(280005),new java.sql.Date(28022005),
                    "skl,kjcndjhb"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/
        /*try {
            cs.SupprimerConcours(2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       /*try {
            System.out.println(cs.AfficherConcours());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        }
    }
}