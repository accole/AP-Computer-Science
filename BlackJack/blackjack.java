/*
 * Adam Cole
 * 11/17/16
 * Blackjack
 */

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Game {

    public static void main(String[] args) {
        String[] options = {"Hit me", "Stand"};
        int playagain = 0;
        long bet = 0;
        long balance = 0;
        int value = 0;
        int value1 = 0;
        int totalplayer = 0;
        int totaldealer = 0;
        int hitme = 0;
        //Intro screen
        ImageIcon blackjack = new ImageIcon(Game.class.getResource("HangoverBlackjack.jpg"));
        JOptionPane.showMessageDialog(null, "Welcome to Blackjack!\n\nThe object "
                + "is to reach as close to 21 without going over.  Good Luck :)\n"
                + "\nReady to play?", "Blackjack", JOptionPane.INFORMATION_MESSAGE, blackjack);
        //get the currency
        String currency = JOptionPane.showInputDialog(null, "What currency will you be placing"
                + " your bets in?", "Enter a valid currency", JOptionPane.INFORMATION_MESSAGE);
        do {
            //create a new deck
            Deck cards = new Deck();
            //shuffle the deck
            cards.shuffle();
            //reset the totals
            totalplayer = 0;
            boolean gameover = false;
            //get the bet
            //check to make sure it's real
            do {
                bet = Long.parseLong(JOptionPane.showInputDialog(null, "Current Balance : "
                        + balance + " " + currency + "\nHow many " + currency
                        + " would you like to bet?", "Enter your bet", JOptionPane.INFORMATION_MESSAGE));
            } while (bet < 0);
            //deal two cards to the player and the dealer
            //player and display both
            for (int x = 1; x <= 2; x++) {
                Card c = cards.DealCard();
                JOptionPane.showMessageDialog(null, "You drew "
                        + "a: ", "Card number " + x,
                        JOptionPane.INFORMATION_MESSAGE, cards.getIcon(c));
                value = c.getCardValue();
                totalplayer += value;
            }
            //dealer and display first only
            for (int x = 1; x <= 2; x++) {
                Card c = cards.DealCard();
                if (x == 1) {
                    JOptionPane.showMessageDialog(null, "Dealer drew: ", "Card number " + x,
                            JOptionPane.INFORMATION_MESSAGE, cards.getIcon(c));
                    value1 = c.getCardValue();
                    totaldealer += value1;
                } else {
                    JOptionPane.showMessageDialog(null, "Dealer's second draw is hidden",
                            "Card number " + x, JOptionPane.INFORMATION_MESSAGE);
                    value = c.getCardValue();
                    totaldealer += value;
                }
            }
            //correct the potential double aces as 11 and 1
            if (totaldealer == 22) {
                totaldealer = 12;
            }
            if (totalplayer == 22) {
                totalplayer = 12;
            }
            //check for blackjack
            if (totaldealer == 21) {
                JOptionPane.showMessageDialog(null, "Dealer Wins :(\nYou owe "
                        + bet + " " + currency, "Better "
                        + "luck next time!", JOptionPane.INFORMATION_MESSAGE);
                balance -= bet;
                gameover = true;
            } else if (totalplayer == 21) {
                JOptionPane.showMessageDialog(null, "You Win!! :)\nYou won "
                        + bet + " " + currency, "Blackjack!", JOptionPane.INFORMATION_MESSAGE);
                balance += bet;
                gameover = true;
            }
            if (gameover != true) {
                do {
                    hitme = JOptionPane.showOptionDialog(null, "Would you"
                            + " like another card?\nYour total is: " + totalplayer
                            + "\nThe dealer's total is " + value1 + " plus something.",
                            "Select Yes or No", JOptionPane.NO_OPTION,
                            JOptionPane.YES_NO_CANCEL_OPTION, null, options, options[1]);
                    switch (hitme) {
                        case 0:
                            Card c = cards.DealCard();
                            JOptionPane.showMessageDialog(null, "You drew "
                                    + "a: ", "Your card:",
                                    JOptionPane.INFORMATION_MESSAGE, cards.getIcon(c));
                            value = c.getCardValue();
                            totalplayer += value;
                    }
                } while (hitme != 1 && totalplayer < 21);
                if (totalplayer > 21) {
                    gameover = true;
                    balance -= bet;
                    JOptionPane.showMessageDialog(null, "You busted!\nYou owe "
                            + bet + " " + currency + ".", "Better Luck next time!",
                            JOptionPane.ERROR_MESSAGE);
                } else if (totalplayer == 21) {
                    gameover = true;
                    balance += bet;
                    JOptionPane.showMessageDialog(null, "You Win!! :)\nYou won "
                            + bet + " " + currency, "Blackjack!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (gameover != true) {
                do {
                    Card c = cards.DealCard();
                    JOptionPane.showMessageDialog(null, "Dealer drew "
                            + "a: ", "Dealer's card",
                            JOptionPane.INFORMATION_MESSAGE, cards.getIcon(c));
                    value = c.getCardValue();
                    totaldealer += value;
                } while (totaldealer <= 16);
                if (totaldealer > 21) {
                    JOptionPane.showMessageDialog(null, "Dealer busted!\nYou win "
                            + bet + " " + currency + ".", "You Win!", JOptionPane.INFORMATION_MESSAGE);
                    balance += bet;
                } else if (totaldealer == totalplayer) {
                    JOptionPane.showMessageDialog(null, "The game is a push.  You did "
                            + "not win or lose anything.", "Tie", JOptionPane.INFORMATION_MESSAGE);
                } else if (totaldealer > totalplayer) {
                    JOptionPane.showMessageDialog(null, "The dealer had more than you!\nYou owe "
                            + bet + " " + currency + ".", "Better luck next time!",
                            JOptionPane.INFORMATION_MESSAGE);
                    balance -= bet;
                } else {
                    JOptionPane.showMessageDialog(null, "You had more than the dealer!\nYou win "
                            + bet + " " + currency + ".", "You Win!", JOptionPane.INFORMATION_MESSAGE);
                    balance += bet;
                }
            }
            playagain = JOptionPane.showConfirmDialog(null, "Would you like to "
                    + "play again?", "Select Yes or No", JOptionPane.YES_NO_CANCEL_OPTION);
        } while (playagain == JOptionPane.YES_OPTION);
        if (playagain == JOptionPane.NO_OPTION) {
            if (balance > 0) {
                JOptionPane.showMessageDialog(null, "You won " + balance + " "
                        + currency + "!\n\nCome again soon!", "Nice job", JOptionPane.INFORMATION_MESSAGE);
            } else if (balance == 0) {
                JOptionPane.showMessageDialog(null, "You broke even! You won " + balance + " "
                        + currency + "!\n\nCome again soon!", "Nice job", JOptionPane.INFORMATION_MESSAGE);
            } else {
                balance = Math.abs(balance);
                JOptionPane.showMessageDialog(null, "You owe " + balance + " "
                        + currency + "!\n\nCome again soon!", "Better luck next time!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
