import org.json.JSONObject;

import javax.naming.Name;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public abstract class Konto {
    String vollerName;
    String kontoNummer;
    String[][] transaktionen;
    String kontoArt;
    int kontoStand = 0;

    public int getKontoStand() {
        return kontoStand;
    }

    public void setKontoStand(int kontoStand) {
        this.kontoStand = kontoStand;
    }

    public String[][] getTransaktionen() {
        return transaktionen;
    }

    public void setTransaktionen(String[][] transaktionen) {
        this.transaktionen = transaktionen;
    }

    public String getKontoNummer() {
        return kontoNummer;
    }

    public void setKontoNummer(String kontoNummer) {
        this.kontoNummer = kontoNummer;
    }

    public String getVollerName() {
        return vollerName;
    }

    public void setVollerName(String vollerName) {
        this.vollerName = vollerName;
    }

    public String getKontoArt() {
        return kontoArt;
    }

    public void setKontoArt(String kontoArt) {
        this.kontoArt = kontoArt;
    }

    public Konto wähleKontoArt(String input)
    {
        Konto konto;
        switch (input){
            case "G": //Konto Eröffnen
                konto = new Girokonto();
                konto.setKontoArt("Griokonto");
                return konto;
            case "P": //Konto Betreten
                konto = new PremiumGrioKonto();
                konto.setKontoArt("Premium Girokonto");
                return konto;
            default:
                return konto = null
                ;
        }
    }
    public void ErstelleKonto() {
        //Step 1. Erstellen ein neues Objekt
        //Step 2. Weisen ihm alle daten zu
        //Step 3. Speichern denn nutzer Ab
        Scanner userInput = new Scanner(System.in);

        // Hier bestimmen wir die konto art
        System.out.println("Bitte Wählen sie Ihre Kontoart");
        System.out.println("[G]-Girokonto[Gebühren-0,5%]");
        System.out.println("[P]-Girokontopremium[Gebühren-2%]");

        //Default müssen wir ausehalb des scopes schon ein konto object erstellen
        Konto konto = wähleKontoArt(userInput.nextLine());;

        System.out.println("Bitte Namen Eingeben");
        konto.setVollerName(userInput.nextLine());

        //Setzen eine Random Kontonummer
        Random random = new Random();
        int id = random.nextInt(1, 1000000000);
        String userID = Integer.toString(id);
        konto.setKontoNummer(userID);

        JsonJackson jsonJackson = new JsonJackson();
        //Neue Json Methode
        jsonJackson.ObjectToJson(konto,userID);
    }

    public void KontoBetreten() {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Bitte Kontonummer eingeben");

        String kontonummer = userInput.nextLine();


        JsonJackson jackson = new JsonJackson();
        //todo Prüfung einbauen ob kontonummer existiert //Brauchen wir ja sonst nicht oder?
        //Laden in der methode die Json in eine map rein und reichen sie weiter
        Map<String,Object> kontodaten = jackson.JsonToObject(kontonummer);

        //todo Davor kontodaten anzeigen bevor der aktion
        System.out.println("Bitte Wählen sie Ihre Aktion");
        System.out.println("[E]-Einzahlen");
        System.out.println("[A]-Abheben");

        String userinput = userInput.nextLine();
        switch (userinput) {
            case "E":
                jackson.EditJsonObject("E",kontodaten);
                break;
            case "A":
                jackson.EditJsonObject("A",kontodaten);
                break;
        }
    }
}

class Girokonto extends Konto {




}

class PremiumGrioKonto extends Konto {



}
