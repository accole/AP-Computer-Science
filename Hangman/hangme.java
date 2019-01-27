/*
 Hangman program
 11/3/16
 Adam Cole
 */

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class hangme {

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        ImageIcon noose = new ImageIcon(hangme.class.getResource("noose.gif.gif"));
        JOptionPane.showMessageDialog(null, "Welcome to hangman!",
                "Hangman, by Adam Cole", JOptionPane.INFORMATION_MESSAGE, noose);

        String[] words = {"madlibs", "fatfriday", "computer", "boolean", "algebra",
            "hangman", "java", "netbeans", "applet", "picture", "kilobyte", "computer",
            "xbox", "hexadecimal", "octal", "proof", "integer", "double", "ascii", "loop",
            "exit", "charlie", "aidan", "You're Amazing :)", "calulus", "MIT", "methods",
            "googleplex", "integral", "polynomial"};

        ClassLoader cloader = hangme.class.getClassLoader();
        ImageIcon[] images = {
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang1.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang2.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang3.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang4.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang5.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang6.gif"))),
            new ImageIcon(Toolkit.getDefaultToolkit().getImage(cloader.getResource("Hang7.gif")))
        };
        int playagain = 0;
        do {
            String randword = words[(int) (Math.random() * words.length)];
            int guesses = 7;
            int wrongguesses = 0;
            String dashes = "";
            for (int x = 1; x <= randword.length(); x++) {
                dashes += "*";
            }
            String missletters = "";
            String guesst = null;
            char guess;
            String newdashes = "";
            do {
                guesst = JOptionPane.showInputDialog(null, "Hangman: "
                        + dashes + "\n\nGuessed Letters: " + missletters + "\n\nGuess"
                        + " a letter:", "Guesses remaining: " + guesses,
                        JOptionPane.INFORMATION_MESSAGE);
                guess = guesst.charAt(0);
                if (randword.indexOf(guess) >= 0) {
                    for (int i = 0; i < randword.length(); i++) {
                        if (randword.charAt(i) == guess) {
                            newdashes += guess;
                        } else {
                            newdashes += dashes.charAt(i);
                        }
                    }
                    dashes = newdashes;
                    newdashes = "";
                } else {
                    wrongguesses += 1;
                    guesses -= 1;
                    missletters += guess + " ";
                    JOptionPane.showMessageDialog(null, "", "",
                            JOptionPane.INFORMATION_MESSAGE, images[wrongguesses - 1]);
                }
            } while (guesses != 0 && !dashes.equals(randword));
            if (guesses == 0) {
                JOptionPane.showMessageDialog(null, "Game Over :("
                        + "\nThe secret word was: " + randword, "Nice Try",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nice!  you guessed the "
                        + "correct word: " + randword,
                        "Congradulations!", JOptionPane.INFORMATION_MESSAGE);
            }
            playagain = JOptionPane.showConfirmDialog(null, "Would you like to "
                    + "play again?", "Select Yes or No", JOptionPane.YES_NO_CANCEL_OPTION);
        } while (playagain == JOptionPane.YES_OPTION);
        if (playagain == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Thanks for playing!", "Come again :)",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
