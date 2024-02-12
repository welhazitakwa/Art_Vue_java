package services.concours;


import models.Concours;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.MyDataBase;

public class ConcoursService implements Iconcours<Concours> {

    private Connection connection;


    public ConcoursService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void AjouterConcours(Concours concours) throws SQLException {
        String sql = "INSERT INTO `concours`(`titre`, `date_debut`, `date_fin`, `description`)"
                +"values('"+ Concours.getTitre() + "','"+Concours.getDate_debut()+ "','"+Concours.getDate_fin()+ "','"+Concours.getDescription()+ "')";

        Statement statement=connection.createStatement();

        statement.executeUpdate(sql);
    }

    @Override
    public void ModifierConcours(Concours concours) throws SQLException {
        String sql="UPDATE concours SET titre =?,date_debut =?, date_fin =?,description=? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, concours.getTitre());
            preparedStatement.setDate(2, new java.sql.Date(concours.getDate_debut().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(concours.getDate_fin().getTime()));
            preparedStatement.setString(4, concours.getDescription());
            preparedStatement.setInt(5, concours.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void SupprimerConcours(int id) throws SQLException {
        String req = "DELETE FROM `concours` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Concours> AfficherConcours() throws SQLException {
        String sql = "SELECT * FROM concours";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Concours> listeConcours = new ArrayList<>();
        while (rs.next()) {
            Concours c = new Concours();
            c.setId(rs.getInt("id"));
            c.setTitre(rs.getString("titre"));
            c.setDate_debut(rs.getDate("date_debut"));
            c.setDate_fin(rs.getDate("date_fin"));
            c.setDescription(rs.getString("description"));
            listeConcours.add(c);
        }

        return listeConcours;

    }
}
