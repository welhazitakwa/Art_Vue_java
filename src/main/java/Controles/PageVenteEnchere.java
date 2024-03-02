package Controles;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.StringConverter;
import models.Exposition;
import models.VenteEncheres;
import services.Exposition.ExpositionService;
import services.venteencheres.VenteEncheresService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PageVenteEnchere implements Initializable {
    @FXML
    public ComboBox<Exposition> expositionComboBox;
    @FXML
    private TableView<VenteEncheres> venteEnchere_tableView;

    @FXML
    private TableColumn<VenteEncheres,Integer> idV_tableV;

    @FXML
    private TextField idV_textFile;

    @FXML
    private TableColumn<VenteEncheres, Date> DateDebutV_tableV;

    @FXML
    private DatePicker DateDebutV_textFile;
    @FXML
    private TableColumn<VenteEncheres,Date> DateFinV_tableV;

    @FXML
    private DatePicker DateFinV_textFile;
    @FXML
    private TableColumn<VenteEncheres, Float> PrixDepart_tableV;

    @FXML
    private TextField prixDepartV_textFile;
    @FXML
    private TableColumn<VenteEncheres, String> Statue_tableV;
    @FXML
    private TableColumn<VenteEncheres, Void> actionsColumn;

    @FXML
    private TextField StatueV_textFile;
    private VenteEncheresService venteEncheresService;
    private int idArtiste;
    @FXML
    private BorderPane contentArea;
    @FXML
    private ComboBox<String> comboBoxOptions;
    private ExpositionService expositionService;

    public void setParametre(int idArtiste) { // Modifier le type du paramètre
        this.idArtiste = idArtiste;
        System.out.println("ID de l'artiste dans page Acceuil : " + idArtiste);

    }
    public void ajouter_exposition(ActionEvent actionEvent) throws SQLException {
        // Vérifier que tous les champs sont remplis
        if (    DateDebutV_textFile.getValue() == null ||
                DateFinV_textFile.getValue() == null ||
                prixDepartV_textFile.getText().isEmpty() ||
                expositionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Veuillez remplir tous les champs.");
            alert.show();
            return; // Arrêter le traitement car un champ est vide
        }
        VenteEncheresService cs = new VenteEncheresService();
        LocalDate dateDebut = DateDebutV_textFile.getValue();
        LocalDate dateFin = DateFinV_textFile.getValue();
// Convertir en java.sql.Date si nécessaire
        Date dateDebutSQL = Date.valueOf(dateDebut);
        Date dateFinSQL = Date.valueOf(dateFin);

        cs.AjouterVenteEncheres(new VenteEncheres(
                -1,
                dateDebutSQL,
                dateFinSQL,
                Float.parseFloat(prixDepartV_textFile.getText()),
                "disponible",
                expositionComboBox.getValue().getId()
        ));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("VenteEncheres ajoutée");
        alert.setContentText("VenteEncheres ajoutée !");
        alert.show();

// Mettre à jour les cellules de la table
        DateDebutV_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,java.sql.Date>("DateDebut"));
        DateFinV_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,java.sql.Date>("DateFin"));
        PrixDepart_tableV.setCellValueFactory(new PropertyValueFactory<VenteEncheres,Float>("PrixDepart"));

