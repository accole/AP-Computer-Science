/*
 Recursion fun

 By, Adam Cole
 
 */
package recursion;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Recursion {

    public static void main(String[] args) {
        //ImageIcon
        ImageIcon warp = new ImageIcon(Recursion.class.getResource("warp.gif"));
        //Array of Options
        String[] options = {"Factorial", "Sum", "Fibonacci",
            "Integer to Binary", "Binary to Integer", "EXIT"};
        int option;
        //have the default option change to whichever button the user last
        //selected
        int w = 5;
        do {
            //menuchoice
            option = JOptionPane.showOptionDialog(null,
                    "Choose a program to run :)", //message
                    "Menu of Recursion Programs :) By, Adam Cole", //title
                    JOptionPane.NO_OPTION, //option type
                    JOptionPane.INFORMATION_MESSAGE,//message type
                    warp, //icon/gif
                    options, //list of options
                    options[w]);  //default option

            switch (option) {
                case 0:
                    //factorial recursive code
                    int f;
                    w = 0;
                    do {
                        f = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the "
                                + "'n' value to which you would like to factorial", "FACTORIAL",
                                JOptionPane.QUESTION_MESSAGE));
                    } while (f < 0);
                    JOptionPane.showMessageDialog(null, factorial(f));
                    break;
                case 1:
                    //sum recursive code
                    int n;
                    w = 1;
                    do {
                        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the "
                                + "'n' value to which you would like to sum", "SUM",
                                JOptionPane.QUESTION_MESSAGE));
                    } while (n < 0);
                    JOptionPane.showMessageDialog(null, Sum(n));
                    break;
                case 2:
                    //fibbonacci recursive code
                    int z;
                    w = 2;
                    do {
                        z = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the "
                                + "'n' value of the fibbonnacci term you want", "FIBBONACCI",
                                JOptionPane.QUESTION_MESSAGE));
                    } while (z < 0);
                    JOptionPane.showMessageDialog(null, fib(z));
                    break;
                case 3:
                    //integer to binary recursive code
                    w = 3;
                    JOptionPane.showMessageDialog(null, Int2bin(Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Enter an Integer to be \nconverted to a Binary code", "Binary to Integer",
                            JOptionPane.QUESTION_MESSAGE))));
                    break;
                case 4:
                    //binary to integer recursive code
                    w = 4;
                    JOptionPane.showMessageDialog(null, bin2Int(JOptionPane.showInputDialog(null,
                            "Enter the binary code to be \nconverted to an Integer", "Integer to Binary",
                            JOptionPane.QUESTION_MESSAGE)));
                    break;
            }
        } while (option != -1 && option != 5);
        //Exit code
        JOptionPane.showMessageDialog(null, "Goodbye...Have a nice day! :)");
    }

    public static long Sum(int n) {
        //Precondition: n >= 0
        //Postcondition: returns the sum of the integers from 0 to n
        if (n <= 0) {
            return 0;
        } else {
            return n + Sum(n - 1);
        }
    }

    public static long factorial(int n) {
        //Precondition: n >= 0
        //Postcondition: returns n! (factorial)
        if (n <= 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static int fib(int n) {
        //Precondition: n >= 0
        //Postcondition: returns the "n"th term in the fibonacci sequence
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static long bin2Int(String binary) {
        //Precondition: String of 1's and 0's
        //Postcondition: converted integer returned
        if (binary.length() == 1) {
            return (long) (binary.charAt(0) - '0');
        } else {
            return (long) ((binary.charAt(0) - '0') * Math.pow(2, binary.length() - 1)
                    + bin2Int(binary.substring(1)));
        }
    }

    public static String Int2bin(int n) {
        //Precondition: n >= 0
        //Postcondition: returns a string of 1's and 0's equal to int n in binary
        if (n == 0) {
            return "0";
        } else if (n == 1) {
            return "1";
        } else {
            return Int2bin(n / 2) + (n % 2);
        }
    }

    public static String Count(int n) {
        //Precondition: n >= 1
        //Postcondition: returns the string "1 + 2 + 3 + 4 + .... + n"
        if (n == 1) {
            return "1";
        } else {
            return Count(n - 1) + "+" + n;
        }
    }
}
