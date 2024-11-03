package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// REST-Controller für die SSCC-Validierungsfunktion
@RestController
@RequestMapping("/api") // Basis-URL für die Endpunkte des Controllers
public class SSCCController {
    
    // Repository zur Speicherung von Validierungsanfragen
    @Autowired
    private ValidationRequestRepository validationRequestRepository;

    // POST-Endpunkt zur Validierung einer SSCC-Nummer
    @PostMapping("/validate-sscc")
    public ResponseEntity<Map<String, Object>> validateSSCC(@RequestBody Map<String, String> request) {
        // SSCC-Nummer aus der Anfrage extrahieren
        String sscc = request.get("sscc");

        // SSCC-Nummer validieren
        boolean isValid = validateSSCCNumber(sscc);

        // Validierungsanfrage in der Datenbank speichern
        ValidationRequest validationRequest = new ValidationRequest();
        validationRequest.setSscc(sscc); // SSCC-Nummer festlegen
        validationRequest.setValid(isValid); // Validierungsergebnis festlegen
        validationRequest.setRequestTime(LocalDateTime.now()); // Anfragezeit festlegen
        validationRequestRepository.save(validationRequest); // Speichern der Anfrage

        // Antwort-Map erstellen, um das Validierungsergebnis zurückzugeben
        Map<String, Object> response = new HashMap<>();
        response.put("sscc", sscc);
        response.put("isValid", isValid);

        // Antwort mit Status 200 und den Validierungsergebnissen zurückgeben
        return ResponseEntity.ok(response);
    }

    // Methode zur Überprüfung, ob die SSCC-Nummer gültig ist
    private boolean validateSSCCNumber(String sscc) {
        // Prüfen, ob die SSCC-Nummer null, nicht 18-stellig oder nicht numerisch ist
        if (sscc == null || sscc.length() != 18 || !sscc.matches("\\d+")) {
            return false; // Ungültige SSCC-Nummer
        }
        // Prüfziffer validieren
        return isValidCheckDigit(sscc);
    }

    // Methode zur Berechnung und Überprüfung der Prüfziffer der SSCC-Nummer
    private boolean isValidCheckDigit(String sscc) {
        int sum = 0;

        // Schleife über die ersten 17 Ziffern der SSCC-Nummer
        for (int i = 0; i < 17; i++) {
            int digit = Character.getNumericValue(sscc.charAt(i));

            // Jeder zweite Wert wird mit 3 multipliziert und zur Summe hinzugefügt
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        // Berechnung der Prüfziffer
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;

        // Vergleich der berechneten Prüfziffer mit der in der SSCC angegebenen Prüfziffer
        int providedCheckDigit = Character.getNumericValue(sscc.charAt(17));
        return calculatedCheckDigit == providedCheckDigit;
    }
}