// Charger les données dans la table en fonction de l'exposition sélectionnée
        Exposition expositionSelectionnee = expositionComboBox.getValue();
        if (expositionSelectionnee != null) {
            int idExpositionSelectionnee = expositionSelectionnee.getId();
            chargerDonnees(idExpositionSelectionnee);
        }

    }
    @FXML
    public void initialize(URL location, ResourceBundle resources){
        System.out.println("Méthode initialize appelée.");
        venteEncheresService = new VenteEncheresService();
        initialiserTableView();
        Exposition expositionSelectionnee = expositionComboBox.getValue();
        if (expositionSelectionnee != null) {
            int idExpositionSelectionnee = expositionSelectionnee.getId();
            chargerDonnees(idExpositionSelectionnee);
        }
        expositionService = new ExpositionService();
        comboboxexpo();
    }

    public void comboboxexpo(){
        List<Exposition> expositions = null;
        try {
            expositions = expositionService.AfficherExposition();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        expositionComboBox.setItems(FXCollections.observableArrayList(expositions));
        // Définir un convertisseur pour le ComboBox
        expositionComboBox.setConverter(new StringConverter<Exposition>() {
            @Override
            public String toString(Exposition exposition) {
                if (exposition == null) {
                    return "";
                }
                return exposition.getId() + " - " + exposition.getNom();
            }

            @Override
            public Exposition fromString(String string) {
                return null; // Vous n'avez pas besoin de cela pour un ComboBox non éditable
            }
        });

        // Définir la cellule de rendu personnalisée
        expositionComboBox.setCellFactory(param -> new ListCell<Exposition>() {
            @Override
            protected void updateItem(Exposition exposition, boolean empty) {
                super.updateItem(exposition, empty);
                if (empty || exposition == null) {
                    setText(null);
                } else {
                    setText(exposition.getId() + " - " + exposition.getNom());
                }
            }
        });

        expositionComboBox.setItems(FXCollections.observableArrayList(expositions));
    }

    public void modifier_exposition(ActionEvent actionEvent) {
    }



    private void initialiserTableView() {
        idV_tableV.setCellValueFactory(new PropertyValueFactory<>("id"));
        DateDebutV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        DateFinV_tableV.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        PrixDepart_tableV.setCellValueFactory(new PropertyValueFactory<>("prixDepart"));
        Statue_tableV.setCellValueFactory(new PropertyValueFactory<>("statue"));
        actionsColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(null)); // Adjusted to return an ObservableValue<java.lang.Void>
        actionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button editButton = new Button("Modifier");

            {
                // Définir les actions des boutons
                deleteButton.setOnAction(event -> {
                    VenteEncheres venteEnchere = getTableView().getItems().get(getIndex());
                    try {
                        venteEncheresService.SupprimerVenteEncheres(venteEnchere.getId()); // Appel de la méthode supprimerVenteEnchere avec la VenteEnchere à supprimer
                        Exposition expositionSelectionnee = expositionComboBox.getValue();
                        if (expositionSelectionnee != null) {
                            int idExpositionSelectionnee = expositionSelectionnee.getId();
                            chargerDonnees(idExpositionSelectionnee);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // Ajoutez ici le code pour supprimer la VenteEnchere
                    System.out.println("Supprimer VenteEnchere : " + venteEnchere);
                });

                editButton.setOnAction(event -> {
                    VenteEncheres venteEnchere = getTableView().getItems().get(getIndex());
                    try {
                        // Charger la nouvelle fenêtre FXML de modification
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/ModificationController.fxml"));
                        Parent root = loader.load();

                        // Récupérer le contrôleur de la fenêtre de modification
                        ModificationController controller = loader.getController();

                        // Passer la vente aux enchères sélectionnée au contrôleur de modification
                        controller.setVenteEnchere(venteEnchere);

                        // Afficher la fenêtre de modification
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // Ajoutez ici le code pour modifier la VenteEnchere
                    System.out.println("Modifier VenteEnchere : " + venteEnchere);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(deleteButton, editButton);
                    buttons.setSpacing(5);
                    setGraphic(buttons);
                }
            }
        });


        // Ajouter la colonne des actions à la TableView
        venteEnchere_tableView.getColumns().add(actionsColumn);
    }

    private void chargerDonnees(int idExposition) {


       /* try {
            if (venteEncheresService != null) { // Vérifier si categorieService est initialisé
                List<VenteEncheres> venteEncheres = venteEncheresService.AfficherVenteEncheres();
                ObservableList<VenteEncheres> venteEncheresObservableList = FXCollections.observableArrayList(venteEncheres);
                venteEnchere_tableView.setItems(venteEncheresObservableList);
                System.out.println("VenteEncheres affichées avec succès : " + venteEncheres.size());
            } else {
                System.err.println("VenteEncheresService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        try {
            if (venteEncheresService != null) { // Vérifier si venteEncheresService est initialisé
                List<VenteEncheres> venteEncheres = venteEncheresService.AfficherVenteEncheresParExposition(idExposition);
                ObservableList<VenteEncheres> venteEncheresObservableList = FXCollections.observableArrayList(venteEncheres);
                venteEnchere_tableView.setItems(venteEncheresObservableList);
                System.out.println("VenteEncheres affichées avec succès : " + venteEncheres.size());
            } else {
                System.err.println("VenteEncheresService n'est pas initialisé.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleExpositionSelection(ActionEvent actionEvent) {
        Exposition expositionSelectionnee = expositionComboBox.getValue();
        if (expositionSelectionnee != null) {
            int idExpositionSelectionnee = expositionSelectionnee.getId();
            chargerDonnees(idExpositionSelectionnee);
        }

    }

    public void To_Accueil(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AcceuilArtiste.fxml"));
            Parent newPage = loader.load();
            AcceuilArtiste acceuilArtiste = loader.getController();
            acceuilArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void To_Apropos(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/AproposArtiste.fxml"));
            Parent newPage = loader.load();
            AproposArtiste aproposArtiste = loader.getController();
            aproposArtiste.setParametre(idArtiste);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void To_Oeuvre_Art(ActionEvent actionEvent) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlArtiste/OeuvrePageArtiste.fxml"));
            Parent newPage = loader.load();
            OeuvresPageArtiste oeuvrePageArtiste = loader.getController();
            oeuvrePageArtiste.setParametre(idArtiste); // Passage de l'ID de l'artiste à la page OeuvrePageArtiste

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(newPage);
            stage.setScene(scene);
        } catch (IOException ex) {
            Logger.getLogger(Acceuil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void traiterSelectionComboBox(ActionEvent actionEvent) {
        if (comboBoxOptions != null && comboBoxOptions.getValue() != null) {
            String optionSelectionnee = (String) comboBoxOptions.getValue();
            if ("Déconnexion".equals(optionSelectionnee)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
                    Parent registerParent = loader.load();
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(registerParent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if ("Modifier le profil".equals(optionSelectionnee)) {
//
                Stage detailsSatge = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                double X = detailsSatge.getX();
                double Y = detailsSatge.getY();
                try{
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/fxml/modifierCompteClient.fxml"));
                    Parent root2 = loader2.load() ;
                    ScaleTransition st = new ScaleTransition(Duration.millis(50),root2);
                    st.setInterpolator(Interpolator.EASE_BOTH);
                    st.setFromX(0);
                    st.setFromY(0);
                    st.setToX(1);
                    st.setToY(1);
                    Stage stage = new Stage() ;
                    Scene scene= new Scene(root2);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    stage.setX(X + 200);
                    stage.setY(Y + 50);

                    ModifierCompteClient modifierProfil = loader2.getController();
                    modifierProfil.setParametre(String.valueOf(idArtiste));
                    modifierProfil.initialize(null,null);
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }



            else if ("Consulter mon compte".equals(optionSelectionnee)) {
                /***********************************************************************/
                Stage detailsSatge = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                double X = detailsSatge.getX();
                double Y = detailsSatge.getY();
                try{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userDetails.fxml"));
                    Parent root = loader.load() ;

                    ScaleTransition st = new ScaleTransition(Duration.millis(50),root);
                    st.setInterpolator(Interpolator.EASE_BOTH);
                    st.setFromX(0);
                    st.setFromY(0);
                    st.setToX(1);
                    st.setToY(1);

                    Stage stage = new Stage() ;
                    Scene scene= new Scene(root);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    stage.setX(X + 350);
                    stage.setY(Y + 150);
                    UserDetails udcontroller = loader.getController();
                    udcontroller.setParametre(String.valueOf(idArtiste));
                    udcontroller.initialize(null,null);
                } catch (Exception e) {
                    e.printStackTrace();

                }
                /***********************************************************************/
            }
        } else {
            System.out.println("Le ComboBox n'est pas correctement initialisé ou aucune valeur n'est sélectionnée.");
        }
    }
}
