package services.oeuvreArt;

import models.Categorie;
import models.OeuvreArt;
import models.Utilisateur;
import services.categorie.CategorieService;
import services.utilisateur.UtilisateurService;
import utils.MyDataBase;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OeuvreArtService implements IOeuvreArt<OeuvreArt> {

    private Connection connection;

    public OeuvreArtService() {
        connection = MyDataBase.getInstance().getConnection();
    }


    //----------------------------------------Ajouter Oeuvre d'art------------------------------------------------------


    @Override
    public void AjouterOeuvreArt(OeuvreArt oeuvreArt) throws SQLException {
        // Vérifier si la catégorie n'est pas nulle
        if (oeuvreArt.getCategorie() == null) {
            System.out.println("La catégorie de l'oeuvre d'art est nulle. Impossible d'ajouter l'oeuvre.");
            return; // Arrêter l'exécution de la méthode
        }

        String sql = "INSERT INTO oeuvreart (image, titre, description, dateAjout, prixVente, id_categorie, status, id_artiste) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, oeuvreArt.getImage());
            preparedStatement.setString(2, oeuvreArt.getTitre());
            preparedStatement.setString(3, oeuvreArt.getDescription());
            preparedStatement.setDate(4, new java.sql.Date(oeuvreArt.getDateAjout().getTime()));
            preparedStatement.setFloat(5, oeuvreArt.getPrixVente());
            preparedStatement.setInt(6, oeuvreArt.getCategorie().getIdCategorie());
            preparedStatement.setString(7, oeuvreArt.getStatus());
            preparedStatement.setInt(8, oeuvreArt.getArtiste().getId());
            preparedStatement.executeUpdate();
        }
    }

    //--------------------------------Modifier oeuvre d'art------------------------------------------------------------

    @Override
    public void ModifierOeuvreArt(OeuvreArt oeuvreArt) throws SQLException {

        String sql = "UPDATE oeuvreart SET image = ?, titre = ?, description = ?, prixVente = ?, id_categorie = ? WHERE idOeuvreArt = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, oeuvreArt.getImage());
            preparedStatement.setString(2, oeuvreArt.getTitre());
            preparedStatement.setString(3, oeuvreArt.getDescription());
            preparedStatement.setFloat(4, oeuvreArt.getPrixVente());
            preparedStatement.setInt(5, oeuvreArt.getCategorie().getIdCategorie());
            preparedStatement.setInt(6, oeuvreArt.getId());
            preparedStatement.executeUpdate();
            System.out.println("L'oeuvre d'art a été modifiée avec succès.");
        }
    }

    //----------------------------------------------Supprimer oeuvre art------------------------------------------------------
    @Override
    public void SupprimerOeuvreArt(int id) throws SQLException {
        String sql = "DELETE FROM oeuvreart WHERE idOeuvreArt = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("L'oeuvre d'art avec l'ID " + id + " a été supprimée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'oeuvre d'art avec l'ID " + id + " : " + e.getMessage());
        }
    }


    // ----------------------------------Affichage des Oeuvres d'Art By catégorie ....................................


    @Override
    public List<OeuvreArt> getAllOeuvreArtByCategorie(String categorie) throws SQLException {
        String sql = "SELECT O.*, C.idCategorie, C.nomCategorie, U.* " +
                "FROM oeuvreart O " +
                "JOIN categorie C ON O.id_Categorie = C.idCategorie " +
                "JOIN utilisateur U ON O.id_artiste = U.id " +
                "WHERE C.nomCategorie = ?";

        List<OeuvreArt> oeuvres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categorie);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OeuvreArt oeuvre = new OeuvreArt();
                    oeuvre.setId(rs.getInt("idOeuvreArt"));
                    oeuvre.setImage(rs.getString("image"));
                    oeuvre.setTitre(rs.getString("titre"));
                    oeuvre.setDescription(rs.getString("description"));
                    oeuvre.setDateAjout(rs.getDate("dateAjout")); // Gestion de la date nulle
                    oeuvre.setPrixVente(rs.getFloat("prixVente"));
                    oeuvre.setStatus(rs.getString("status"));

                    Categorie categorieObj = new Categorie();
                    categorieObj.setIdCategorie(rs.getInt("idCategorie"));
                    categorieObj.setNomCategorie(rs.getString("nomCategorie"));
                    oeuvre.setCategorie(categorieObj);

                    // Création de l'objet Utilisateur (artiste)
                    Utilisateur artiste = new Utilisateur();
                    artiste.setId(rs.getInt("id"));
                    artiste.setNom(rs.getString("nom"));
                    artiste.setPrenom(rs.getString("prenom"));
                    // Ajout de l'artiste à l'oeuvre d'art
                    oeuvre.setArtiste(artiste);

                    oeuvres.add(oeuvre);
                }
            }
        }
        return oeuvres;
    }


    //------------------------------------------Afficher Oeuvre By Artiste---------------------------------------------------


    @Override
    public List<OeuvreArt> getAllOeuvreArtByArtistes(int idArtiste) throws SQLException {
        List<OeuvreArt> oeuvreArts = new ArrayList<>();
        String sql = "SELECT o.*, c.idCategorie, c.nomCategorie, u.nom, u.prenom " +
                "FROM oeuvreart o " +
                "JOIN utilisateur u ON o.id_Artiste = u.id " +
                "JOIN categorie c ON o.id_Categorie = c.idCategorie " +
                "WHERE u.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idArtiste);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OeuvreArt oeuvreArt = new OeuvreArt();
                    oeuvreArt.setId(rs.getInt("idOeuvreArt"));
                    oeuvreArt.setImage(rs.getString("image"));
                    oeuvreArt.setTitre(rs.getString("titre"));
                    oeuvreArt.setDescription(rs.getString("description"));
                    oeuvreArt.setDateAjout(rs.getDate("dateAjout"));
                    oeuvreArt.setPrixVente(rs.getFloat("prixVente"));
                    oeuvreArt.setStatus(rs.getString("status"));

                    // Création de l'objet Catégorie
                    Categorie categorieObj = new Categorie();
                    categorieObj.setIdCategorie(rs.getInt("idCategorie"));
                    categorieObj.setNomCategorie(rs.getString("nomCategorie"));
                    oeuvreArt.setCategorie(categorieObj);

                    // Création de l'objet Utilisateur (artiste)
                    Utilisateur artiste = new Utilisateur();
                    artiste.setNom(rs.getString("nom"));
                    artiste.setPrenom(rs.getString("prenom"));
                    oeuvreArt.setArtiste(artiste);

                    oeuvreArts.add(oeuvreArt);
                }
            }
        }
        return oeuvreArts;
    }

    //------------------------------------------------Afficher tous les Oeuvre d'art..........................................


    @Override
    public List<OeuvreArt> AfficherOeuvreArt() throws SQLException {
        List<OeuvreArt> oeuvres = new ArrayList<>();
        String sql = "SELECT o.*, c.idCategorie, c.nomCategorie, u.id as artisteId, u.nom as artisteNom, u.prenom as artistePrenom " +
                "FROM oeuvreart o " +
                "JOIN categorie c ON o.id_categorie = c.idCategorie " +
                "JOIN utilisateur u ON o.id_artiste = u.id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                OeuvreArt oeuvre = new OeuvreArt();
                oeuvre.setId(rs.getInt("idOeuvreArt"));
                oeuvre.setImage(rs.getString("image"));
                oeuvre.setTitre(rs.getString("titre"));
                oeuvre.setDescription(rs.getString("description"));
                oeuvre.setDateAjout(rs.getDate("dateAjout"));
                oeuvre.setPrixVente(rs.getFloat("prixVente"));
                oeuvre.setStatus(rs.getString("status"));

                // Création de l'objet Catégorie
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(rs.getInt("idCategorie"));
                categorie.setNomCategorie(rs.getString("nomCategorie"));
                oeuvre.setCategorie(categorie);

                // Création de l'objet Utilisateur (artiste)
                Utilisateur artiste = new Utilisateur();
                artiste.setId(rs.getInt("artisteId"));
                artiste.setNom(rs.getString("artisteNom"));
                artiste.setPrenom(rs.getString("artistePrenom"));
                oeuvre.setArtiste(artiste);

                oeuvres.add(oeuvre);
            }
        }

        return oeuvres;
    }

    //-------------------------------------Afficher Oeuvre selon By Id--------------------------------------------------------

    @Override
    public OeuvreArt AfficherOeuvreArtById(int id) throws SQLException {
        String sql = "SELECT o.*, c.idCategorie, c.nomCategorie, u.id as artisteId, u.nom as artisteNom, u.prenom as artistePrenom " +
                "FROM oeuvreart o " +
                "JOIN categorie c ON o.id_categorie = c.idCategorie " +
                "JOIN utilisateur u ON o.id_artiste = u.id " +
                "WHERE o.idOeuvreArt = ?";

        OeuvreArt oeuvre = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    oeuvre = new OeuvreArt();
                    oeuvre.setId(rs.getInt("idOeuvreArt"));
                    oeuvre.setImage(rs.getString("image"));
                    oeuvre.setTitre(rs.getString("titre"));
                    oeuvre.setDescription(rs.getString("description"));
                    oeuvre.setDateAjout(rs.getDate("dateAjout"));
                    oeuvre.setPrixVente(rs.getFloat("prixVente"));
                    oeuvre.setStatus(rs.getString("status"));

                    // Création de l'objet Catégorie
                    Categorie categorie = new Categorie();
                    categorie.setIdCategorie(rs.getInt("idCategorie"));
                    categorie.setNomCategorie(rs.getString("nomCategorie"));
                    oeuvre.setCategorie(categorie);

                    // Création de l'objet Utilisateur (artiste)
                    Utilisateur artiste = new Utilisateur();
                    artiste.setId(rs.getInt("artisteId"));
                    artiste.setNom(rs.getString("artisteNom"));
                    artiste.setPrenom(rs.getString("artistePrenom"));
                    oeuvre.setArtiste(artiste);
                }
            }
        }
        return oeuvre;
    }

    //------------------------getAllOeuvreArtByArtisteAndCategory------------------
    public List<OeuvreArt> getAllOeuvreArtByArtisteAndCategory(int idArtiste, String categorie) throws SQLException {
        List<OeuvreArt> oeuvreArts = new ArrayList<>();
        String sql = "SELECT o.*, c.idCategorie, c.nomCategorie, u.nom, u.prenom " +
                "FROM oeuvreart o " +
                "JOIN utilisateur u ON o.id_Artiste = u.id " +
                "JOIN categorie c ON o.id_Categorie = c.idCategorie " +
                "WHERE u.id = ? AND c.nomCategorie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idArtiste);
            preparedStatement.setString(2, categorie);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OeuvreArt oeuvreArt = new OeuvreArt();
                    oeuvreArt.setId(rs.getInt("idOeuvreArt"));
                    oeuvreArt.setImage(rs.getString("image"));
                    oeuvreArt.setTitre(rs.getString("titre"));
                    oeuvreArt.setDescription(rs.getString("description"));
                    oeuvreArt.setDateAjout(rs.getDate("dateAjout"));
                    oeuvreArt.setPrixVente(rs.getFloat("prixVente"));
                    oeuvreArt.setStatus(rs.getString("status"));

                    // Création de l'objet Catégorie
                    Categorie categorieObj = new Categorie();
                    categorieObj.setIdCategorie(rs.getInt("idCategorie"));
                    categorieObj.setNomCategorie(rs.getString("nomCategorie"));
                    oeuvreArt.setCategorie(categorieObj);

                    // Création de l'objet Utilisateur (artiste)
                    Utilisateur artiste = new Utilisateur();
                    artiste.setNom(rs.getString("nom"));
                    artiste.setPrenom(rs.getString("prenom"));
                    oeuvreArt.setArtiste(artiste);

                    oeuvreArts.add(oeuvreArt);
                }
            }
        }
        return oeuvreArts;
    }


    //-----------------------------Calculer nombre des oeuvre d'art total-------------------------------------------------------

    public int nombreOeuvresArt() throws SQLException {
        int nombreOeuvres = 0;
        String sql = "SELECT COUNT(*) AS count FROM oeuvreart";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                nombreOeuvres = resultSet.getInt("count");
            }
        }
        return nombreOeuvres;
    }

    //------------------------------calculer nombre oeuvre d'art selon une catégorie-----------------------------------
    public int nombreOeuvresArtParCategorie(String categorie) throws SQLException {
        int nombreOeuvres = 0;
        String sql = "SELECT COUNT(*) AS count FROM oeuvreart o JOIN categorie c ON o.id_categorie = c.idCategorie WHERE c.nomCategorie = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categorie);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    nombreOeuvres = resultSet.getInt("count");
                }
            }
        }
        return nombreOeuvres;
    }


    //---------------------------------Calculer nombre Oeuvre d'art selon Artiste--------------------------------------


    public int nombreOeuvresArtParArtiste(int idArtiste) throws SQLException {
        int nombreOeuvres = 0;
        String sql = "SELECT COUNT(*) AS nombre FROM oeuvreart WHERE id_Artiste = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idArtiste);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    nombreOeuvres = rs.getInt("nombre");
                }
            }
        }
        return nombreOeuvres;
    }
           /*************list oeuvres (mariem)******************************/

           public List<OeuvreArt> getOeuvres() {
               List<OeuvreArt> oeuvres = new ArrayList<>();

               try {
                   String query = "SELECT * FROM oeuvreart";
                   Statement statement = connection.createStatement();
                   ResultSet resultSet = statement.executeQuery(query);

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
                       Categorie categorie = getCategorieById(categorieId);
                       Utilisateur artiste = getArtisteById(artisteId);

                       OeuvreArt oeuvre = new OeuvreArt(id, image, titre, description, dateAjout, prixVente, categorie, status, artiste);
                       oeuvres.add(oeuvre);
                   }

               } catch (SQLException e) {
                   e.printStackTrace();
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
/******************************************/



    //------------------------------------------------------------------------------

    public Map<String, Integer> nombreOeuvresArtParToutesCategories() throws SQLException {
        Map<String, Integer> nombreOeuvresParCategorie = new HashMap<>();
        String sql = "SELECT nomCategorie, COUNT(*) AS count FROM oeuvreart JOIN categorie ON oeuvreart.id_categorie = categorie.idCategorie GROUP BY nomCategorie";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String categorie = resultSet.getString("nomCategorie");
                int nombreOeuvres = resultSet.getInt("count");
                nombreOeuvresParCategorie.put(categorie, nombreOeuvres);
            }
        }
        return nombreOeuvresParCategorie;
    }

    public List<OeuvreArt> getOeuvresByTitre(String titre) throws SQLException {
        List<OeuvreArt> oeuvres = new ArrayList<>();
        String sql = "SELECT * FROM oeuvreart WHERE titre = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, titre);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OeuvreArt oeuvre = new OeuvreArt();
                    oeuvre.setId(rs.getInt("idOeuvreArt"));
                    oeuvre.setImage(rs.getString("image"));
                    oeuvre.setTitre(rs.getString("titre"));
                    oeuvre.setDescription(rs.getString("description"));

                    // Vérifiez si la date de l'ajout est nulle
                    Date dateAjout = rs.getDate("dateAjout");
                    if (dateAjout != null) {
                        oeuvre.setDateAjout(dateAjout);
                    } else {

                        oeuvre.setDateAjout(null); // ou attribuez une valeur par défaut, par exemple : oeuvre.setDateAjout(new Date(System.currentTimeMillis()));
                    }

                    oeuvre.setPrixVente(rs.getFloat("prixVente"));
                    oeuvre.setStatus(rs.getString("status"));

                    // Obtenez la catégorie de l'œuvre d'art
                    CategorieService categorieService = new CategorieService();
                    Categorie categorie = categorieService.getCategorieById(rs.getInt("id_categorie"));
                    oeuvre.setCategorie(categorie);

                    // Obtenez l'artiste de l'œuvre d'art
                    UtilisateurService utilisateurService = new UtilisateurService();
                    Utilisateur artiste = utilisateurService.getUtilisateurById(rs.getInt("id_artiste"));
                    oeuvre.setArtiste(artiste);

                    oeuvres.add(oeuvre);
                }
            }
        }
        return oeuvres;
    }



    /*************list oeuvres (mariem)******************************/

    public List<OeuvreArt> getOeuvres() {
        List<OeuvreArt> oeuvres = new ArrayList<>();

        try {
            String query = "SELECT * FROM oeuvreart";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

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
                Categorie categorie = getCategorieById(categorieId);
                Utilisateur artiste = getArtisteById(artisteId);

                OeuvreArt oeuvre = new OeuvreArt(id, image, titre, description, dateAjout, prixVente, categorie, status, artiste);
                oeuvres.add(oeuvre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
/******************************************/


}





















