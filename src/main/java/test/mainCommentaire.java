package test;

import models.Commentaire;
import services.commentaire.CommentaireService;

import java.sql.SQLException;

public class mainCommentaire
{
    public static void main(String[] args) {
        CommentaireService comment1 = new CommentaireService() ;
//         try {
//         comment1.ajouter(new Commentaire( "very nice !!",32,32));
//        } catch (SQLException s){
//            System.out.println(s.getMessage());
//        }
//        try {
//            comment1.modifier(new Commentaire(2, "niiiiccceeeeee"));
//        } catch (SQLException s){
//            System.out.println(s.getMessage());
//    try {
//        comment1.supprimer(3);
//    } catch (SQLException e) {
//    System.out.println(e.getMessage());
//}

        try {
            System.out.println("ouuueevvvrreeeeee");
            System.out.println(comment1.getCommentsByOeuvre(32));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        }

}
