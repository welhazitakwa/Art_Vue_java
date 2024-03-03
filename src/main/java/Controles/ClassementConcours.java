
package Controles;



import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;
import java.io.File;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
public class ClassementConcours {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox classementVBox;

    public void initialize() {
        // Initialisation, si nécessaire
        afficherClassement();
    }

  /*  private void afficherClassement() {
      /*  // Récupérez la liste de tous les concours
        ConcoursService concoursService = new ConcoursService();
        List<Concours> concoursList = concoursService.getConcoursList();

        // Créez un VBox pour contenir les TitledPanes
        VBox concoursVBox = new VBox();
        concoursVBox.setSpacing(10); // Ajustez l'espacement entre les TitledPanes

        // Parcourez la liste des concours
        for (Concours concours : concoursList) {
            // Créez un TitledPane pour chaque concours
            TitledPane concoursTitledPane = new TitledPane();
            Button envoyerEmailButton = new Button("Envoyer un e-mail aux gagnants");
            envoyerEmailButton.setOnAction(event -> envoyerEmailAuxGagnants(concours));
            // Create a button for sending SMS
            Button envoyerSMSButton = new Button("Envoyer un SMS aux gagnants");
            envoyerSMSButton.setOnAction(event -> envoyerSMSAuxGagnants(concours));

            // Add the SMS button to the TitledPane
            VBox buttonContainer = new VBox(envoyerEmailButton, envoyerSMSButton);
            concoursTitledPane.setGraphic(buttonContainer);
            concoursTitledPane.setGraphic(envoyerEmailButton);

            concoursTitledPane.setText(concours.getTitre());



            // Créez un TilePane pour les œuvres
            TilePane tilePane = new TilePane();
            tilePane.setHgap(100); // Espacement horizontal entre les œuvres
            tilePane.setVgap(100); // Espacement vertical entre les œuvres

            // Récupérez la liste des œuvres du classement pour ce concours
            OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
            List<OeuvreArt> classement = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

            // Ajoutez les éléments graphiques à TilePane
            for (int i = 0; i < classement.size(); i++) {
                OeuvreArt oeuvre = classement.get(i);

                // Créez un conteneur pour chaque œuvre (VBox pour le titre, l'artiste, le rang et l'image)
                VBox oeuvreContainer = new VBox();
                oeuvreContainer.setAlignment(Pos.CENTER); // Ajustez l'alignement selon vos besoins

                // Ajoutez le rang de l'œuvre à VBox
                Label rangLabel = new Label("Rang : " + (i + 1)); // +1 car les indices commencent à 0
                rangLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(rangLabel);

                // Ajoutez le titre de l'œuvre à VBox
                Label titreLabel = new Label("Titre : " + oeuvre.getTitre());
                titreLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(titreLabel);

                // Ajoutez l'artiste de l'œuvre à VBox
                Label artisteLabel = new Label("Artiste : " + oeuvre.getArtiste());
                artisteLabel.setStyle("-fx-font-size: 14;-fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(artisteLabel);


                // Taille fixe pour les ImageView
                double imageViewSize = 200;
                ImageView imageView = new ImageView(new Image(new File(oeuvre.getImage()).toURI().toString()));
                imageView.setFitWidth(imageViewSize); // Ajustez la largeur de l'image
                imageView.setFitHeight(imageViewSize); // Ajustez la hauteur de l'image
                StackPane imageStackPane = new StackPane(imageView);
                imageStackPane.setAlignment(Pos.CENTER);
                oeuvreContainer.getChildren().add(imageStackPane);

                // Ajoutez le conteneur d'œuvre à TilePane
                tilePane.getChildren().add(oeuvreContainer);
            }

            // Ajoutez TilePane au TitledPane
            concoursTitledPane.setContent(tilePane);

            // Ajoutez le TitledPane à la VBox principale
            concoursVBox.getChildren().add(concoursTitledPane);
        }

        // Ajoutez la VBox principale à la ScrollPane
        scrollPane.setContent(concoursVBox);*/

