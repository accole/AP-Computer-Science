/*
 MEMORY CARDS GAME
 -----------------
 This game will utilize a deck, stopwatch, and a card class to display the user with a deck 
 of overturned cards, and two cards are selected by the user.  If the two cards are
 the same, they disappear.  But if they are differnt, both cards overturn again.
 The game continues until there is no more cards left and the timer stops.
 -----------------
 Created in class 5/16/17 by Adam Cole
 */

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MemoryCards {

    //Private Global Data - gameboard
    private static final Card[][] gameboard = new Card[4][7];

    public static void main(String[] args) {
        // Welcome Screen
        ImageIcon cards = new ImageIcon(MemoryCards.class.getResource("cards.jpg"));
        JOptionPane.showMessageDialog(null, "Welcome to Memory Cards!\n\nPair up"
                + " the cards as fast as you can!  \nNot all cards will have a pair.  "
                + "\n\nReady to Play?", "Memory Cards:  By, Adam Cole",
                JOptionPane.INFORMATION_MESSAGE, cards);
        //Initialize objects
        Stopwatch s = new Stopwatch();
        Deck d = new Deck();
        //record
        long record = 0;
        String recorddisplay = null;
        // Playagain Option
        int playagain = 0;
        do {
            //Shuffle the deck and place the cards in gameboard
            d.shuffle();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 7; j++) {
                    gameboard[i][j] = d.DealCard();
                }
            }
            //Start the stopwatch
            s.Start();
            //Play the game until there are no more pairs on the board
            while (RemainingPairs(gameboard) != 0) {
                //Display the remaining cards with numbers on them
                //get first selected card from the user and display
                int choice1;
                do {
                    choice1 = Integer.parseInt(JOptionPane.showInputDialog(
                            DisplayBoard(gameboard) + "\n\nEnter a "
                            + "card number to reveal the card! There are\n"
                            + RemainingPairs(gameboard) + " pairs left"));
                } while (choice1 < 1 || choice1 > 28);
                Card c1 = gameboard[(choice1 - 1) / 7][(choice1 - 1) % 7];
                JOptionPane.showMessageDialog(null, c1.toString(), "First Card",
                        JOptionPane.INFORMATION_MESSAGE, d.getIcon(c1));
                //get the second card from the user and test if a pair
                int choice2;
                do {
                    choice2 = Integer.parseInt(JOptionPane.showInputDialog(
                            DisplayBoard(gameboard) + "Enter the "
                            + "card number of the pair for your " + c1.toString()));
                } while ((choice2 < 1 || choice2 > 28) && (choice2 != choice1));
                Card c2 = gameboard[(choice2 - 1) / 7][(choice2 - 1) % 7];
                //if they are the same, tell the user and remove the cards
                if (c1.sameFaceValue(c2)) {
                    gameboard[(choice1 - 1) / 7][(choice1 - 1) % 7] = null;
                    gameboard[(choice2 - 1) / 7][(choice2 - 1) % 7] = null;
                        JOptionPane.showMessageDialog(null, "Congrats! You paired the cards!",
                                "There are " + RemainingPairs(gameboard) + " pairs left!!",
                                JOptionPane.INFORMATION_MESSAGE);
                    //else tell them they are wrong
                } else {
                    JOptionPane.showMessageDialog(null, "You drew a " + c2.toString()
                            + ".  Those cards do not match.  Try again.", "Second Card",
                            JOptionPane.INFORMATION_MESSAGE, d.getIcon(c2));
                }
            }
            //Stop the timer and display the time
            s.Stop();
            JOptionPane.showMessageDialog(null, "You paired up the cards in "
                    + s.toString() + "!!");
            //update the record
            if (record == 0) {
                record = s.totaltime();
                recorddisplay = s.toString();
                JOptionPane.showMessageDialog(null, "Your new highscore is: "
                        + recorddisplay);
            } else if (record > s.totaltime()) {
                record = s.totaltime();
                recorddisplay = s.toString();
                JOptionPane.showMessageDialog(null, "Your new highscore is: "
                        + recorddisplay);
            } else {
                JOptionPane.showMessageDialog(null, "You did not beat your old "
                        + "highscore of: " + recorddisplay);
            }
            //playagain
            playagain = JOptionPane.showConfirmDialog(null, "Want to play Memory "
                    + "Cards again?", "Play again?", JOptionPane.YES_NO_OPTION);
        } while (playagain == JOptionPane.YES_OPTION);
        //Exit message
        JOptionPane.showMessageDialog(null, "Have a nice day!");
    }

    public static int RemainingPairs(Card[][] c) {
        //Precondition: c is initialized
        //Postcondition: returns the number of remaining pairs in the gameboard
        //create a one dimensional array for each card value
        int[] occurances = new int[13];
        int pairs = 0;
        //initialize all card occurances to 0
        for (int i = 0; i < occurances.length; i++) {
            occurances[i] = 0;
        }
        //loop through and increment occurances for each value
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                if (c[i][j] != null) {
                    occurances[c[i][j].getFaceValue() - 1]++;
                }
            }
        }
        //find the number of pairs by dividing the occurances of each card by 2
        for (int i = 0; i < occurances.length; i++) {
            pairs += occurances[i] / 2;
        }
        return pairs;
    }

    public static String DisplayBoard(Card[][] c) {
        String board = "";
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                if (c[i][j] == null) {
                    board += "  [     ]  ";
                } else {
                    int d1 = i * 7 + j + 1;
                    if (d1 < 10) {
                        board += "  [" + 0 + d1 + "]  ";
                    } else {
                        board += "  [" + d1 + "]  ";
                    }
                }
            }
            board += "\n\n";
        }
        return board;
    }
}
