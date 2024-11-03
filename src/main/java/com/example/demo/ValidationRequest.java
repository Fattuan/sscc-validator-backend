package com.example.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Diese Klasse repräsentiert eine Entität für Validierungsanfragen in der Datenbank
@Entity
@Table(name = "validation_requests") // Name der Tabelle in der Datenbank
public class ValidationRequest {

    // Primärschlüssel mit auto-increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // SSCC-Nummer, die validiert wird, darf nicht null sein
    @Column(nullable = false)
    private String sscc;

    // Ergebnis der Validierung (true = gültig, false = ungültig)
    @Column(nullable = false)
    private boolean isValid;

    // Zeitpunkt der Anfrage, ebenfalls verpflichtend
    @Column(nullable = false)
    private LocalDateTime requestTime;

    // Getter- und Setter-Methoden für die Eigenschaften

    // Gibt die ID der Anfrage zurück
    public Long getId() {
        return id;
    }

    // Setzt die ID der Anfrage (wird in der Regel von der Datenbank automatisch gesetzt)
    public void setId(Long id) {
        this.id = id;
    }

    // Gibt die SSCC-Nummer zurück
    public String getSscc() {
        return sscc;
    }

    // Setzt die SSCC-Nummer
    public void setSscc(String sscc) {
        this.sscc = sscc;
    }

    // Gibt zurück, ob die SSCC-Nummer gültig ist
    public boolean isValid() {
        return isValid;
    }

    // Setzt das Validierungsergebnis
    public void setValid(boolean valid) {
        isValid = valid;
    }

    // Gibt den Zeitpunkt der Anfrage zurück
    public LocalDateTime getRequestTime() {
        return requestTime;
    }

    // Setzt den Zeitpunkt der Anfrage
    public void setRequestTime(LocalDateTime requestTime) {
        this.requestTime = requestTime;
    }
}
