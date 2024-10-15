import java.util.Scanner;

public class MainMenu  {
    public String bankIntro(){
        System.out.println("Willkommen bei ihrer Bank");
        System.out.println("Bitte Wählen sie Ihre Aktion");
        System.out.println("[E]-Konto Eröffnen");
        System.out.println("[B]-Konto Betreten");

        Scanner Input = new Scanner(System.in);
        return Input.nextLine();
    }
}
