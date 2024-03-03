package services.venteencheres;

import models.Exposition;
import models.VenteEncheres;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VenteEncheresService implements IVenteEncheres<VenteEncheres>{

    private Connection connection;
    public VenteEncheresService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public void AjouterVenteEncheres(VenteEncheres venteEncheres) throws SQLException {
        String sql = "INSERT INTO venteencheres ( dateDebut, dateFin, prixDepart, statue,id_exposition) VALUES (?, ?, ?, ?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(venteEncheres.getDateDebut().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(venteEncheres.getDateFin().getTime()));
            preparedStatement.setFloat(3, venteEncheres.getPrixDepart());
            preparedStatement.setString(4, venteEncheres.getStatue());
            preparedStatement.setInt(5, venteEncheres.getIdExposition());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void ModifierVenteEncheres(VenteEncheres venteEncheres) throws SQLException {
        String sql = "update venteencheres set dateFin = ? , prixDepart = ?, Statue = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, new java.sql.Date(venteEncheres.getDateFin().getTime()));
            preparedStatement.setFloat(2, venteEncheres.getPrixDepart());
            preparedStatement.setString(3, venteEncheres.getStatue());
            preparedStatement.setInt(4, venteEncheres.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void SupprimerVenteEncheres(int id) throws SQLException {
        String req = "DELETE FROM `venteencheres` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<VenteEncheres> AfficherVenteEncheres() throws SQLException {
        String sql = "SELECT * FROM venteencheres";
        List<VenteEncheres> listeVenteEncheres = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                VenteEncheres cmd = new VenteEncheres();
                cmd.setId(rs.getInt("id"));
                cmd.setDateDebut(rs.getDate("dateDebut"));
                cmd.setDateFin(rs.getDate("dateFin"));
                cmd.setPrixDepart(rs.getFloat("PrixDepart"));
                cmd.setStatue(rs.getString("statue"));
                listeVenteEncheres.add(cmd);
            }
        }
        return listeVenteEncheres;
    }
    public List<VenteEncheres> AfficherVenteEncheresParExposition(int idExposition) throws SQLException {
        String sql = "SELECT * FROM venteencheres WHERE id_exposition = ?";
        List<VenteEncheres> listeVenteEncheres = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idExposition);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                VenteEncheres venteEncheres = new VenteEncheres();
                venteEncheres.setId(rs.getInt("id"));
                venteEncheres.setDateDebut(rs.getDate("dateDebut"));
                venteEncheres.setDateFin(rs.getDate("dateFin"));
                venteEncheres.setPrixDepart(rs.getFloat("PrixDepart"));
                venteEncheres.setStatue(rs.getString("statue"));
                listeVenteEncheres.add(venteEncheres);
            }
        }

        return listeVenteEncheres;
    }
}
