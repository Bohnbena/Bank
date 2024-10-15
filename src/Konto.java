import org.json.JSONObject;

import javax.naming.Name;
import java.io.FileWriter;
import java.io.IOException;
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

        //Jedes neue konto starten mit einem betrag von 0€
        //Erstellen neues bank object und teilen die werte zu
//        JSONObject jsonkonto = new JSONObject();
//        jsonkonto.put("Name", konto.getVollerName());
//        jsonkonto.put("Kontonummer", konto.getKontoNummer());
//        jsonkonto.put("Kontostand", konto.getKontoStand());
//        jsonkonto.put("Kontoart",konto.getKontoArt());
//
//        //Schreiben die JSON Datei um ein neue konto zu eröffnen
//        JsonUtils utils = new JsonUtils();
//        utils.writeJsonBank(jsonkonto, userID);
    }

    public void KontoBetreten() {

        Scanner userInput = new Scanner(System.in);
        System.out.println("Bitte Kontonummer eingeben");

        String kontonummer = userInput.nextLine();

        JsonUtils jsonUtils = new JsonUtils();
        JSONObject jsonkonto = jsonUtils.readJsonBank(kontonummer);

        //todo Davor kontodaten anzeigen bevor der aktion

        System.out.println("Bitte Wählen sie Ihre Aktion");
        System.out.println("[E]-Einzahlen");
        System.out.println("[A]-Abheben");

        String userinput = userInput.nextLine();
        //System.out.println(jsonkonto.getString("Name"));
        switch (userinput) {
            case "E":
                jsonUtils.modifyJsonBank("E", jsonkonto);

                break;
            case "A":


                jsonUtils.modifyJsonBank("A", jsonkonto);
                break;
        }
    }
}

class Girokonto extends Konto {




}

class PremiumGrioKonto extends Konto {



}
