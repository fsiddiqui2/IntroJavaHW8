import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class checkHandTest {
    public static void main(String[] args){
        File f = new File("testCases.txt");
        Scanner in = null;
        try {
			in = new Scanner(f); 
		} catch(FileNotFoundException e){ 
			System.err.println(args[0] + " could not be found");
			System.exit(0);
        }

        int failed = 0;

        while(in.hasNextLine()){
            String cards = in.nextLine();
            String eval = in.nextLine();

            String[] hand = cards.split(" ");
            Player p = new Player();
            addCards(p, hand);

            Game game = new Game();
            String gameEval = game.checkHand(p.getHand());
            
            if (!eval.toLowerCase().equals(gameEval.toLowerCase())){
                System.out.println(p.getHand());
                System.out.println("Got: " + gameEval);
                System.out.println("Expected: " + eval + "\n");
                failed++;

            }
        }

        if(failed == 0){
            System.out.println("All test cases passed!");
        }
        else{
            System.out.println("Test cases failed: " + failed);
        }
        
    }

    private static void addCards(Player p, String[] testHand){
        for(String card : testHand){
                int suit;
                switch (card.charAt(0)){
                    case 'c':
                        suit = 1;
                        break;
                    case 'd':
                        suit = 2;
                        break;
                    case 'h':
                        suit = 3;
                        break;
                    default:
                        suit = 4;
                }

			int rank = Integer.parseInt(card.substring(1));

			Card c = new Card(suit, rank);
			p.addCard(c);
        }
    }
}
