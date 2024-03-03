package Controles;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import models.OeuvreArt;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFGenerator {

    public static void generatePDF(ObservableList<OeuvreArt> oeuvres) {
        Document document = new Document();
        try {
            // Chemin du bureau de l'utilisateur
            String desktopPath = System.getProperty("user.home") + "/Desktop";
            String filePath = desktopPath + "/oeuvres.pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Création d'un paragraphe avec style pour le titre
            Paragraph title = new Paragraph("Liste des oeuvres d'art", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            PdfPTable table = new PdfPTable(7); // Nombre de colonnes
            table.setWidthPercentage(100); // Pour occuper toute la largeur de la page

            // Ajout du style CSS au tableau
            table.getDefaultCell().setPadding(5);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

            // Ajout des en-têtes de colonnes avec style
            addTableHeader(table);

            // Ajout des lignes de données avec style
            addRows(table, oeuvres);

            document.add(table);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTableHeader(PdfPTable table) {
        table.addCell(new Phrase("ID"));
        table.addCell(new Phrase("Titre"));
        table.addCell(new Phrase("Description"));
        table.addCell(new Phrase("Date Ajout"));
        table.addCell(new Phrase("Prix Vente"));
        table.addCell(new Phrase("Statut"));
        table.addCell(new Phrase("Image"));
    }

    private static void addRows(PdfPTable table, ObservableList<OeuvreArt> oeuvres) {
        for (OeuvreArt oeuvre : oeuvres) {
            table.addCell(String.valueOf(oeuvre.getId()));
            table.addCell(oeuvre.getTitre());
            table.addCell(oeuvre.getDescription());
            table.addCell(oeuvre.getDateAjout().toString());
            table.addCell(String.valueOf(oeuvre.getPrixVente()));
            table.addCell(oeuvre.getStatus());
            table.addCell(oeuvre.getImage());
        }
    }
}

