package services.concours;

import models.Categorie;
import models.Concours;
import models.OeuvreArt;
import models.Utilisateur;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;
import services.vote.voteServices;
import utils.MyDataBase;
import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class OeuvreConcoursService {

    private Connection connection;
    public OeuvreConcoursService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }


/*_______________________________Ajouter une oeuvre a un concours dans la table oeuvre_concours________________________________________________________*/

    public static void ajouterOeuvreConcours(Connection connection, int idOeuvre, int idConcours) throws SQLException, SQLException {
        // Préparation de la requête
        String sql = "INSERT INTO oeuvre_concours (id_oeuvre, id_concours) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        ((PreparedStatement) statement).setInt(1, idOeuvre);
        statement.setInt(2, idConcours);

        // Exécution de la requête
        statement.executeUpdate();
    }

/*______________________________Retourner une liste des oeuvre associes a un concours____________________________________________________________________*/


    public List<OeuvreArt> getOeuvresByConcoursId(int concoursId) {
        List<OeuvreArt> oeuvres = new ArrayList<>();

        try {
            // Utilisez une requête SQL SELECT pour récupérer les œuvres associées à un concours
            String query = "SELECT oeuvreart.* FROM oeuvre_concours " +
                    "JOIN oeuvreart ON oeuvre_concours.id_oeuvre = oeuvreart.idOeuvreArt " +
                    "WHERE oeuvre_concours.id_concours = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, concoursId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Parcourez les résultats et ajoutez les œuvres à la liste
                    while (resultSet.next()) {
                        int id = resultSet.getInt("idOeuvreArt");
                        String image = resultSet.getString("image");
                        String titre = resultSet.getString("titre");
                        String description = resultSet.getString("description");
                        float prixVente = resultSet.getFloat("prixVente");
                        String status = resultSet.getString("status");
                        Date dateAjout = resultSet.getDate("dateAjout");

                        int categorieId = resultSet.getInt("id_categorie");

                        int artisteId = resultSet.getInt("id_artiste");

                        // Vous devrez probablement implémenter des méthodes de récupération pour les objets liés

                        Categorie categorie =  getCategorieById(categorieId);
                        Utilisateur artiste =  getArtisteById(artisteId);

                        OeuvreArt oeuvre = new OeuvreArt(id, image, titre, description, dateAjout, prixVente, categorie, status, artiste);
                        oeuvres.add(oeuvre);
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

        return oeuvres;
}

    // Méthode pour récupérer une catégorie par ID
    private Categorie getCategorieById(int categorieId) throws SQLException {
        // Implémentez la logique de récupération de la catégorie depuis la base de données
        // Retournez un objet Categorie
        return null;
    }

    // Méthode pour récupérer un artiste par ID
    private Utilisateur getArtisteById(int artisteId) throws SQLException {
        // Implémentez la logique de récupération de l'artiste depuis la base de données
        // Retournez un objet Utilisateur
        return null;
    }

    /*__________________________Supprimer les oeuvres associes a un concours ____________________________________________*/

    public void supprimerOeuvresDuConcours(int idConcours) {
        try {
            // Utilisez une requête SQL DELETE pour supprimer toutes les œuvres associées à un concours
            String deleteQuery = "DELETE FROM oeuvre_concours WHERE id_concours = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, idConcours);

                // Exécutez la requête DELETE
                deleteStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }
/*_________________________________Ajouter DES oeuvre a un concours dans la table oeuvre_concours________________________________________*/
    public void ajouterOeuvresAuConcours(int idConcours, List<OeuvreArt> oeuvres) {
        try {
            // Utilisez une requête SQL INSERT pour ajouter les nouvelles entrées dans la table oeuvre_concours
            String insertQuery = "INSERT INTO oeuvre_concours (id_oeuvre, id_concours) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                for (OeuvreArt oeuvre : oeuvres) {
                    insertStatement.setInt(1, oeuvre.getId());
                    insertStatement.setInt(2,idConcours );

                    // Exécutez la requête INSERT
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }
    /*____________________________________getConcoursIdByOeuvreId_______________________________________________________*/

    public int getConcoursIdByOeuvreId(int oeuvreId) {
        int concoursId = -1; // Valeur par défaut si rien n'est trouvé

        // Utilisez une requête SQL SELECT pour récupérer l'id du concours par l'id de l'oeuvre
        String query = "SELECT id_concours FROM oeuvre_concours WHERE id_oeuvre = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, oeuvreId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    concoursId = resultSet.getInt("id_concours");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

        return concoursId;}
    /*___________________________________________ modifierConcours interface ______________________________________*/

    public void modifierConcours(Concours concoursModifie, List<OeuvreArt> nouvellesOeuvres) {
        try {
            // Commencez par supprimer les anciennes relations avec les œuvres
            supprimerOeuvresDuConcours(concoursModifie.getId());

            // Utilisez une requête SQL UPDATE pour mettre à jour le concours dans la base de données
            String updateQuery = "UPDATE concours SET titre = ?, date_debut = ?, date_fin = ?, description = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                // Paramétrez les valeurs mises à jour
                updateStatement.setString(1, concoursModifie.getTitre());
                updateStatement.setDate(2, Date.valueOf(concoursModifie.getDate_debut()));
                updateStatement.setDate(3, Date.valueOf(concoursModifie.getDate_fin()));
                updateStatement.setString(4, concoursModifie.getDescription());
                updateStatement.setInt(5, concoursModifie.getId());

                // Exécutez la requête UPDATE
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Concours modifié avec succès.");

                    // Ajoutez les nouvelles relations avec les œuvres
                    ajouterOeuvresAuConcours(concoursModifie.getId(), nouvellesOeuvres);
                } else {
                    System.out.println("Aucun concours n'a été modifié. Vérifiez l'ID du concours.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }
/*************************************************/
private voteServices voteService=new voteServices();  // Assurez-vous que la classe VoteService est injectée

    public List<OeuvreArt> getTop3ClassementConcours(int concoursId) {
        List<OeuvreArt> oeuvres = getOeuvresByConcoursId(concoursId);
        Map<Integer, Double> noteMoyenneMap = new HashMap<>();

        // Calculer la note moyenne pour chaque œuvre
        for (OeuvreArt oeuvre : oeuvres) {
            double noteMoyenne = voteService.getNoteMoyenneOeuvre(oeuvre.getId(), concoursId);
            noteMoyenneMap.put(oeuvre.getId(), noteMoyenne);
        }

        // Utilisez Collections.sort avec un Comparator personnalisé
        Collections.sort(oeuvres, new Comparator<OeuvreArt>() {
            @Override
            public int compare(OeuvreArt o1, OeuvreArt o2) {
                // Comparez les notes moyennes de manière décroissante
                double note1 = noteMoyenneMap.getOrDefault(o1.getId(), 0.0);
                double note2 = noteMoyenneMap.getOrDefault(o2.getId(), 0.0);
                return Double.compare(note2, note1);
            }
        });

        // Retournez les trois premières œuvres si la liste en contient au moins trois, sinon, retournez la liste entière
        return oeuvres.size() >= 3 ? oeuvres.subList(0, 3) : oeuvres;
    }
    /*----------------------------------------------------------*/
    public String getArtisteEmail(int oeuvreId) {


        // Préparez la requête SQL
        String sql = "SELECT u.email FROM utilisateur u JOIN oeuvreart o ON u.id = o.id_artiste WHERE o.idOeuvreArt = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Définissez les paramètres de la requête
            preparedStatement.setInt(1, oeuvreId);

            // Exécutez la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            // Traitez les résultats
            if (resultSet.next()) {
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions correctement dans une application réelle
        }

        return null; // Ou lancez une exception si l'email n'est pas trouvé
    }
    public int getArtistePhoneNumber(int oeuvreId) {
        // Préparez la requête SQL
        String sql = "SELECT u.numTel FROM utilisateur u JOIN oeuvreart o ON u.id = o.id_artiste WHERE o.idOeuvreArt = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Définissez les paramètres de la requête
            preparedStatement.setInt(1, oeuvreId);

            // Exécutez la requête
            ResultSet resultSet = preparedStatement.executeQuery();

            // Traitez les résultats
            if (resultSet.next()) {
                return resultSet.getInt("numTel");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les exceptions correctement dans une application réelle
        }

        return 0; // Ou lancez une exception si le numéro de téléphone n'est pas trouvé
    }


}
