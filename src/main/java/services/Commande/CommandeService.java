package services.Commande;
import models.*;
import services.Panier.PanierService;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import services.panieroeuvre.PanieroeuvreService;
public class CommandeService implements ICommande {
    private Connection connection;

    public CommandeService() {
        connection = MyDataBase.getInstance().getConnection();
    }

    public void creerCommande(Commande commande, Panier panier) throws SQLException {
        if (panier == null) {
            System.out.println("Le panier spécifié n'existe pas. Impossible de créer la commande.");
            return;
        }

        // Calcul du montant total de la commande à partir des œuvres dans le panier
        PanieroeuvreService panieroeuvreService = new PanieroeuvreService();
        float montantTotal = panieroeuvreService.calculerMontantTotal(panier.getId());
        commande.setMontant(montantTotal);

        // Définition de l'état initial de la commande
        commande.setEtat("En attente");

        // Insertion de la commande dans la base de données
        String sql = "INSERT INTO commande (montant, date, etat, panier) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, commande.getMontant());
            preparedStatement.setDate(2, new java.sql.Date(commande.getDate().getTime()));
            preparedStatement.setString(3, commande.getEtat());
            preparedStatement.setInt(4, panier.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                // Récupération de l'ID de la commande insérée
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int commandeId = generatedKeys.getInt(1);
                    commande.setId(commandeId);
                    System.out.println("La commande a été ajoutée avec succès avec l'ID : " + commandeId);
                }
            } else {
                System.out.println("Erreur lors de l'ajout de la commande.");
            }
        }
    }

    public void modifierCommande(Commande commande, boolean estExpediee) throws SQLException {
        // Vérifier si la commande existe
        if (commande == null) {
            System.out.println("La commande spécifiée n'existe pas. Impossible de la modifier.");
            return;
        }
        String nouvelEtat = estExpediee ? "Expédiée" : "En attente";

        commande.setEtat(nouvelEtat);
        String sql = "UPDATE commande SET etat = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nouvelEtat);
            preparedStatement.setInt(2, commande.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("La commande a été mise à jour avec succès.");
            } else {
                System.out.println("Aucune commande n'a été modifiée.");
            }
        }

    }

    public Commande getCommandeById(int id) throws SQLException {
        Commande commande = null;
        String sql = "SELECT * FROM commande WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Créez une instance de Commande avec les données récupérées de la base de données
                    commande = new Commande();
                    commande.setId(resultSet.getInt("id"));
                    commande.setMontant(resultSet.getFloat("montant"));
                    commande.setDate(resultSet.getDate("date"));
                    commande.setEtat(resultSet.getString("etat"));
                    int panierId = resultSet.getInt("panier");
                    PanierService panierService = new PanierService(); // Création d'un service pour les utilisateurs
                    Panier panier = panierService.getPanierById(panierId);
                    commande.setPanier(panier);
                } else {
                    // La commande avec l'ID spécifié n'existe pas
                    System.out.println("La commande avec l'ID " + id + " n'existe pas.");
                }
            }
        }
        return commande;
    }


    public void supprimerCommande(int id) throws SQLException {
        String sql = "DELETE FROM commande WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("La commande avec l'ID " + id + " a été supprimée avec succès.");
            } else {
                System.out.println("Aucune commande avec l'ID " + id + " n'a été trouvée.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la commande avec l'ID " + id + " : " + e.getMessage());
        }
    }

    public List<Commande> getCommandesParPanier(int idPanier) throws SQLException {


        List<Commande> commandesPanier = new ArrayList<>();

        String sql = "SELECT * FROM commande WHERE panier = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idPanier);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Commande commande = new Commande();

                    commande.setMontant(rs.getInt("montant"));
                    commande.setDate(rs.getDate("date"));
                    commande.setEtat(rs.getString("etat"));
                    // Assurez-vous de récupérer d'autres détails de la commande selon votre modèle de données
                    commandesPanier.add(commande);
                }
            }
        }

        return commandesPanier;
    }


   public Commande getCommandeByPanierId(int idPanier) throws SQLException {
       String sql = "SELECT c.*, p.id AS panierId\n" +
               "FROM commande c\n" +
               "JOIN panier p ON c.panier = p.id\n" +
               "WHERE p.id = ?\n" +  // Ajouter le paramètre pour l'ID du panier
               "ORDER BY c.id DESC\n" + // Utiliser l'identifiant de commande pour trier
               "LIMIT 1";

       try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, idPanier);  // Définir le paramètre pour l'ID du panier
           try (ResultSet rs = preparedStatement.executeQuery()) {
               if (rs.next()) {
                   Commande commande = new Commande();

                   commande.setMontant(rs.getInt("montant"));
                   commande.setDate(rs.getDate("date"));
                   commande.setEtat(rs.getString("etat"));

                   // Créez un objet Panier et configurez son ID
                   Panier panier = new Panier();
                   panier.setId(rs.getInt("panierId"));

                   // Définissez le panier pour la commande
                   commande.setPanier(panier);

                   return commande;
               } else {
                   return null; // Aucune commande trouvée pour cet ID de panier
               }
           }
       }
   }


}