        // Récupérez la liste de tous les concours
       /* ConcoursService concoursService = new ConcoursService();
        List<Concours> concoursList = concoursService.getConcoursList();

        // Créez un VBox pour contenir les TitledPanes
        VBox concoursVBox = new VBox();
        concoursVBox.setSpacing(10); // Ajustez l'espacement entre les TitledPanes

        // Parcourez la liste des concours
        for (Concours concours : concoursList) {
            // Créez un TitledPane pour chaque concours
            TitledPane concoursTitledPane = new TitledPane();

            // Create buttons for sending email and SMS
            Button envoyerEmailButton = new Button("Envoyer un e-mail aux gagnants");
            envoyerEmailButton.setOnAction(event -> envoyerEmailAuxGagnants(concours));

            Button envoyerSMSButton = new Button("Envoyer un SMS aux gagnants");
            envoyerSMSButton.setOnAction(event -> envoyerSMSAuxGagnants(concours));

            // Create a VBox to hold both buttons
            VBox buttonContainer = new VBox(envoyerEmailButton, envoyerSMSButton);
            buttonContainer.setAlignment(Pos.CENTER);

            // Set the VBox as the graphic for the TitledPane
            concoursTitledPane.setGraphic(buttonContainer);

            concoursTitledPane.setText(concours.getTitre());

            // Créez un TilePane pour les œuvres
            TilePane tilePane = new TilePane();
            tilePane.setHgap(100); // Espacement horizontal entre les œuvres
            tilePane.setVgap(100); // Espacement vertical entre les œuvres

            // Récupérez la liste des œuvres du classement pour ce concours
            OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
            List<OeuvreArt> classement = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

            // Ajoutez les éléments graphiques à TilePane
            for (int i = 0; i < classement.size(); i++) {
                OeuvreArt oeuvre = classement.get(i);

                // Créez un conteneur pour chaque œuvre (VBox pour le titre, l'artiste, le rang et l'image)
                VBox oeuvreContainer = new VBox();
                oeuvreContainer.setAlignment(Pos.CENTER); // Ajustez l'alignement selon vos besoins

                // Ajoutez le rang de l'œuvre à VBox
                Label rangLabel = new Label("Rang : " + (i + 1)); // +1 car les indices commencent à 0
                rangLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(rangLabel);

                // Ajoutez le titre de l'œuvre à VBox
                Label titreLabel = new Label("Titre : " + oeuvre.getTitre());
                titreLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(titreLabel);

                // Ajoutez l'artiste de l'œuvre à VBox
                Label artisteLabel = new Label("Artiste : " + oeuvre.getArtiste());
                artisteLabel.setStyle("-fx-font-size: 14;-fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                oeuvreContainer.getChildren().add(artisteLabel);


                // Taille fixe pour les ImageView
                double imageViewSize = 200;
                ImageView imageView = new ImageView(new Image(new File(oeuvre.getImage()).toURI().toString()));
                imageView.setFitWidth(imageViewSize); // Ajustez la largeur de l'image
                imageView.setFitHeight(imageViewSize); // Ajustez la hauteur de l'image
                StackPane imageStackPane = new StackPane(imageView);
                imageStackPane.setAlignment(Pos.CENTER);
                oeuvreContainer.getChildren().add(imageStackPane);

                // Ajoutez le conteneur d'œuvre à TilePane
                tilePane.getChildren().add(oeuvreContainer);
            }

            // Ajoutez TilePane au TitledPane
            concoursTitledPane.setContent(tilePane);

            // Ajoutez le TitledPane à la VBox principale
            concoursVBox.getChildren().add(concoursTitledPane);
        }

        // Ajoutez la VBox principale à la ScrollPane
        scrollPane.setContent(concoursVBox);



        }*/
        private void afficherClassement() {
            // Récupérez la liste de tous les concours
            ConcoursService concoursService = new ConcoursService();
            List<Concours> concoursList = concoursService.getConcoursList();

            // Créez un VBox pour contenir les TitledPanes
            VBox concoursVBox = new VBox();
            concoursVBox.setSpacing(10); // Ajustez l'espacement entre les TitledPanes

            // Parcourez la liste des concours
            for (Concours concours : concoursList) {
                // Créez un TitledPane pour chaque concours
                TitledPane concoursTitledPane = new TitledPane();

                // Create buttons for sending email and SMS
                Button envoyerEmailButton = new Button("Envoyer un e-mail aux gagnants");
                envoyerEmailButton.setOnAction(event -> envoyerEmailAuxGagnants(concours));

                Button envoyerSMSButton = new Button("Envoyer un SMS aux gagnants");
                envoyerSMSButton.setOnAction(event -> envoyerSMSAuxGagnants(concours));

                // Create an HBox to hold both buttons
                HBox buttonsContainer = new HBox(envoyerEmailButton, envoyerSMSButton);
                buttonsContainer.setAlignment(Pos.CENTER);

                VBox contentVBox = new VBox();
                Label titleLabel = new Label("Titre du concours : " + concours.getTitre());
                titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill:#A4685B;");
                contentVBox.getChildren().addAll(
                        titleLabel,
                        buttonsContainer
                );

                // Set the VBox as the graphic for the TitledPane
                concoursTitledPane.setGraphic(contentVBox);

                // Set an empty string as the text for the TitledPane
                concoursTitledPane.setText("");

                // Créez un TilePane pour les œuvres
                TilePane tilePane = new TilePane();
                tilePane.setHgap(100); // Espacement horizontal entre les œuvres
                tilePane.setVgap(100); // Espacement vertical entre les œuvres

                // Récupérez la liste des œuvres du classement pour ce concours
                OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
                List<OeuvreArt> classement = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

                // Ajoutez les éléments graphiques à TilePane
                for (int i = 0; i < classement.size(); i++) {
                    OeuvreArt oeuvre = classement.get(i);

                    // Créez un conteneur pour chaque œuvre (VBox pour le titre, l'artiste, le rang et l'image)
                    VBox oeuvreContainer = new VBox();
                    oeuvreContainer.setAlignment(Pos.CENTER); // Ajustez l'alignement selon vos besoins

                    // Ajoutez le rang de l'œuvre à VBox
                    Label rangLabel = new Label("Rang : " + (i + 1)); // +1 car les indices commencent à 0
                    rangLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                    oeuvreContainer.getChildren().add(rangLabel);

                    // Ajoutez le titre de l'œuvre à VBox
                    Label titreLabel = new Label("Titre : " + oeuvre.getTitre());
                    titreLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                    oeuvreContainer.getChildren().add(titreLabel);

                    // Ajoutez l'artiste de l'œuvre à VBox
                    Label artisteLabel = new Label("Artiste : " + oeuvre.getArtiste());
                    artisteLabel.setStyle("-fx-font-size: 14;-fx-font-weight: bold;"); // Ajustez la police et la taille du texte
                    oeuvreContainer.getChildren().add(artisteLabel);

                    // Taille fixe pour les ImageView
                    double imageViewSize = 200;
                    ImageView imageView = new ImageView(new Image(oeuvre.getImage()));
                    imageView.setFitWidth(imageViewSize); // Ajustez la largeur de l'image
                    imageView.setFitHeight(imageViewSize); // Ajustez la hauteur de l'image
                    StackPane imageStackPane = new StackPane(imageView);
                    imageStackPane.setAlignment(Pos.CENTER);
                    oeuvreContainer.getChildren().add(imageStackPane);

                    // Ajoutez le conteneur d'œuvre à TilePane
                    tilePane.getChildren().add(oeuvreContainer);
                }

                // Ajoutez TilePane au TitledPane
                concoursTitledPane.setContent(tilePane);

                // Ajoutez le TitledPane à la VBox principale
                concoursVBox.getChildren().add(concoursTitledPane);
            }

            // Ajoutez la VBox principale à la ScrollPane
            scrollPane.setContent(concoursVBox);
        }






