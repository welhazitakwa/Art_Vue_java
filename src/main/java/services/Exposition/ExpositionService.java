package services.Exposition;
import models.Exposition;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpositionService implements  IExposition<Exposition>{
    private final Connection connection;
    public ExpositionService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void AjouterExposition(Exposition exposition) throws SQLException {
        String sql = "INSERT INTO exposition ( id, nom, dateDebut, dateFin, nbrOeuvre) VALUES ( ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, exposition.getId());
            preparedStatement.setString(2, exposition.getNom());
            preparedStatement.setDate(3, new java.sql.Date(exposition.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(exposition.getDateFin().getTime()));
            preparedStatement.setInt(5, exposition.getNbrOeuvre());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void ModifierExposition(Exposition exposition) throws SQLException {
        String sql = "update exposition set nom = ? ,dateDebut = ? ,dateFin = ? , nbrOeuvre = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, exposition.getNom());
            preparedStatement.setDate(2, new java.sql.Date(exposition.getDateDebut().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(exposition.getDateFin().getTime()));
            preparedStatement.setInt(4, exposition.getNbrOeuvre());
            preparedStatement.setInt(5, exposition.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void SupprimerExposition(int id) throws SQLException {
        String req = "DELETE FROM `exposition` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Exposition> AfficherExposition() throws SQLException {
        String sql = "SELECT * FROM exposition";
        List<Exposition> listeExposition = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Exposition cmd = new Exposition();
                cmd.setId(rs.getInt("id"));
                cmd.setNom(rs.getString("nom"));
                cmd.setDateDebut(rs.getDate("dateDebut"));
                cmd.setDateFin(rs.getDate("dateFin"));
                cmd.setNbrOeuvre(rs.getInt("nbrOeuvre"));
                listeExposition.add(cmd);
            }
        }
        return listeExposition;

    }
    public void associerVenteExposition(int idVenteEnchere, int idExposition) throws SQLException {
        // Réalisez ici la logique pour associer la vente aux enchères à l'exposition
        // Par exemple, vous pouvez exécuter une requête SQL pour mettre à jour l'attribut id_exposition dans la table vente_enchere
        String sql = "UPDATE venteencheres SET id_exposition = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idExposition);
            preparedStatement.setInt(2, idVenteEnchere);
            preparedStatement.executeUpdate();
        }
    }

}
