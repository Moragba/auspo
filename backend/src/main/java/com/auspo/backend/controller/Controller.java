package com.auspo.backend.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auspo.backend.model.AltersKlasse;
import com.auspo.backend.model.Disziplin;
import com.auspo.backend.model.Verband;
import com.auspo.backend.repo.AltersKlasseRepo;
import com.auspo.backend.repo.DisziplinRepo;
import com.auspo.backend.repo.VerbandRepo;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;




@RestController
@RequestMapping("/api")
public class Controller {

    private final VerbandRepo verbandRepo;
    private final AltersKlasseRepo altersKlasseRepo;
    private final DisziplinRepo disziplinRepo;
    
    public Controller(VerbandRepo verbandRepo, AltersKlasseRepo altersKlasseRepo, DisziplinRepo disziplinRepo) {
        this.verbandRepo = verbandRepo;
        this.altersKlasseRepo = altersKlasseRepo;
        this.disziplinRepo = disziplinRepo;
    }

    
   
    
    @GetMapping("/verbaende")
    public List<Verband> getVerbaende() {
        return verbandRepo.findAll();
    }

    @GetMapping("/altersklassen")
    public List<AltersKlasse> getAltersklassen() {
        return altersKlasseRepo.findAll();
    }

    @GetMapping("/disziplinen")
    public List<Disziplin> getDisziplinen(@RequestParam(required = false) String verband) {
        System.out.println(verband);       
        if (verband != null && !verband.trim().isEmpty()) {            
            return disziplinRepo.findByVerbandIgnoreCase(verband);
        }

        return disziplinRepo.findAll();
    }

    @PostMapping("/getpdf")
    public ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> payload) {
        System.out.println("Daten sind da! Titel: " + payload.get("verband"));

        
        
        // Ein minimales, gültiges "Dummy-PDF" als Byte-Array (nur zum Testen)
        // Sobald Sie iText oder OpenPDF nutzen, generieren diese Bibliotheken dieses byte[]
        // byte[] dummyPdfBytes = "%PDF-1.4\n1 0 obj\n<< /Type /Catalog /Pages 2 0 R >>\nendobj\n2 0 obj\n<< /Type /Pages /Kids [3 0 R] /Count 1 >>\nendobj\n3 0 obj\n<< /Type /Page /Parent 2 0 R /MediaBox [0 0 612 792] >>\nendobj\nxref\n0 4\n0000000000 65535 f \n0000000009 00000 n \n0000000056 00000 n \n0000000111 00000 n \ntrailer\n<< /Size 4 /Root 1 0 R >>\nstartxref\n190\n%%EOF".getBytes();
        // 1. Daten aus dem Frontend-Payload auslesen
        String verband = (String) payload.getOrDefault("verband", "N/A");
        String disziplin = (String) payload.getOrDefault("disziplinId", "N/A");
        String altersklassen = String.valueOf(payload.getOrDefault("angeboteneAltersklassen", "N/A"));
        // 2. Output-Stream zur Speicherung der PDF-Bytes im Arbeitsspeicher
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Dokument im A4-Format erstellen
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(document, out);

            document.open();

            // Schriftarten definieren
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, new Color(41, 128, 185));
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, Color.WHITE);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 11, Color.BLACK);

            // Überschrift
            Paragraph title = new Paragraph("Ausschreibung", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            // Tabelle mit 2 Spalten für die Daten erstellen
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{30, 70}); // Spaltenverhältnis

            // Tabellen-Header
            addTableCell(table, "Kategorie", headerFont, new Color(41, 128, 185));
            addTableCell(table, "Wert / Angabe", headerFont, new Color(41, 128, 185));

            // Tabellen-Inhalt
            addTableCell(table, "Verband", textFont, Color.LIGHT_GRAY);
            addTableCell(table, verband, textFont, Color.WHITE);

            addTableCell(table, "Disziplin-ID", textFont, Color.LIGHT_GRAY);
            addTableCell(table, disziplin, textFont, Color.WHITE);

            addTableCell(table, "Altersklassen", textFont, Color.LIGHT_GRAY);
            addTableCell(table, altersklassen, textFont, Color.WHITE);

            document.add(table);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        // 3. PDF als Byte-Array an den Browser zurückgeben
        byte[] pdfBytes = out.toByteArray();

        

        // Dem Browser explizit sagen, dass ein PDF kommt
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "ausschreibung_test.pdf");

        // CORS-Header zur Sicherheit direkt in der Antwort mitsenden
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    // Hilfsmethode zum Befüllen der Tabellenzellen
    private void addTableCell(PdfPTable table, String text, Font font, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(8);
        table.addCell(cell);
    }

}
