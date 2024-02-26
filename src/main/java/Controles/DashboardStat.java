package Controles;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import services.categorie.CategorieService;
import services.oeuvreArt.OeuvreArtService;

public class DashboardStat {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label nbOeuvreLabel;
    @FXML
    private Label idNbCategorieLabel;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void initialize() {
        OeuvreArtService oeuvreArtService = new OeuvreArtService();
        CategorieService categorieService = new CategorieService();
        try {
            int nombreOeuvres = oeuvreArtService.nombreOeuvresArt();
            nbOeuvreLabel.setText(String.valueOf(nombreOeuvres));

            int nombreCategorie = categorieService.getNombreCategories();
            idNbCategorieLabel.setText(String.valueOf(nombreCategorie));

            Map<String, Integer> data = oeuvreArtService.nombreOeuvresArtParToutesCategories();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                String categorie = entry.getKey();
                int nombreOeuvresCat = entry.getValue();
                series.getData().add(new XYChart.Data<>(categorie + " (" + nombreOeuvresCat + ")", nombreOeuvresCat));
            }
            barChart.getData().add(series);
            barChart.setTitle("Nombre d'œuvres par catégorie");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
