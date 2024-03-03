package Controles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.*;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import services.Panier.PanierService;
import services.panieroeuvre.PanieroeuvreService;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextInputDialog;
import services.Commande.CommandeService;
import org.apache.commons.mail.EmailException;
import services.utilisateur.UtilisateurService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PagePanier implements Initializable {
    @FXML
    private ListView<OeuvreArt> listeOeuvresPanier;

    private PanieroeuvreService panieroeuvreService;
    @FXML
    private Label montantTotalLabel;

    @FXML
    private Button BtnCommande;
    @FXML
    private Button CommandePageBtn;
    @FXML
    private Button trierParPrixButton;
    private CommandeService commandeService;
    private PanierService panierService = new PanierService();
    private UtilisateurService utilisateurService = new UtilisateurService();
    private int idClient;

    public void setParametre(int idClient) {
        this.idClient = idClient;
        System.out.println("ID de l'client connecté page panier : " + idClient);
        chargerOeuvresDuPanier();
        afficherMontantTotal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panieroeuvreService = new PanieroeuvreService();
        commandeService = new CommandeService();
        trierParPrixButton.setOnAction(this::trierParPrix);
        chargerOeuvresDuPanier();
        afficherMontantTotal();
        CommandePageBtn.setVisible(false);
        // Utiliser un Callback pour définir le rendu personnalisé des cellules de la ListView
        listeOeuvresPanier.setCellFactory(new Callback<ListView<OeuvreArt>, ListCell<OeuvreArt>>() {
            @Override
            public ListCell<OeuvreArt> call(ListView<OeuvreArt> param) {
                return new ListCell<OeuvreArt>() {
                    private final ImageView imageView = new ImageView();
                    private final Button deleteButton = new Button("Supprimer");
                    private final Button modifyButton = new Button("Modifier");


                    {
                        deleteButton.setOnAction(event -> {
                            OeuvreArt oeuvre = getItem();
                            if (oeuvre != null) {
                                try {
                                    int idPanier = panierService.getPanierIdByClientId(idClient);
                                    // Supprimer l'oeuvre du panier
                                    panieroeuvreService.supprimerOeuvreDuPanier(idPanier, oeuvre.getId());
                                    // Supprimer l'oeuvre de la liste affichée
                                    getListView().getItems().remove(oeuvre);
                                    afficherMontantTotal();

                                } catch (SQLException e) {
                                    Logger.getLogger(PagePanier.class.getName()).log(Level.SEVERE, null, e);
                                }
                            }
                        });
                        modifyButton.setOnAction(event -> {
                            OeuvreArt oeuvre = getItem();
                            if (oeuvre != null) {
                                showModifyQuantityDialog(oeuvre);
                            }
                        });

                    }


                    @Override
                    protected void updateItem(OeuvreArt oeuvreArt, boolean empty) {
                        super.updateItem(oeuvreArt, empty);

                        if (empty || oeuvreArt == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            // Charger l'image à partir du chemin et l'afficher dans l'ImageView
                            Image image = new Image(oeuvreArt.getImage());
                            imageView.setImage(image);
                            imageView.setFitWidth(200); // Définir la largeur de l'image
                            imageView.setFitHeight(200); // Définir la hauteur de l'image

                            // Afficher le titre de l'œuvre sous l'image
                            VBox imageBox = new VBox(imageView);
                            VBox.setMargin(imageView, new Insets(0, 50, 0, 0));

                            // Afficher le prix de l'œuvre
                            Label prixLabel = new Label("Prix : " + oeuvreArt.getPrixVente() + " DT");

                            // Créer un VBox pour le prix
                            VBox prixBox = new VBox(prixLabel);
                            prixBox.setSpacing(5); // Espacement vertical entre le prix et les autres éléments

                            // Créer un VBox pour le bouton de suppression
                            VBox buttonBox = new VBox(deleteButton, new Region(), modifyButton);
                            buttonBox.setSpacing(10); // Espacement vertical entre les boutons
                            setText(oeuvreArt.getTitre());

                            // Définir l'image, le prix et le bouton de suppression comme contenu graphique de la cellule
                            setGraphic(new HBox(imageBox, prixBox, buttonBox));
                        }
                    }


                };
            }
        });
    }

    private void chargerOeuvresDuPanier() {
        try {
            int idPanier = panierService.getPanierIdByClientId(idClient);
            List<OeuvreArt> oeuvresDansPanier = panieroeuvreService.getOeuvresDuPanier(idPanier);
            ObservableList<OeuvreArt> observableList = FXCollections.observableArrayList(oeuvresDansPanier);
            listeOeuvresPanier.setItems(observableList);
            System.out.println("affichage");
            afficherMontantTotal();
        } catch (SQLException e) {
            Logger.getLogger(PagePanier.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    private void showModifyQuantityDialog(OeuvreArt oeuvre) {

        // Créer une boîte de dialogue pour modifier la quantité
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier la quantité");
        dialog.setHeaderText(null);
        dialog.setContentText("Nouvelle quantité:");

        // Afficher la boîte de dialogue et attendre la réponse du client
        Optional<String> result = dialog.showAndWait();
        int idPanier = panierService.getPanierIdByClientId(idClient);
        // Vérifier si le client a appuyé sur OK et récupérer la nouvelle quantité saisie
        result.ifPresent(newQuantity -> {
            try {
                int newQuantityInt = Integer.parseInt(newQuantity);
                // Récupérer l'identifiant de l'association Panieroeuvre associé à l'oeuvre
                int associationId = panieroeuvreService.getIdAssociationPanierOeuvre(idPanier, oeuvre.getId());
                // Modifier la quantité dans la classe d'association Panieroeuvre
                panieroeuvreService.modifierQuantiteOeuvreDansPanier(associationId, newQuantityInt);
                // Mettre à jour l'affichage
                chargerOeuvresDuPanier();
                afficherMontantTotal();

            } catch (NumberFormatException | SQLException e) {
                e.printStackTrace(); // Gérer les erreurs de conversion ou d'accès à la base de données
            }
        });
    }

    private void afficherMontantTotal() {

        try {
            int idPanier = panierService.getPanierIdByClientId(idClient);// Remplacez 19 par l'ID du panier actuel
            float montantTotal = panieroeuvreService.calculerMontantTotal(idPanier);
            montantTotalLabel.setText(String.format("%.2f DT", montantTotal));
            if (montantTotal == 0) {
                BtnCommande.setVisible(false);
            } else {
                BtnCommande.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérer les erreurs de récupération du montant total
        }
    }


    @FXML
    void To_Oeuvre_Art(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/PageOeuvre.fxml"));
            Parent registerParent = loader.load();
            PageOeuvre pageOeuvre = loader.getController();
            pageOeuvre.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void To_Accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Acceuil.fxml"));
            Parent registerParent = loader.load();
            Acceuil acceuil = loader.getController();
            acceuil.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void To_Apropos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/Apropos.fxml"));
            Parent registerParent = loader.load();
            Apropos apropos = loader.getController();
            apropos.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML

    public void ajouter_commande(ActionEvent actionEvent) {
        try {
            int idPanier = panierService.getPanierIdByClientId(idClient);

            Commande nouvelleCommande = new Commande();
            nouvelleCommande.setDate(new Date());
            Panier panier = panierService.getPanierById(idPanier);
            commandeService.creerCommande(nouvelleCommande, panier);
            // Envoyer un e-mail de confirmation de commande
            Utilisateur client = utilisateurService.getUtilisateurById(idClient);

            // Récupérer l'adresse e-mail du client
            String recipientEmail = client.getEmail();
            String subject = "Confirmation de commande";
            String message = "Votre commande a été créée avec succès et en cours de traitement .";

            sendEmail(recipientEmail, subject, message);
            System.out.println("envoyé avec succes");

            // Afficher une alerte de succès si la commande est créée avec succès
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succès");
            alert.setHeaderText(null);
            alert.setContentText("La commande a été créée avec succès.");
            alert.showAndWait();


            // Cacher le bouton "Commander"
            BtnCommande.setVisible(false);

            // Afficher le bouton "Consulter commande"
            CommandePageBtn.setVisible(true);
            // Vider le panier
            panieroeuvreService.viderPanier(idPanier);

        } catch (SQLException e) {
            // En cas d'erreur, afficher une alerte d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la création de la commande.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    void To_Commande_Page(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/fxmlClient/CommandePage.fxml"));
            Parent registerParent = loader.load();
            CommandePage commandePage = loader.getController();
            commandePage.setParametre(idClient);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(registerParent));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void trierParPrix(ActionEvent event) {
        List<OeuvreArt> oeuvres = listeOeuvresPanier.getItems();
        oeuvres.sort(Comparator.comparingDouble(OeuvreArt::getPrixVente));
        listeOeuvresPanier.setItems(FXCollections.observableArrayList(oeuvres));
        listeOeuvresPanier.refresh();
        System.out.println("tri fait");
    }
    // Méthode pour envoyer l'e-mail
    public void sendEmail(String recipientEmail, String subject, String message) {
        try {

            Email email = new HtmlEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(587); // Port SMTP
            email.setStartTLSEnabled(true); // Utiliser STARTTLS
            email.setAuthenticator(new DefaultAuthenticator("oumeyma.benkram@esprit.tn", "211JFT4900"));
            //email.setSSLOnConnect(true); // Utiliser SSL
            email.setFrom("oumeyma.benkram@esprit.tn");
            email.setSubject(subject);
            email.setMsg(message);
            email.addTo(recipientEmail);
            email.send();
            System.out.println("Email sent successfully.");
        } catch (EmailException e) {
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

}