    /******************************************************************************/
    private void envoyerEmailAuxGagnants(Concours concours) {


            OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
            List<OeuvreArt> gagnants = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

            // Set up the properties for the mail server
            Email email = new HtmlEmail();
            email.setHostName("smtp-mail.outlook.com");
            email.setSmtpPort(587);
            email.setStartTLSEnabled(true);
            email.setAuthenticator(new DefaultAuthenticator("mariem.saieb@esprit.tn", "Mic2024rosoft"));

            // Set the default "from" address
        try {
            email.setFrom("mariem.saieb@esprit.tn");
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }

        for (OeuvreArt gagnant : gagnants) {
                // Set the recipient address
            try {
                email.addTo(oeuvreConcoursService.getArtisteEmail(gagnant.getId()));
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }

            // Set the email subject and body
                email.setSubject("Félicitations! Vous avez gagné le concours " + concours.getTitre());
            try {
                email.setMsg("Cher Artiste "  + ",\n\n"
                        + "Félicitations! Vous avez remporté le concours '" + concours.getTitre() + "'."
                        + "\n\nMerci pour votre participation.");
            } catch (EmailException e) {
                throw new RuntimeException(e);
            }

            try {
                    // Send the email
                    email.send();

                    System.out.println("Email sent successfully to: " + oeuvreConcoursService.getArtisteEmail(gagnant.getId()));

                } catch (EmailException e) {
                    e.printStackTrace();
                    System.err.println("Error sending email to: " + oeuvreConcoursService.getArtisteEmail(gagnant.getId()));
                }

                // Clear the recipient address for the next iteration
                email.getToAddresses().clear();
            }


        }
/*************************************************************************************/
    private void envoyerSMSAuxGagnants(Concours concours) {
        OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
        List<OeuvreArt> gagnants = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

        // Your Twilio Account SID and Auth Token
        String ACCOUNT_SID = "ACa75b9ec0f620ce8818eedae19d3b82a8";
        String AUTH_TOKEN = "1c99623fe6acdb95172d704211aa154f";

        // Initialize Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Twilio phone number
        String twilioPhoneNumber = "+12522313962";

        for (OeuvreArt gagnant : gagnants) {
            // Recipient's phone number
           String recipientNumber = formatPhoneNumber(oeuvreConcoursService.getArtistePhoneNumber(gagnant.getId()));



            // SMS body
            String messageBody = "Cher Artiste "  + ",\n\n"
                    + "Félicitations! Vous avez remporté le concours '" + concours.getTitre() + "'."
                    + "\n\nMerci pour votre participation.";

            // Send SMS
            Message message = Message.creator(
                            new PhoneNumber(recipientNumber), // Recipient's phone number
                            new PhoneNumber(twilioPhoneNumber), // Your Twilio phone number
                            messageBody)
                    .create();

            System.out.println("SMS sent successfully to: " + recipientNumber);
        }
    }
    private String formatPhoneNumber(int rawPhoneNumber) {
        return "+216" + String.valueOf(rawPhoneNumber);
    }


}


