package services.Panier;

import models.Commande;
import models.Panier;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Utilisateur;
public class PanierService implements Ipanier<Panier> {
    private Connection connection;


    public PanierService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }

    @Override
    public void AjouterPanier(Panier panier) throws SQLException {
        if (panier.getClient() == null) {
            System.out.println("Le client est nul. Impossible d'ajouter le panier.");
            return; // Arrêter l'exécution de la méthode
        }
        if (clientPossedePanier(panier.getClient().getId())) {
            System.out.println("Le client possède déjà un panier. Impossible d'ajouter un nouveau panier.");
            return; // Arrêter l'exécution de la méthode
        }
        String sql = "INSERT INTO panier (dateAjout,client) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(panier.getDateAjout().getTime()));
            preparedStatement.setInt(2, panier.getClient().getId());
            preparedStatement.executeUpdate();
        }
    }

    private boolean clientPossedePanier(int clientId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM panier WHERE client = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, clientId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }
    @Override
    public void ModifierPanier(Panier panier) throws SQLException {
        if (panier.getId() <= 0) {
            System.out.println("Le panier doit avoir un ID valide pour être modifiée.");
            return;
        }

        String sql = "UPDATE panier SET dateAjout = ?, client = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setDate(1, new java.sql.Date(panier.getDateAjout().getTime()));
            preparedStatement.setInt(2, panier.getClient().getId());
            preparedStatement.setInt(3, panier.getId());

            preparedStatement.executeUpdate();
            System.out.println("Le panier a été modifiée avec succès.");
        }

    }

    @Override
    public void SupprimerPanier(int id) throws SQLException {
        String sql = "DELETE FROM panier WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Le panier avec l'ID " + id + " a été supprimée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'oeuvre d'art avec l'ID " + id + " : " + e.getMessage());
        }
    }

    @Override
    public List<Panier> AfficherPanier() throws SQLException {
        List<Panier> paniers = new ArrayList<>();

        String sql = "SELECT p.*, u.id AS clientId, u.nom AS clientNom, u.prenom AS clientPrenom " +
                "FROM panier p " +
                "JOIN utilisateur u ON p.client = u.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
Panier panier = new Panier();
panier.setId(rs.getInt("id"));
panier.setDateAjout(rs.getDate("DateAjout"));
                Utilisateur client = new Utilisateur();
                client.setId(rs.getInt("clientId"));
                client.setNom(rs.getString("clientNom"));
                client.setPrenom(rs.getString("clientPrenom"));
                panier.setClient(client);
                paniers.add(panier);
            }
        }
        return paniers;
    }


    public Panier getPanierById(int id) throws SQLException {
        Panier panier = null;
        String sql = "SELECT * FROM panier WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Création d'un objet Panier avec les données récupérées de la base de données
                    panier = new Panier();
                    panier.setId(resultSet.getInt("id"));
                    panier.setDateAjout(resultSet.getDate("dateAjout"));

                    // Récupération des détails du client associé au panier
                    int clientId = resultSet.getInt("client");
                    UtilisateurService utilisateurService = new UtilisateurService(); // Création d'un service pour les utilisateurs
                    Utilisateur client = utilisateurService.getUtilisateurById(clientId);
                    panier.setClient(client);
                }
            }
        }
        return panier;
    }








}
