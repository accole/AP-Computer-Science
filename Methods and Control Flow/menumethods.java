/*
 Menu project with methods
 Adam Cole
 11/8/16
 */
package menumethodsnotes;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MenuMethodsNotes {

    public static void main(String[] args) {
        ImageIcon equations = new ImageIcon(MenuMethodsNotes.class.getResource("equations.jpg"));
        String[] options = {"Palindrome Tester", "Dash Remover", "Space Counter",
            "Encoder", "Decoder", "EXIT"};
        int option;
        do {
            option = JOptionPane.showOptionDialog(null,
                    "Choose a program to run :)", //message
                    "Menu of Programs :) By, Adam Cole", //title
                    JOptionPane.NO_OPTION, //option type
                    JOptionPane.INFORMATION_MESSAGE,//message type
                    equations, //icon
                    options, //list of options
                    options[5]);                    //default option
            switch (option) {
                case 0:
                    //palindrome tester
                    String word = JOptionPane.showInputDialog("Enter a word to check "
                            + "if it is a palindrome");
                    if (isPalindrome(word)) {
                        JOptionPane.showMessageDialog(null, word + " is a palindrome");
                    } else {
                        JOptionPane.showMessageDialog(null, word + " is not a palindrome");
                    }
                    break;
                case 1:
                    //removeDashes
                    String numbwdash = JOptionPane.showInputDialog("Enter a "
                            + "number with dashes");
                    JOptionPane.showMessageDialog(null, "Your number without "
                            + "dashes is: \n" + removeDashes(numbwdash));
                    break;
                case 2:
                    //countSpaces
                    String swspaces = JOptionPane.showInputDialog("Enter a sentence: ");
                    JOptionPane.showMessageDialog(null, "There are " + countSpaces(swspaces)
                            + " spaces in that sentence.");
                    break;
                case 3:
                    //Encode
                    String encode = JOptionPane.showInputDialog(null, "enter a "
                            + "word or phrase to encode\n(use _ for spaces)\n(! marks and"
                            + " quotation marks will be omitted)", "encode a "
                            + "word", JOptionPane.INFORMATION_MESSAGE);
                    JOptionPane.showMessageDialog(null, "your encoded word is: "
                            + encodeWord(encode, 3));
                    break;
                case 4:
                    //Decode
                    String decode = JOptionPane.showInputDialog("enter an encoded "
                            + "word to decode");
                    JOptionPane.showMessageDialog(null, "your decoded word is: "
                            + decodeWord(decode, 3));
                    break;
            }
        } while (option != -1 && option != 5);
        JOptionPane.showMessageDialog(null, "Goodbye...Have a nice day! :)");
    }

    private static boolean isPalindrome(String word) {
        String backwards = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            backwards += word.charAt(i);
        }
        return word.equalsIgnoreCase(backwards);
    }

    private static String removeDashes(String word) {
        String numnodash = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == '-') {
                numnodash += "";
            } else {
                numnodash += word.charAt(i);
            }
        }
        return numnodash;
    }

    private static int countSpaces(String word) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == ' ') {
                count++;
            }
        }
        return count;
    }

    private static String encodeWord(String word, int n) {
        //create a backwards word with each character n values less
        String back = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            back += (char) (word.charAt(i) - n);
        }
        return back;
    }

    private static String decodeWord(String word, int n) {
        String real = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            real += (char) (word.charAt(i) + n);
        }
        return real;
    }
}
