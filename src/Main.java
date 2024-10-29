public class Main {
    public static void main(String[] args) {
        Konto konto      = new Girokonto();
        MainMenu menu    = new MainMenu();
        while(true)
        {
            String UserInput = menu.bankIntro();
            switch (UserInput){
                case "E": //Konto Eröffnen
                    konto.ErstelleKonto();
                    break;
                case "B": //Konto Betreten
                    konto.KontoBetreten("");
                    break;
            }
        }
    }
}