package Controles;

import models.evenement;
import services.EventService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class  CardEventClient {
    @FXML
    private Label nomevent;
    @FXML
    private Label capa;
    @FXML
    private Label lieu;
    @FXML
    private Label date;
    @FXML
    private Label prix;
    @FXML
    private Label place;
    private  int id;
    private  int idu;

    public void SetIdu(int id) {
        this.idu=id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setdata(evenement e,int nbr){
        nomevent.setText(e.getNom());
        place.setText(String.valueOf(e.getCapacite()-nbr));
        lieu.setText(e.getLieu());
        date.setText(e.getDate().toString());
        prix.setText(String.valueOf(e.getPrice()));
        capa.setText(String.valueOf(e.getCapacite()));
    }
    public static ImageView generateQrCode(String text, int width, int height) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1); // adjust margin as needed

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < bitMatrix.getWidth(); x++) {
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        baos.flush();
        baos.close();

        byte[] imageData = baos.toByteArray();
        Image qrImage = new Image(new java.io.ByteArrayInputStream(imageData));

        ImageView imageView = new ImageView(qrImage);
        return imageView;
    }
    public void inscrit(ActionEvent actionEvent) throws SQLException, IOException {
        EventService es=new EventService();
        int t=es.inscricreEvent(idu,id);
        if(t==1){

            Notifications n = Notifications.create()
                    .title("Félicitations !")
                    .text("Inscription avec succès")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n.showConfirm();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/Eventclient.fxml"));
            Parent root=loader.load();
            capa.getScene().setRoot(root);
            String qrText = "https://giphy.com/gifs/applause-followers-clap-11uArCoB4fkRcQ";
            int width = 300;
            int height = 300;

            try {
                ImageView qrImageView = generateQrCode(qrText, width, height);


                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(qrImageView);

                Scene scene = new Scene(stackPane, width, height);

                // Créez une nouvelle fenêtre (stage) et configurez-la avec la scène
                Stage stage = new Stage();
                stage.setScene(scene);

                // Affichez la fenêtre (stage)
                stage.show();

            } catch (WriterException | IOException e) {
                e.printStackTrace();
            }

        }else{
            Notifications n2 = Notifications.create()
                    .title("Erreur")
                    .text("Désolé, la Inscription a échoué")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER_RIGHT)
                    .darkStyle();
            n2.showError();
        }

    }
}
