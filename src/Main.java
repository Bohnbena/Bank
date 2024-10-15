public class Main {
    public static void main(String[] args) {
        MainMenu menu    = new MainMenu();
        Konto konto      = new Girokonto();
        String UserInput = menu.bankIntro();

        switch (UserInput){
            case "E": //Konto Eröffnen
                  konto.ErstelleKonto();
                break;
            case "B": //Konto Betreten
                  konto.KontoBetreten();
                break;
        }
    }
}