package services;


import models.evenement;
import utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService implements InterfaceEvent<evenement>{
    private Connection connection;
    public EventService (){
        connection = MyDataBase.getInstance().getConnection();
    }
    @Override
    public int ajouterEvent(evenement evenement,int id) throws SQLException {
        String req = "INSERT INTO evenement (nom, lieu, date, calender, price, capacite) " +
                "VALUES ('" + evenement.getNom() + "', " +
                "'" + evenement.getLieu() + "', " +
                "'" + evenement.getDate() + "', " +
                "'" + id + "', " +
                "'" + evenement.getPrice() + "', " +
                "'" + evenement.getCapacite() + "')";
        Statement statement = connection.createStatement();
        int rows= statement.executeUpdate(req);
        if (rows > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int modifierEvent(evenement evenement, int id) throws SQLException {
        String req = "UPDATE evenement SET " +
                "nom = '" + evenement.getNom() + "', " +
                "lieu = '" + evenement.getLieu() + "', " +
                "date = '" + evenement.getDate() + "', " +
                "price = '" + evenement.getPrice() + "', " +
                "capacite = '" + evenement.getCapacite() + "' " +
                "WHERE id = " + id;
        Statement statement = connection.createStatement();
        int rowsUpdated = statement.executeUpdate(req);
        if (rowsUpdated > 0) {
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    public int supprimerEvent(int id) throws SQLException {
        String reqDeleteUtilisateurs = "DELETE FROM utilisateurs_evenement WHERE id_evenement = ?";
        String reqDeleteEvenement = "DELETE FROM evenement WHERE id = ?";

        int rowsDeletedUtilisateurs;
        try (PreparedStatement statement1 = connection.prepareStatement(reqDeleteUtilisateurs)) {
            statement1.setInt(1, id);
            rowsDeletedUtilisateurs = statement1.executeUpdate();
        } catch (SQLException e) {
            // Gérer l'erreur
            e.printStackTrace();
            return 0;
        }

        if (rowsDeletedUtilisateurs > 0) {
            int rowsDeletedEvenement;
            try (PreparedStatement statement2 = connection.prepareStatement(reqDeleteEvenement)) {
                statement2.setInt(1, id);
                rowsDeletedEvenement = statement2.executeUpdate();
            } catch (SQLException e) {
                // Gérer l'erreur
                e.printStackTrace();
                return 0;
            }

            return rowsDeletedEvenement > 0 ? 1 : 0;
        } else {

            try (PreparedStatement statement3 = connection.prepareStatement(reqDeleteEvenement)) {
                statement3.setInt(1, id);
                int rowsDeletedEvenement = statement3.executeUpdate();
                return rowsDeletedEvenement > 0 ? 1 : 0;
            } catch (SQLException e) {

                e.printStackTrace();
                return 0;
            }
        }
    }



    @Override
    public List<evenement> listAllEvent() throws SQLException {
        List<evenement> evenements = new ArrayList<>();
        String req = "SELECT e.nom AS evenement_nom, e.id AS evenement_id, e.lieu AS evenement_lieu, e.date AS evenement_date, e.price AS evenement_price, e.capacite AS evenement_capacite " +
                "FROM evenement e";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        while  (resultSet.next()) {
            evenement evenement = new evenement();
            int evenementId = resultSet.getInt("evenement_id");
            evenement.setId(evenementId);
            evenement.setNom(resultSet.getString("evenement_nom"));
            evenement.setLieu(resultSet.getString("evenement_lieu"));
            evenement.setDate(resultSet.getDate("evenement_date"));
            evenement.setPrice(resultSet.getFloat("evenement_price"));
            evenement.setCapacite(resultSet.getInt("evenement_capacite"));

            evenements.add(evenement);
        }

        return evenements;
    }

    @Override
    public List<evenement> listAllEventcalender() throws SQLException {
        List<evenement> evenements = new ArrayList<>();
        String req = "SELECT e.nom AS evenement_nom, e.id AS evenement_id, e.lieu AS evenement_lieu, e.date AS evenement_date, e.price AS evenement_price, e.capacite AS evenement_capacite, " +
                "       c.name AS calender_nom " +
                "FROM evenement e " +
                "JOIN calendar c ON e.calender = c.id ";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {

            Map<Integer, evenement> evenementMap = new HashMap<>();
            while (resultSet.next()) {
                evenement evenement = new evenement();
                int evenementId = resultSet.getInt("evenement_id");
                evenement.setId(evenementId);
                evenement.setNom(resultSet.getString("evenement_nom"));
                evenement.setLieu(resultSet.getString("evenement_lieu"));
                evenement.setDate(resultSet.getDate("evenement_date"));
                evenement.setPrice(resultSet.getFloat("evenement_price"));
                evenement.setCapacite(resultSet.getInt("evenement_capacite"));
                evenement.setCalendername(resultSet.getString("calender_nom"));
                evenementMap.put(evenementId, evenement);
            }
            evenements.addAll(evenementMap.values());
        }

        return evenements;
    }


    @Override
    public List<String> listAllEventUser(int id) throws SQLException {
        List<String> names = new ArrayList<>();
        String req = "SELECT u.nom AS utilisateur_nom " +
                "FROM utilisateur u " +
                "JOIN utilisateurs_evenement ue ON u.id = ue.id_utilisateur " +
                "JOIN evenement e ON ue.id_evenement = e.id WHERE e.id="+id;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(req)) {

            while (resultSet.next()) {
                String name=resultSet.getString("utilisateur_nom");
                names.add(name);
            }
        }

        return names;
    }


    @Override
    public evenement getEventById(int id) throws SQLException {
        String req = "SELECT e.nom AS evenement_nom, e.lieu AS evenement_lieu, e.date AS evenement_date, e.price AS evenement_price, e.capacite AS evenement_capacite " +
                "FROM evenement e " +
                "WHERE e.id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        if (resultSet.next()) {
            evenement evenement = new evenement();
            evenement.setId(id);
            evenement.setNom(resultSet.getString("evenement_nom"));
            evenement.setLieu(resultSet.getString("evenement_lieu"));
            evenement.setDate(resultSet.getDate("evenement_date"));
            evenement.setPrice(resultSet.getFloat("evenement_price"));
            evenement.setCapacite(resultSet.getInt("evenement_capacite"));
            return evenement;
        } else {
            return null;
        }
    }



    @Override
    public int inscricreEvent(int idu, int idE) throws SQLException {
        String req = "INSERT INTO utilisateurs_evenement (id_evenement, id_utilisateur) " +
                "VALUES ('" + idE + "', " +
                "'" + idu + "')";
        Statement statement = connection.createStatement();
        int rows= statement.executeUpdate(req);
        if (rows > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int nombredeutulisateurparevent(int id) throws SQLException {
        String req = "SELECT COUNT(*) AS nombre_utilisateurs FROM utilisateurs_evenement ue WHERE ue.id_evenement=" + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        if (resultSet.next()) {
            return resultSet.getInt("nombre_utilisateurs");
        } else {
            return 0;
        }
    }

    @Override
    public evenement getEventBynom(String nom) throws SQLException {
        String req = "SELECT e.nom AS evenement_nom,e.id As eid ,e.lieu AS evenement_lieu, e.date AS evenement_date, e.price AS evenement_price, e.capacite AS evenement_capacite " +
                "FROM evenement e " +
                "WHERE e.nom = '" + nom+ "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(req);
        if (resultSet.next()) {
            evenement evenement = new evenement();
            evenement.setId(resultSet.getInt("eid"));
            evenement.setNom(resultSet.getString("evenement_nom"));
            evenement.setLieu(resultSet.getString("evenement_lieu"));
            evenement.setDate(resultSet.getDate("evenement_date"));
            evenement.setPrice(resultSet.getFloat("evenement_price"));
            evenement.setCapacite(resultSet.getInt("evenement_capacite"));
            return evenement;
        } else {
            return null;
        }
    }

}
