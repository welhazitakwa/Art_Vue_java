package test;

import models.Panier;
import services.Panier.PanierService;
import utils.MyDataBase;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate.*;
public class MainPanier {
    public static void main(String[] args) {
        MyDataBase d = MyDataBase.getInstance();
        PanierService pan1=new PanierService();
        PanierService pan2=new PanierService();
        PanierService pan3=new PanierService();
        PanierService pan4=new PanierService();
     /*   try
        {
            //pan1.AjouterPanier(new Panier(12, LocalDate.of(2024, 1, 1)));
//pan2.AjouterPanier(new Panier(5,LocalDate.of(2024,1,1)));
//pan3.AjouterPanier(new Panier(6,LocalDate.of(2023,1,1)));
pan4.AjouterPanier(new Panier(7,LocalDate.of(2024,1,4)));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }*/
     /*   try {
            pan3.ModifierPanier(new Panier(3,6,LocalDate.of(2024,1,1)));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }*/
      /*  try
        {
            pan4.SupprimerPanier(4);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }*/
        try {
            System.out.println(pan1.AfficherPanier());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
    }
