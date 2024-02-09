package services.Commande;
import models.Categorie;
import models.Commande;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class CommandeService implements ICommande<Commande> {
    private Connection connection;


    public CommandeService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }
    public void AjouterCommande(Commande commande) throws SQLException {
        String sql = "insert into commande (montant,date,etat) " +
                "values('" + commande.getMontant()+ "' " + commande.getEtat() + " " + commande.getDate() + ")";

        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }
    public void ModifierCommande(Commande commande) throws SQLException {
        String sql = "update commande set montant = ? ,date = ? , etat = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setFloat(1, commande.getMontant());
            preparedStatement.setDate(2, new java.sql.Date(commande.getDate().getTime()));
            preparedStatement.setString(3, commande.getEtat());
            preparedStatement.setInt(4, commande.getId());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void SupprimerCommande(int id) throws SQLException {
        String req = "DELETE FROM `commande` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Commande> AfficherCommande() throws SQLException {
        String sql = "SELECT * FROM commande";
        List<Commande> listeCommandes = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Commande cmd = new Commande();
                cmd.setId(rs.getInt("id"));
                cmd.setMontant(rs.getFloat("montant"));
                cmd.setDate(rs.getDate("date"));
                cmd.setEtat(rs.getString("etat"));
                listeCommandes.add(cmd);
            }
        }
        return listeCommandes;

    }
}
