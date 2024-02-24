package services.concours;


import models.Concours;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.OeuvreArt;
import utils.MyDataBase;


public class ConcoursService implements Iconcours<Concours> {

    private static Connection connection;


    public ConcoursService()
    {
        connection = MyDataBase.getInstance().getConnection();
    }

    /*___________________________________________________ajouter1 concours_____________________________________________________________________________*/
    @Override
    public void AjouterConcours(Concours concours) throws SQLException {
        /*String sql = "INSERT INTO `concours`(`titre`, `date_debut`, `date_fin`, `description`)"
                +"values('"+ Concours.getTitre() + "','"+Concours.getDate_debut()+ "','"+Concours.getDate_fin()+ "','"+Concours.getDescription()+ "')";

        Statement statement=connection.createStatement();

        statement.executeUpdate(sql);*/
    }

    /*_______________________________________ajouter3 un concours avec des Oeuvres ____________________________________________*/

// Méthode pour ajouter un concours avec des oeuvres spécifiques
    public void ajouterConcoursAvecOeuvres(Concours concours, List<OeuvreArt> oeuvres) {
        try {
            // Partie 1: Ajouter le concours
            String queryAjoutConcours = "INSERT INTO concours (titre, date_debut, date_fin, description) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryAjoutConcours, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, concours.getTitre());

                preparedStatement.setDate(2, Date.valueOf(concours.getDate_debut()));
                preparedStatement.setDate(3, Date.valueOf(concours.getDate_fin()));
                preparedStatement.setString(4, concours.getDescription());
                preparedStatement.executeUpdate();

                // Récupérer l'ID généré pour le concours
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        concours.setId(generatedKeys.getInt(1));
                    }
                }
            }

            // Partie 2: Associer les oeuvres au concours dans la table de liaison "oeuvre_concours"
            String queryAjoutOeuvreConcours = "INSERT INTO oeuvre_concours (id_oeuvre, id_concours) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryAjoutOeuvreConcours)) {
                for (OeuvreArt oeuvre : oeuvres) {
                    preparedStatement.setInt(1, oeuvre.getId());
                    preparedStatement.setInt(2, concours.getId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer les erreurs ici (par exemple, rollback si nécessaire)
        }}

  /*________________________________________________ModifierConcours CRUD___________________________________________________________________*/
    @Override
   public void ModifierConcours(Concours concours) throws SQLException {
       String sql="UPDATE concours SET titre =?,date_debut =?, date_fin =?,description=? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, concours.getTitre());
            preparedStatement.setDate(2, Date.valueOf(concours.getDate_debut()));
            preparedStatement.setDate(3, Date.valueOf(concours.getDate_fin()));
            preparedStatement.setString(4, concours.getDescription());
            preparedStatement.setInt(5, concours.getId());
            preparedStatement.executeUpdate();
        }
    }
    /*___________________________________________SupprimerConcours CRUD_______________________________________________________*/
    @Override
    public void SupprimerConcours(int id) throws SQLException {
        String req = "DELETE FROM `concours` WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(req)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

    /*_____________________________________________AfficherConcours CRUD_____________________________________________________*/

    public List<Concours> AfficherConcours() throws SQLException {
        String sql = "SELECT * FROM concours";

        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        List<Concours> listeConcours = new ArrayList<>();
        while (rs.next()) {
            Concours c = new Concours
                    (rs.getInt("id"), rs.getString("titre"), rs.getDate("date_debut").toLocalDate(), rs.getDate("date_fin").toLocalDate(), rs.getString("description"));

            listeConcours.add(c);
        }

        return listeConcours;

    }
    /*____________________________________________ Retourner une liste des concours ______________________________________________________*/
    public static List<Concours> getConcoursList() {
        List<Concours> concoursList = new ArrayList<>();

        try {
            // Utilisez une requête SQL pour récupérer les données des concours depuis la base de données
            String query = "SELECT * FROM concours";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                // Parcourez les résultats et créez des objets Concours
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String titre = resultSet.getString("titre");
                    LocalDate dateDebut = resultSet.getDate("date_debut").toLocalDate();
                    LocalDate dateFin = resultSet.getDate("date_fin").toLocalDate();
                    String description = resultSet.getString("description");

                    Concours concours = new Concours(id, titre, dateDebut, dateFin, description);
                    concoursList.add(concours);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }

        return concoursList;
    }


    /*__________________________________________ supprimerConcours interface ________________________________________________*/
    public void supprimerConcours(Concours concours) {
        try {
            // Supprimer les œuvres associées à ce concours dans la table oeuvre_concours
            String deleteOeuvreConcoursQuery = "DELETE FROM oeuvre_concours WHERE id_concours = ?";
            try (PreparedStatement deleteOeuvreConcoursStatement = connection.prepareStatement(deleteOeuvreConcoursQuery)) {
                deleteOeuvreConcoursStatement.setInt(1, concours.getId());
                deleteOeuvreConcoursStatement.executeUpdate();
            }

            // Supprimer le concours dans la table concours
            String deleteConcoursQuery = "DELETE FROM concours WHERE id = ?";
            try (PreparedStatement deleteConcoursStatement = connection.prepareStatement(deleteConcoursQuery)) {
                deleteConcoursStatement.setInt(1, concours.getId());
                deleteConcoursStatement.executeUpdate();
            }

            // La suppression a réussi
            System.out.println("Concours supprimé avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les exceptions de manière appropriée dans votre application
        }
    }
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


    /*___________________________________________supprimerOeuvresDuConcours_________________________________________________________________*/
    private void supprimerOeuvresDuConcours(int idConcours) {
        try {
            // Utilisez une requête SQL DELETE pour supprimer les entrées correspondantes dans la table oeuvre_concours
            String deleteQuery = "DELETE FROM oeuvre_concours WHERE id_concours = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, idConcours);

                // Exécutez la requête DELETE
                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Œuvres supprimées avec succès pour le concours avec l'ID : " + idConcours);
                } else {
                    System.out.println("Aucune œuvre associée au concours avec l'ID : " + idConcours);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }

    /*___________________________________________ajouterOeuvresAuConcours________________________________________________________________*/
    private void ajouterOeuvresAuConcours(int idConcours, List<OeuvreArt> oeuvres) {
        try {
            // Utilisez une requête SQL INSERT pour ajouter les nouvelles entrées dans la table oeuvre_concours
            String insertQuery = "INSERT INTO oeuvre_concours (id_concours, id_oeuvre) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                for (OeuvreArt oeuvre : oeuvres) {
                    insertStatement.setInt(1, idConcours);
                    insertStatement.setInt(2, oeuvre.getId());

                    // Exécutez la requête INSERT
                    int rowsAffected = insertStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Œuvre ajoutée avec succès au concours avec l'ID : " + idConcours);
                    } else {
                        System.out.println("Échec de l'ajout de l'œuvre au concours avec l'ID : " + idConcours);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions de manière appropriée dans votre application
        }
    }
}

