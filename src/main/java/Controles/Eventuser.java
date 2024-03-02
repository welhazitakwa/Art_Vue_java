package Controles;

import models.Person;
import models.evenement;
import services.EventService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Eventuser {
    @FXML
    private TableColumn<Person,Integer> numero;

    @FXML
    private TableColumn<Person, String> nom;
    @FXML
    private TableView<Person> table;
    private int id;
    public void setId(int id){
        this.id=id;
    }
    public void setdata(int id) throws SQLException {
        EventService es = new EventService();
        List<String> l = es.listAllEventUser(id);
        ObservableList<Person> data = FXCollections.observableArrayList();
        for (int i = 0; i < l.size(); i++) {
            data.add(new Person(i + 1, l.get(i)));
        }
        numero.setCellValueFactory(cellData -> cellData.getValue().numberProperty().asObject());
        nom.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        table.setItems(data);
    }

    public void pdf(ActionEvent actionEvent) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

            String imagePath = Eventuser.class.getResource("/image/0.png").getPath();
            File imageFile = new File(imagePath);
            PDImageXObject badgeImage = PDImageXObject.createFromFileByContent(imageFile, document);
            float x = 50;
            float yy = 650;
            float width = 80;
            float height = 80;


            contentStream.drawImage(badgeImage, x, yy, width, height);
            EventService es = new EventService();
            evenement e=es.getEventById(id);



            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
            contentStream.beginText();
            contentStream.newLineAtOffset(150, 700);
            contentStream.showText("Nom de l'événement : ");
            contentStream.showText(e.getNom());
            contentStream.newLine();
            contentStream.endText();

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
            contentStream.beginText();
            contentStream.newLineAtOffset(250, 650);
            contentStream.showText("Détails de l'événement");
            contentStream.endText();


            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.moveTextPositionByAmount(100, 600);
            contentStream.drawString("Lieu : " + e.getLieu());
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString("Capacité : " + e.getCapacite());
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.drawString("Date : " + e.getDate());

            contentStream.endText();


            String[] userList = es.listAllEventUser(id).toArray(new String[0]);
            int y = 490;
            for (int i = 0; i < userList.length; i++) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.newLineAtOffset(300, y);
                contentStream.showText("Utilisateur " + (i+1) + " : " + userList[i]);
                contentStream.endText();
                y -= 20;
            }


            int numberOfUsers = userList.length;
            float eventPrice = e.getPrice();
            float totalPrice = numberOfUsers * eventPrice;

            // Ajout du prix total
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(100, 300);
            contentStream.showText("Prix total : " + totalPrice + " Dt");
            contentStream.endText();


            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            contentStream.newLineAtOffset(400, 50);
            contentStream.showText("Date : " + LocalDate.now());
            contentStream.moveTextPositionByAmount(0, -20);
            contentStream.showText("ArtVue");
            contentStream.endText();

            contentStream.close();
            File file = new File("Facture.pdf");
            document.save(file);
            document.close();

            // Ouverture du document PDF avec l'application par défaut
            if (Desktop.isDesktopSupported() && file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Impossible d'ouvrir le fichier PDF.");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la génération ou de l'ouverture du fichier PDF : " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

     }
}
