import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] a){
        Scanner reader = new Scanner(System.in);
        System.out.println("~~~~~~THICC-TAcC-T0E~~~~~~");
        System.out.println("by xxXX-5aV@9eL1c|0us-XXxx");
        System.out.println();

        Board brd = new Board();
        Random r = new Random();
        boolean end=false;
        boolean who = r.nextBoolean();
        while(true){
            if(who) {
                System.out.println("Player1's move!");
                brd.printBrd();
                if(brd.playerMove()) who=!who;;
            } else{
                System.out.println("CPU1's move!");
                brd.aiMove();
                who=!who;
            }
            brd.printBrd();
            System.out.println();
            switch(brd.scanwin()){
                case 1 :{
                    System.out.println("CPU1 WINS!!!!1!!");
                    end=true;
                    break;
                }
                case 2:{
                    System.out.println("PLAYER1 WINS!!!!1!!");
                    end=true;
                }
            }
            if(brd.scanbrd()){
                System.out.println("IT'S A DRAW!!!!1!!");
                end=true;
            }
            if(end) break;
        }
        System.out.println("   GAME  OVER");
    }
}
