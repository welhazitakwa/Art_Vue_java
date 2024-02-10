package test;
import models.Billetevènement;
import services.billetevent.Billetevent;
import utils.MyDataBase;
import java.sql.SQLException;

public class testbillet {public static void main(String [] args){

    //  MyDabase d=MyDabase.getInstance();
    Billetevent es=new Billetevent();
    /*try {

        Billetevènement even;
        even = new Billetevènement(5.1f);
        es.ajouterbilletevent(even);
    } catch (SQLException e) {
        System.out.println((e.getMessage()));
    }
    try {

        Billetevènement even;
        even = new Billetevènement(1,6.1f);
        es.modifier(even);
    } catch (SQLException e) {
        System.out.println((e.getMessage()));
    }*/

    try {

        Billetevènement even;
        even = new Billetevènement(1,6.1f);
        es.supprimer(even.getId_event());
    } catch (SQLException e) {
        System.out.println((e.getMessage()));
    }

    try {

        System.out.println(es.recuperer());
    } catch (SQLException e) {
        System.out.println((e.getMessage()));

    }


}
}
