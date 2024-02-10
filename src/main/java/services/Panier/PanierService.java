package services.Panier;

import models.Commande;
import models.Panier;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PanierService implements Ipanier<Panier> {
    private Connection connection;


    public PanierService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void AjouterPanier(Panier panier) throws SQLException {
        String sql = "insert into panier (Quantite, dateAjout) values ('" +
                panier.getQuantite() + "', '" +
                panier.getDateAjout() + "')";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @Override
    public void ModifierPanier(Panier panier) throws SQLException {
        String sql = "update panier set quantite = ? ,dateAjout = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, panier.getQuantite());
            preparedStatement.setDate(2, Date.valueOf(panier.getDateAjout()));
            preparedStatement.setInt(3, panier.getId());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void SupprimerPanier(int id) throws SQLException {
            String req = "DELETE FROM `panier` WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
    }

    @Override
    public List<Panier> AfficherPanier() throws SQLException {
        String sql = "SELECT * FROM panier";
        List<Panier> listePaniers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Panier pan = new Panier();
                pan.setId(rs.getInt("id"));
                pan.setQuantite(rs.getInt("quantite"));
                pan.setDateAjout(rs.getDate("dateAjout").toLocalDate());
                listePaniers.add(pan);
            }
        }
        return listePaniers;
    }

}
