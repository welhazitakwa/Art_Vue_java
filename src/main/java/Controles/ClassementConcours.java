
package Controles;



import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import models.Concours;
import models.OeuvreArt;
import services.concours.ConcoursService;
import services.concours.OeuvreConcoursService;
import java.io.File;
import java.util.List;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class ClassementConcours {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox classementVBox;

    public void initialize() {
        // Initialisation, si nécessaire
        afficherClassement();
    }

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
            Button envoyerEmailButton = new Button("Envoyer un e-mail aux gagnants");
            envoyerEmailButton.setOnAction(event -> envoyerEmailAuxGagnants(concours));
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
        scrollPane.setContent(concoursVBox);
    }

    /******************************************************************************/
    private void envoyerEmailAuxGagnants(Concours concours) {
        // Récupérez la liste des gagnants ou œuvres à envoyer par e-mail
        OeuvreConcoursService oeuvreConcoursService = new OeuvreConcoursService();
        List<OeuvreArt> gagnants = oeuvreConcoursService.getTop3ClassementConcours(concours.getId());

        // Configurations pour le serveur SMTP Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créez une session avec une authentification
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("aartvue@gmail.com", "artvue2024");
            }
        });

        // Parcourez la liste des gagnants et envoyez un e-mail à chacun
        for (OeuvreArt gagnant : gagnants) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("aartvue@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(oeuvreConcoursService.getArtisteEmail(gagnant.getId())));
                message.setSubject("Félicitations! Vous avez gagné le concours " + concours.getTitre());
                message.setText("Cher " + gagnant.getArtiste() + ",\n\n"
                        + "Félicitations! Vous avez remporté le concours '" + concours.getTitre() + "'."
                        + "\n\nMerci pour votre participation.");

                // Envoyer le message
                Transport.send(message);

                System.out.println("E-mail envoyé au gagnant : " + oeuvreConcoursService.getArtisteEmail(gagnant.getId()));

            } catch (MessagingException e) {
                e.printStackTrace();
                System.err.println("Erreur lors de l'envoi d'un e-mail au gagnant : " + oeuvreConcoursService.getArtisteEmail(gagnant.getId()));
            }

        }}




}


