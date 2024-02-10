package services.billetevent;
import models.Billetevènement;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Billetevent implements Ibillet<Billetevènement> {
    private Connection connection;
    public Billetevent(){
        connection= MyDataBase.getInstance().getConnection();
    }
    @Override
    public void ajouterbilletevent(Billetevènement billetevènement) throws SQLException {
        String sql="insert into Billetevenement(id_event,prix)"+
                "VALUES(" + billetevènement.getId_event() + ", '" + billetevènement.getPrix() +  "')";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);

    }

    @Override
    public void modifier(Billetevènement billetevènement) throws SQLException {
        String sql = "update billetevenement set prix = ?  where id_event = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql) ;

        preparedStatement.setFloat(1, billetevènement.getPrix());
        preparedStatement.setInt(2,billetevènement.getId_event() );
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id_event) throws SQLException {
        String req = "DELETE FROM billetevenement WHERE id_event = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1,id_event);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Billetevènement> recuperer() throws SQLException {

        String sql = "SELECT * FROM billetevenement";
        List<Billetevènement> listebilletevenement = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Billetevènement ev = new Billetevènement();
                ev.setId_event(rs.getInt("id_event"));
                ev.setPrix(rs.getFloat("prix"));
                listebilletevenement.add(ev);
            }
        }
        return listebilletevenement;
    }
}

