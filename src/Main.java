import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Game g = new Game();

        char choice;
        Scanner input = new Scanner(System.in);

        System.out.println("\n\n\t\t 15 Puzzle Grid");
        System.out.println("\n" + g.toString());

        do{
            System.out.println("\n*************** Menu ***************");
            System.out.println("1. Esegui una mossa");
            System.out.println("2. Resetta griglia");
            System.out.println("3. Randomizza posizioni");
            System.out.println("0. Exit");

            System.out.print("La tua scelta => ");
            choice = input.next().charAt(0);

            choiceMenu(g, choice);
        }
        while(choice != '0');
    }

    public static void choiceMenu(Game g, char choice){

        Scanner input = new Scanner(System.in);

        switch (choice){
            case '1':
                int movement;
                if(!g.win()){
                    System.out.println("\n\n\t\t 15 Puzzle Grid");
                    System.out.println("\n" + g.toString());

                    String menuController = "Mosse disponibili: ";
                    if(g.coordsMovements().get(0) != null){ menuController += "Left(1)";} // left
                    if(g.coordsMovements().get(1) != null){ menuController += "\tTop(2)";} // top
                    if(g.coordsMovements().get(2) != null){ menuController += "\tRight(3)";} // right
                    if(g.coordsMovements().get(3) != null){ menuController += "\tBottom(4)";} // bottom

                    System.out.println(menuController);
                    System.out.print("Scelta => ");
                    movement = input.nextInt();

                    if(g.swife(movement)){ return; }
                    System.out.println("Non puoi effettuare questa mossa, supera i margini della griglia!");
                }
                else{
                    System.out.println("\n\n\t\t 15 Puzzle Grid");
                    System.out.println("\n" + g.toString());
                    System.out.println("Congratulazioni, hai vinto!");
                }

            break;

            case '2':
                g.reset();
                System.out.println("Griglia resettata!");
            break;

            case '3':
                System.out.println("Indica il numero di mosse da randomizzare => ");
                g.shuffle(input.nextInt());
            break;

            case '0':
                System.out.println("Grazie per aver giocato :)");
            break;

            default:
                System.out.println("La tua scelta non è valida, riprova!");
            break;
        }
    }
}
