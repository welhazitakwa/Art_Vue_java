package Controles;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PDFGenerator {

    public static void generatePDF(List<String> userList, String outputPath) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);

            contentStream.showText("Liste des utilisateurs :");
            contentStream.newLineAtOffset(0, -20);

            for (String user : userList) {
                contentStream.showText(user);
                contentStream.newLineAtOffset(0, -15);
            }

            contentStream.endText();
            contentStream.close();

            document.save(outputPath);
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Exemple d'utilisation :
        List<String> userList = List.of("Utilisateur 1", "Utilisateur 2", "Utilisateur 3");
        generatePDF(userList, "C:\\Users\\Fatma Ouelhazi\\Downloads\\output.pdf");
    }
}
