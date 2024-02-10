package test;

import models.Evènement;
import services.Event.Events;
import utils.MyDataBase;
import java.time.LocalDate;
import java.sql.SQLException;

import static java.time.LocalDate.*;

public class mainEvent {
    public static void main(String [] args){
        //  MyDabase d=MyDabase.getInstance();
        Events es=new Events();
        try {

            Evènement even;
            even = new Evènement("ev","ariana",LocalDate.of(2024,2,8));
            es.ajouterevent(even);
        } catch (SQLException e) {
            System.out.println((e.getMessage()));
        }
        try {
            Evènement even;
            even =
                    new Evènement(1,"amira", "evente", LocalDate.of(2022, 1, 5));
            es.modifier(even);
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