package test;
import models.Exposition;
import services.Exposition.ExpositionService;
import utils.MyDataBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;

public class mainexpo {
    public static void main(String[] args) {
        /*MyDataBase d = MyDataBase.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ExpositionService cmd1 = new ExpositionService();
        ExpositionService cmd2 = new ExpositionService();
        ExpositionService cmd3 = new ExpositionService();
        ExpositionService cmd4 = new ExpositionService();
        try {
            cmd1.AjouterExposition(new Exposition(1, "aaa",new Date(100, 1, 5),new Date(100, 1, 5), 1));
            cmd2.AjouterExposition(new Exposition(2, "bbb",new Date(100, 1, 5),new Date(100, 1, 5), 2));
            cmd3.AjouterExposition(new Exposition(3, "ccc",new Date(100, 1, 5),new Date(100, 1, 5), 3));
            cmd4.AjouterExposition(new Exposition(4, "ddd",new Date(100, 1, 5),new Date(100, 1, 5), 4));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            cmd1.ModifierExposition( new Exposition(1, "aab",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 6));
            //  cmd3.ModifierCommande(new Commande(3,135,LocalDate.of(2024,1,15),"recu"));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }*/
        /*try{
            cmd1.SupprimerExposition(3);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(cmd1.AfficherExposition());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    }
}
