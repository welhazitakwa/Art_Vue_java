package services.offreenchere;

import models.OffreEnchere;
import models.VenteEncheres;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreEnchereService implements IOffreEnchere<OffreEnchere> {
    private Connection connection;
    public OffreEnchereService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void AjouterOffreEnchere(OffreEnchere o) throws SQLException {
        String sql = "INSERT INTO OffreEnchere ( montant, date) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setFloat(1, o.getMontant());
            preparedStatement.setDate(2, new java.sql.Date(o.getDate().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void ModifierOffreEnchere(OffreEnchere o) throws SQLException {
        String sql = "update OffreEnchere set montant = ? ,date = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setFloat(1, o.getMontant());
            preparedStatement.setDate(2, new java.sql.Date(o.getDate().getTime()));
            preparedStatement.setInt(3, o.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void SupprimerOffreEnchere(int id) throws SQLException {
        String req = "DELETE FROM `OffreEnchere` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<OffreEnchere> AfficherOffreEnchere() throws SQLException {
        String sql = "SELECT * FROM OffreEnchere";
        List<OffreEnchere> listeOffreEnchere = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                OffreEnchere cmd = new OffreEnchere();
                cmd.setId(rs.getInt("id"));
                cmd.setMontant(rs.getFloat("montant"));
                cmd.setDate(rs.getDate("date"));
                listeOffreEnchere.add(cmd);
            }
        }
        return listeOffreEnchere;
    }

    public void ajouterOffreEnchere(OffreEnchere o) throws SQLException {
        String sql = "INSERT INTO OffreEnchere (id_VenteEnchere, montant, date) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, o.getId_VenteEnchere());
            preparedStatement.setDouble(2, o.getMontant());
            preparedStatement.setDate(3, new java.sql.Date(o.getDate().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    public List<OffreEnchere> getOffresEncheresForVenteEncheres(int idVenteEnchere) throws SQLException {
        String sql = "SELECT * FROM OffreEnchere WHERE id_VenteEnchere = ?";
        List<OffreEnchere> listeOffreEnchere = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idVenteEnchere);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OffreEnchere offreEnchere = new OffreEnchere();
                    offreEnchere.setId(rs.getInt("id"));
                    offreEnchere.setMontant(rs.getFloat("montant"));
                    offreEnchere.setDate(rs.getDate("date"));
                    listeOffreEnchere.add(offreEnchere);
                }
            }
        }
        return listeOffreEnchere;
    }

}
