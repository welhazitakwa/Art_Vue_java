package test;

import models.Commande;
import services.Commande.CommandeService;
import utils.MyDataBase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate.*;

public class MainCommande {
    public static void main(String[] args) {

        /*MyDataBase d = MyDataBase.getInstance();
        CommandeService cmd1 = new CommandeService();
        CommandeService cmd2 = new CommandeService();
        CommandeService cmd3 = new CommandeService();
        CommandeService cmd4 = new CommandeService();

        try {
            cmd1.AjouterCommande(new Commande(12, LocalDate.of(2024, 1, 1), "recu"));
            cmd2.AjouterCommande(new Commande(10, LocalDate.of(2024, 1, 2), "nonrecu"));
            cmd3.AjouterCommande(new Commande(13, LocalDate.of(2024, 1, 3), "recu"));
            cmd4.AjouterCommande(new Commande(14,LocalDate.of(2024,1,17),"recu"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       /* try {
            cmd1.ModifierCommande( new Commande(1, 11, LocalDate.of(2024, 2, 1), "recu"));
            //  cmd3.ModifierCommande(new Commande(3,135,LocalDate.of(2024,1,15),"recu"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
       /* try{
           cmd3.SupprimerCommande(3);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(cmd1.AfficherCommande());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

    }

}