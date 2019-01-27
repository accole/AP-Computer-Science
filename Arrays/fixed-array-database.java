/* Arrays Part I - Built in Java Arrays
 * 
 * Created by Adam Cole In-class 12/8/2016
 *
 * Design Notes: Since built-in arrays are fixed in length, there are several ways
 * to write this code - one way would be to keep a maximum number values and then
 * kept track of the currently used number of scores. Another way to do this, is to 
 * have the menuoption 1 to create a new array every time and to get rid of the 
 * numScores and use .length() method for built in arrays.  We will use that method.
 * 
 * Also, note that Scores is a Global Variables that the methods access - not always the
 * best programming practice but acceptable in object oriented programming.  A 
 * better way might be to define them local to main, and send them as parameters
 * into the methods.
 */

import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class TestScores {

    // Global Private Data - Do not add any other data here for this project
    private static int[] Scores = new int[0];

    public static void main(String[] args) {
        // Declare local variables
        ImageIcon equations = new ImageIcon(TestScores.class.getResource("equations.jpg"));
        DecimalFormat dec = new DecimalFormat(".#");
        String menuchoice;
        String[] MenuValues = {"1. Imput Test Scores", "2. Score Chart", "3. Compute "
            + "Average", "4. Compute Highest", "5. Compute Letter Grades", "6. Exit"};
        int defaultoption = 0;

        do {
            // Display the menu and get the user menuchoice
            menuchoice = (String) JOptionPane.showInputDialog(null,
                    "What would you like to do?", "Grade analyzer",
                    JOptionPane.QUESTION_MESSAGE,
                    equations,
                    MenuValues,
                    MenuValues[defaultoption]);

            // call the methods
            if (menuchoice.equals(MenuValues[0])) {
                // Get n from user and call GetTestScores
                int n;
                do {
                    n = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Tests: "));
                } while (n <= 0);
                GetTestScores(n);
                defaultoption = 1;
            } else if (menuchoice.equals(MenuValues[1])) {
                // Display the chart
                JOptionPane.showMessageDialog(null, DisplayinChart());
            } else if (menuchoice.equals(MenuValues[2])) {
                //Compute Average
                JOptionPane.showMessageDialog(null, "The average is "
                        + ComputeAverage() + "%");
            } else if (menuchoice.equals(MenuValues[3])) {
                if (Scores.length != 0) {
                    //Compute the highest grade
                    JOptionPane.showMessageDialog(null, "The highest score is "
                            + HighestScore() + "%");
                } else {
                    JOptionPane.showMessageDialog(null, "There is no highest score");
                }
            } else if (menuchoice.equals(MenuValues[4])) {
                //Compute the Letter Grades of Each Test
                JOptionPane.showMessageDialog(null, ComputeGrades());
            } else {
                //EXIT
            }
        } while (!menuchoice.equals(MenuValues[MenuValues.length - 1]));
        // Exit Code nicely
        JOptionPane.showMessageDialog(null, "Have a nice day!");
    }



    // -------------------------------------------------------------------------
    // Private Methods
    
    
    
    // Enter the Test Scores from the user - you will need an input call
    private static void GetTestScores(int n) {
        // Define the array to store the numbers
        Scores = new int[n];
        for (int i = 0; i < n; i++) {
            Scores[i] = Integer.parseInt(JOptionPane.showInputDialog("What is the "
                    + "test score for test number " + (i + 1)));
        }
    }



    // Display the Test Scores in a nice Chart - NO Input/Output (IO)
    private static String DisplayinChart() {
        String chart = "";
        for (int i = 0; i < Scores.length; i++) {
            chart += "Test #" + (i + 1) + "         Score: " + Scores[i] + "%\n";
        }
        return chart;
    }



    // Return the average of the test scores - NO Input/Output (IO)
    private static double ComputeAverage() {
        double totalscore = 0;
        double average = 0;
        for (int i = 0; i < Scores.length; i++) {
            totalscore += Scores[i];
        }
        if (Scores.length == 0) {
            average = 0.0;
        } else {
            average = totalscore / Scores.length;
        }
        return average;
    }



    // Return the Highest Score in the list - NO Input/Output (IO)
    private static int HighestScore() {
        int highest = Scores[0];
        for (int i = 0; i < Scores.length; i++) {
            if (highest >= Scores[i]) {
            } else {
                highest = Scores[i];
            }
        }
        return highest;
    }



    // Display the number of A's, B's, C's, D's, and F's - NO Input/Output (IO)
    private static String ComputeGrades() {
        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;
        int F = 0;
        for (int i = 0; i < Scores.length; i++) {
            if (Scores[i] >= 90) {
                A++;
            } else if (Scores[i] >= 80) {
                B++;
            } else if (Scores[i] >= 70) {
                C++;
            } else if (Scores[i] >= 60) {
                D++;
            } else {
                F++;
            }
        }
        String grades = "A: " + A + "\nB: " + B + "\nC: " + C + "\nD: " + D + "\nF: " + F;
        return grades;
    }
}
