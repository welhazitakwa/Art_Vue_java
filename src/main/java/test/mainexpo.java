package test;
import models.Exposition;
import models.OffreEnchere;
import models.VenteEncheres;
import services.Exposition.ExpositionService;
import services.offreenchere.OffreEnchereService;
import services.venteencheres.VenteEncheresService;
import utils.MyDataBase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;


public class mainexpo {
    public static void main(String[] args) {
        MyDataBase d = MyDataBase.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        //table Exposition

        ExpositionService cmd1 = new ExpositionService();
        ExpositionService cmd2 = new ExpositionService();
        ExpositionService cmd3 = new ExpositionService();
        ExpositionService cmd4 = new ExpositionService();
        try {
            cmd1.AjouterExposition(new Exposition(1, "aaa",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 1));
            cmd2.AjouterExposition(new Exposition(2, "bbb",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 2));
            cmd3.AjouterExposition(new Exposition(3, "ccc",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 3));
            cmd4.AjouterExposition(new Exposition(4, "ddd",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 4));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }
        /*try {
            cmd1.ModifierExposition( new Exposition(1, "aab",dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"), 6));
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

        //table VenteEncheres

        /*VenteEncheresService cmd1 = new VenteEncheresService();
        VenteEncheresService cmd2 = new VenteEncheresService();
        VenteEncheresService cmd3 = new VenteEncheresService();
        VenteEncheresService cmd4 = new VenteEncheresService();
        try {
            cmd1.AjouterVenteEncheres(new VenteEncheres(1,dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"),6.5f, "fff"));
            cmd2.AjouterVenteEncheres(new VenteEncheres(2,dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"),5.6f, "fff"));
            cmd3.AjouterVenteEncheres(new VenteEncheres(3,dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"),6.8f, "fff"));
            cmd4.AjouterVenteEncheres(new VenteEncheres(4,dateFormat.parse("2000-05-25"),dateFormat.parse("2000-05-25"),3.7f, "fff"));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            cmd1.ModifierVenteEncheres( new VenteEncheres(1,dateFormat.parse("2002-05-25"),dateFormat.parse("2002-05-25"),6.5f, "fffmm"));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }*/
        /*try{
            cmd1.SupprimerVenteEncheres(3);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(cmd1.AfficherVenteEncheres());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/

        //table offreenchere

        //OffreEnchereService cmd1 = new OffreEnchereService();
        /*OffreEnchereService cmd2 = new OffreEnchereService();
        OffreEnchereService cmd3 = new OffreEnchereService();
        OffreEnchereService cmd4 = new OffreEnchereService();
        try {
            cmd1.AjouterOffreEnchere(new OffreEnchere(1,6.5f,dateFormat.parse("2000-05-25")));
            cmd2.AjouterOffreEnchere(new OffreEnchere(2,6.5f,dateFormat.parse("2000-05-25")));
            cmd3.AjouterOffreEnchere(new OffreEnchere(3,6.5f,dateFormat.parse("2000-05-25")));
            cmd4.AjouterOffreEnchere(new OffreEnchere(4,6.5f,dateFormat.parse("2000-05-25")));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            cmd1.ModifierOffreEnchere( new OffreEnchere(1,8.6f,dateFormat.parse("2005-05-25")));
        } catch (SQLException | ParseException e) {
            System.out.println(e.getMessage());
        }*/
        /*try{
            cmd1.SupprimerOffreEnchere(3);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        /*try {
            System.out.println(cmd1.AfficherOffreEnchere());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
    }
}
