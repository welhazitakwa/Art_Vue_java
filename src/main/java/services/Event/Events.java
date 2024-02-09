package services.Event;
import models.Evènement;
import utils.MyDataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Events implements Evènements<Evènement>{

    private Connection connection;
    public Events(){
        connection= MyDataBase.getInstance().getConnection();
    }

    @Override
    public void ajouterevent(Evènement evènement) throws SQLException {
        String sql="insert into Evènement(id,nom,lieu,date)"+
                "VALUES(" + evènement.getId() + ", '" + evènement.getNom() + "','" + evènement.getLieu() + "','" + evènement.getDate() + "')";

        Statement statement=connection.createStatement();
        statement.executeUpdate(sql);

    }

    @Override
    public void modifier(Evènement evènement) throws SQLException {
        String sql = "update evènement set nom = ? , date = ? , lieu = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql) ;

        preparedStatement.setString(1,Evènement.getNom());
        preparedStatement.setDate(2, Date.valueOf(Evènement.getDate()));
        preparedStatement.setString(3, Evènement.getLieu());
        preparedStatement.setInt(4, Evènement.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM 'evènement' WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public List<Evènement> recuperer() throws SQLException {
        String sql = "SELECT * FROM evènement";
        List<Evènement> listeEvènement = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Evènement ev = new Evènement();
                ev.setId(rs.getInt("id"));
                ev.setNom(rs.getString("nom"));
                ev.setNom(rs.getString("lieu"));
                ev.setNom(String.valueOf(rs.getDate("date")));
                listeEvènement.add(ev);
            }
        }
        return listeEvènement;
    }
}
