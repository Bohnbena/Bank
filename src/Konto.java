import org.json.JSONObject;

import javax.naming.Name;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public Konto wähleKontoArt() {
        Scanner userInput = new Scanner(System.in);
        Konto konto;
        kontoart:
        while(true)
        {
            System.out.println("Bitte Wählen sie Ihre Kontoart");
            System.out.println("[G]-Girokonto[Gebühren-0,5%]");
            System.out.println("[P]-Girokontopremium[Gebühren-2%]");
            switch (userInput.nextLine()) {
                case "G": //Konto Eröffnen
                    konto = new Girokonto();
                    konto.setKontoArt("Griokonto");
                    return konto;
                case "P": //Konto Betreten
                    konto = new PremiumGrioKonto();
                    konto.setKontoArt("Premium Girokonto");
                    return konto;
            }
        }

    }

    public void ErstelleKonto() {
        //Step 1. Erstellen ein neues Objekt
        //Step 2. Weisen ihm alle daten zu
        //Step 3. Speichern denn nutzer Ab
        Scanner userInput = new Scanner(System.in);

        //Default müssen wir ausehalb des scopes schon ein konto object erstellen
        Konto konto = wähleKontoArt();

        System.out.println("Bitte Namen Eingeben");
        konto.setVollerName(userInput.nextLine());

        //Setzen eine Random Kontonummer
        Random random = new Random();
        int id = random.nextInt(1, 1000000000);
        String userID = Integer.toString(id);
        konto.setKontoNummer(userID);

        JsonJackson jsonJackson = new JsonJackson();
        //Neue Json Methode
        jsonJackson.ObjectToJson(konto, userID);

        //Wenn ein neuer kunde angelegt wird, sollten wir auch ein transaktionen ordner für ihn erstellen
        File transactionsdir = new File("Transactions");
        if (!transactionsdir.exists()) {
            transactionsdir.mkdirs();
        }
        //Dannach erstellen wir denn transaktionens ordner auf der Userebene
        String path = userID;
        String basepath = "Transactions";

        File usertransactionsdir = new File(basepath,path);
        if (!usertransactionsdir.exists()) {
            usertransactionsdir.mkdirs();
        }
        konto.KontoBetreten(userID);
    }

    public void KontoBetreten(String kontoNummer) {
        Scanner userInput = new Scanner(System.in);
        if (kontoNummer.isEmpty())
        {
            System.out.println("Bitte Kontonummer eingeben");
            kontoNummer = userInput.nextLine();
        }

        JsonJackson jackson = new JsonJackson();
        //todo Prüfung einbauen ob kontonummer existiert //Brauchen wir ja sonst nicht oder?
        //Laden in der methode die Json in eine map rein und reichen sie weiter
        Map<String, Object> kontodaten = jackson.JsonToObject(kontoNummer);

        //todo Davor kontodaten anzeigen bevor der aktion

        while (true) {
            System.out.println("Bitte Wählen sie Ihre Aktion");
            System.out.println("[E]-Einzahlen");
            System.out.println("[A]-Abheben");
            System.out.println("[X]-Exit");

            String userinput = userInput.nextLine();
            switch (userinput) {
                case "E":
                    jackson.EditJsonObject("E", kontodaten);
                    break;
                case "A":
                    jackson.EditJsonObject("A", kontodaten);
                    break;
                case "X":
                    System.exit(0);
                    break;
            }
        }
    }
}

class Girokonto extends Konto {


}

class PremiumGrioKonto extends Konto {


}
