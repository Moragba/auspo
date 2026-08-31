package com.auspo.backend.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;




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
        if (verband != null && !verband.trim().isEmpty()) {            
            return disziplinRepo.findByVerbandIgnoreCase(verband);
        }

        return disziplinRepo.findAll();
    }

    @PostMapping("/getpdf")
    public ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> payload) {
        
        // 1. Felder exakt aus deinem React eventData State auslesen
        String vereinsname      = (String) payload.getOrDefault("vereinsname", "Vereinsname nicht angegeben");
        String titel            = (String) payload.getOrDefault("titel", "Ausschreibung");
        String datumVon          = (String) payload.getOrDefault("datumVon", "");
        String datumBis          = (String) payload.getOrDefault("datumBis", "");
        String ort              = (String) payload.getOrDefault("ort", "-");
        String adresse          = (String) payload.getOrDefault("adresse", "-");
        String startgeld        = (String) payload.getOrDefault("startgeld", "-");
        String anmeldeschluss    = formatDatum((String) payload.getOrDefault("anmeldeschluss", "-"));
        String ansprechpartner  = (String) payload.getOrDefault("ansprechpartner", "-");
        String email            = (String) payload.getOrDefault("email", "-");
        String verband = (String) payload.getOrDefault("verband", "-");
        Object rawAltersklassen = payload.get("angeboteneAltersklassen");
        String altersklassenText = "-";
        if (rawAltersklassen instanceof java.util.List<?>) {
            java.util.List<?> list = (java.util.List<?>) rawAltersklassen;
            altersklassenText = String.join(", ", list.stream().map(Object::toString).toList());
        } else if (rawAltersklassen != null) {
            altersklassenText = rawAltersklassen.toString();
        }
        Object rawDisziplin = payload.get("disziplin");
        String eigeneDisziplin = (String) payload.getOrDefault("disziplin", "-");
        String eigenSchuesseZeit = "-";
        String kennziffer = "-";
        String bezeichnung = "-";
        String waffenart = "-";
        String lauflaenge = "-";
        String visierung = "-";
        String geschoss = "-";
        String distanz = "-";
        String anschlagsart = "-";
        String wettkampfschuesse = "-";
        String zeitvorgabeInMin = "-";
        String scheibenNr = "-";
        boolean vereinsinterneAusschreibung = (boolean)payload.getOrDefault("vereinsinterneAusschreibung", false);
        

        String disziplinName = null; // Variable zum Speichern des Klartext-Namens der Disziplin
        System.out.println("Verband: " + verband);
        if(!vereinsinterneAusschreibung) {
            if (rawDisziplin != null && !rawDisziplin.toString().isBlank()) {
                try {
                    Long id = Long.valueOf(rawDisziplin.toString());            
                    Disziplin d = disziplinRepo.findById(id).orElse(null);
                    if (d != null) {
                        kennziffer = d.getKennziffer();
                        bezeichnung = d.getBezeichnung();
                        waffenart = d.getWaffenart();
                        lauflaenge = d.getLauflaenge();
                        visierung = d.getVisierung();
                        geschoss = d.getGeschoss();
                        distanz = d.getDistanz();
                        anschlagsart = d.getAnschlagsart();
                        wettkampfschuesse = d.getWettkampfschuesse();
                        zeitvorgabeInMin = d.getZeitvorgabeInMin();
                        scheibenNr = d.getScheibenNr();
                    };
                } catch (NumberFormatException e) {
                    // Falls disziplinId schon als Klartext-Name gesendet wurde
                    disziplinName = rawDisziplin.toString();
                }
            }
        } else {
            waffenart = (String) payload.getOrDefault("waffenart", "-");
            lauflaenge = (String) payload.getOrDefault("lauflaenge", "-");
            visierung = (String) payload.getOrDefault("visierung", "-");
            geschoss = (String) payload.getOrDefault("geschoss", "-");
            distanz = (String) payload.getOrDefault("distanz", "-");
            anschlagsart = (String) payload.getOrDefault("anschlag", "-");
            scheibenNr = (String) payload.getOrDefault("scheibenNr", "-");
            eigenSchuesseZeit = (String) payload.getOrDefault("schuesseZeit", "-");
        }
              
        
        
        // Booleans auslesen
        boolean hinweiseSpO      = Boolean.TRUE.equals(payload.get("hinweiseSpO"));
        boolean gesundheit       = Boolean.TRUE.equals(payload.get("gesundheit"));
        boolean haftung          = Boolean.TRUE.equals(payload.get("haftungsausschluss"));

        // Datums-String zusammenbauen (z. B. "17.01.2026 - 18.01.2026")
        String datumText = formatDatum(datumVon);
        
        if (!datumBis.isEmpty() && !datumBis.equals(datumVon)) {
            datumText += " bis " + formatDatum(datumBis);
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4, 36, 36, 36, 36);
            PdfWriter.getInstance(document, out);

            document.open();

            // Farbdesign
            Color primaryColor  = new Color(34, 112, 62);   
            Color darkTextColor = new Color(40, 40, 40);
            Color lightBgColor  = new Color(245, 247, 248);

            // Fonts
            Font headerFont  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, primaryColor);
            Font titleFont   = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, primaryColor);
            Font sectionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 11, primaryColor);
            Font labelFont   = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, darkTextColor);
            Font bodyFont    = FontFactory.getFont(FontFactory.HELVETICA, 10, darkTextColor);
            Font footerFont  = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8, Color.GRAY);

            // 1. KOPFZEILE (Vereinsname)
            Paragraph topHeader = new Paragraph(vereinsname.toUpperCase(), headerFont);
            topHeader.setAlignment(Element.ALIGN_CENTER);
            document.add(topHeader);

            document.add(createLineSeparator(primaryColor, 1.5f));

            // 2. HAUPTTITEL
            Paragraph mainTitle = new Paragraph("A U S S C H R E I B U N G", titleFont);
            mainTitle.setAlignment(Element.ALIGN_CENTER);
            mainTitle.setSpacingBefore(10);
            document.add(mainTitle);

            Paragraph eventTitle = new Paragraph(titel, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, darkTextColor));
            eventTitle.setAlignment(Element.ALIGN_CENTER);
            eventTitle.setSpacingAfter(15);
            document.add(eventTitle);

            // 3. ECKDATEN-BOX (Datum, Ort, Anmeldeschluss)
            PdfPTable infoBox = new PdfPTable(1);
            infoBox.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell();
            cell.setBackgroundColor(lightBgColor);
            cell.setBorderColor(primaryColor);
            cell.setBorderWidth(1f);
            cell.setPadding(10);

            Paragraph boxContent = new Paragraph();
            boxContent.add(new Chunk("Datum: ", labelFont));
            boxContent.add(new Chunk(new VerticalPositionMark(), 120, false));
            boxContent.add(new Chunk((datumText.isEmpty() ? "-" : datumText) + "\n", bodyFont));

            boxContent.add(new Chunk("Adresse: ", labelFont));            
            boxContent.add(new Chunk(new VerticalPositionMark(), 120, false));
            boxContent.add(new Chunk(adresse + "\n", bodyFont));

            boxContent.add(new Chunk("Ort: ", labelFont));
            boxContent.add(new Chunk(new VerticalPositionMark(), 120, false));                        
            boxContent.add(new Chunk(ort + "\n", bodyFont));

            boxContent.add(new Chunk("Anmeldeschluss: ", labelFont));
            boxContent.add(new Chunk(new VerticalPositionMark(), 120, false));
            boxContent.add(new Chunk(anmeldeschluss, bodyFont));

            cell.addElement(boxContent);
            infoBox.addCell(cell);
            infoBox.setSpacingAfter(15);
            document.add(infoBox);

            // 4. DETAIL-TABELLE
            PdfPTable detailsTable = new PdfPTable(2);
            detailsTable.setWidthPercentage(100);
            detailsTable.setWidths(new float[]{30, 70});

            addDetailRow(detailsTable, "Veranstalter:", vereinsname, labelFont, bodyFont);
            addDetailRow(detailsTable, "Startgeld:", startgeld, labelFont, bodyFont);
            addDetailRow(detailsTable, "Ansprechpartner:", ansprechpartner, labelFont, bodyFont);
            addDetailRow(detailsTable, "E-Mail für Meldung:", email, labelFont, bodyFont);

            detailsTable.setSpacingAfter(20);

            // Disziplin-Spezifische Felder aus der Datenbank
            String disziplinTitel = (!kennziffer.isEmpty() ? kennziffer + " " : "") + bezeichnung;
            if(vereinsinterneAusschreibung) {
                addDetailRow(detailsTable, "Disziplin:", eigeneDisziplin, labelFont, bodyFont);
            }else {
                addDetailRow(detailsTable, "Disziplin:", disziplinTitel, labelFont, bodyFont);
            }
            addDetailRow(detailsTable, "Altersklasse(n):", altersklassenText, labelFont, bodyFont);
            addDetailRow(detailsTable, "Distanz:", distanz, labelFont, bodyFont);
            addDetailRow(detailsTable, "Anschlag:", anschlagsart, labelFont, bodyFont);

            String programmText = wettkampfschuesse + (!wettkampfschuesse.isEmpty() ? " Schuss" : "") 
                + (!zeitvorgabeInMin.isEmpty() ? " in " + zeitvorgabeInMin + " Min." : "");
            
            if(vereinsinterneAusschreibung) {
                addDetailRow(detailsTable, "Schüsse und Zeitvorgabe:", eigenSchuesseZeit, labelFont, bodyFont);
            }else {
                addDetailRow(detailsTable, "Schüsse und Zeitvorgabe:", programmText, labelFont, bodyFont);
            }
            

            addDetailRow(detailsTable, "Waffenart:", waffenart, labelFont, bodyFont);
            addDetailRow(detailsTable, "Visierung:", visierung, labelFont, bodyFont);
            addDetailRow(detailsTable, "Geschoss:", geschoss, labelFont, bodyFont);
            addDetailRow(detailsTable, "Lauflänge:", lauflaenge, labelFont, bodyFont);
            addDetailRow(detailsTable, "Scheibe Nr.:", scheibenNr, labelFont, bodyFont);

            addDetailRow(detailsTable, "Startgeld:", startgeld, labelFont, bodyFont);
            addDetailRow(detailsTable, "Ansprechpartner:", ansprechpartner, labelFont, bodyFont);
            addDetailRow(detailsTable, "E-Mail:", email, labelFont, bodyFont);
            String weitereInfo = payload.get("weitereInfo") != null ? payload.get("weitereInfo").toString() : "-";
            addDetailRow(detailsTable, "Weitere Informationen:", weitereInfo, labelFont, bodyFont);

            detailsTable.setSpacingAfter(15);
            document.add(detailsTable);

            // 5. REGELWERK & HINWEISE (Basiert auf den Booleans)
            if (hinweiseSpO || haftung) {
                Paragraph rulesHeader = new Paragraph("Allgemeine Bestimmungen:", sectionFont);
                rulesHeader.setSpacingAfter(5);
                document.add(rulesHeader);

                com.lowagie.text.List list = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED, 10);
                list.setListSymbol(new Chunk("• ", sectionFont));

                if (hinweiseSpO) {
                    list.add(new ListItem(  "„Mit der Anmeldung erkennt der Teilnehmer die Sportordnung des " + verband + ", sowie die Schießstandordnung des austragenden Vereins als verbindlich an." + 
                                            " Den Anweisungen der Schießleitung und der Standaufsichten ist Folge zu leisten.“", bodyFont));
                }
                if (haftung) {
                    list.add(new ListItem(". Diese Haftungsbeschränkung gilt nicht für Schäden aus der Verletzung des Lebens, des Körpers oder der Gesundheit.", bodyFont));
                }
                if(gesundheit) {
                    list.add(new ListItem(  "Jeder Schütze ist für die Sicherheit der von ihm abgegebenen Schüsse sowie für den Zustand seiner Waffen und Ausrüstung selbst verantwortlich." +
                                            "Die Teilnehmer erklären mit der Anmeldung, dass keine gesundheitlichen Einschränkungen vorliegen, die einer sicheren Teilnahme am Wettkampf entgegenstehen." +
                                            "Für ausreichenden eigenen Versicherungsschutz (Haftpflicht/Unfall) hat jeder Teilnehmer selbst zu sorgen.", bodyFont));
                }

                document.add(list);
            }

            // 6. FUSSZEILE
            Paragraph footer = new Paragraph("\n\nErstellt am: " + java.time.LocalDate.now(), footerFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
            document.add(footer);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

        byte[] pdfBytes = out.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "Ausschreibung.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdfBytes);
    }

    private Chunk createLineSeparator(Color color, float thickness) {
        com.lowagie.text.pdf.draw.LineSeparator line = new com.lowagie.text.pdf.draw.LineSeparator();
        line.setLineColor(color);
        line.setLineWidth(thickness);
        return new Chunk(line);
    }

    private void addDetailRow(PdfPTable table, String label, String value, Font labelFont, Font bodyFont) {
        PdfPCell cellLabel = new PdfPCell(new Phrase(label, labelFont));
        cellLabel.setBorder(Rectangle.NO_BORDER);
        cellLabel.setPaddingBottom(8);

        PdfPCell cellValue = new PdfPCell(new Phrase(value, bodyFont));
        cellValue.setBorder(Rectangle.NO_BORDER);
        cellValue.setPaddingBottom(8);

        table.addCell(cellLabel);
        table.addCell(cellValue);
    }

    // Hilfsmethode zum Befüllen der Tabellenzellen
    /*private void addTableCell(PdfPTable table, String text, Font font, Color bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(8);
        table.addCell(cell);
    }*/

    // Hilfsmethode zum Formatieren von ISO-Daten (YYYY-MM-DD -> DD.MM.YYYY)
    private String formatDatum(String inputDatum) {
        if (inputDatum == null || inputDatum.isBlank()) {
            return "";
        }
        try {
            LocalDate date = LocalDate.parse(inputDatum);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return date.format(formatter);
        } catch (Exception e) {
            // Falls der String schon formatiert ist oder ein unerwartetes Format hat:
            return inputDatum;
        }
    }
}
