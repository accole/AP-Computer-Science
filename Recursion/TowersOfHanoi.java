/*
 * Adam Cole 03/09/2017
 *
 * Solves the Towers of Hanoi Puzzle using recursion - Study for AP Test
 *
 */
package towersofhanoi;

import javax.swing.JOptionPane;

public class HanoiMeTemplate {

    public static void main(String[] args) {
        //Get the number of rings
        int n = Integer.parseInt(JOptionPane.showInputDialog("How Many Disks to "
                + "move from Peg 1 to Peg 3?"));
        // Call HanoiThem for n
        if (n == 0) {
            System.out.println("No moves neccesary");
        } else {
            HanoiThem(n, 1, 2, 3);
        }
    }

    private static void HanoiThem(int numdisks, int start, int end, int by) {
        // this will move numdisk disks from start to end using the by peg
        // it will display the moves as you go 
        if (numdisks > 0) {
            HanoiThem(numdisks - 1, start, by, end);
            System.out.println(start + " to " + end);
            HanoiThem(numdisks - 1, by, end, start);
        }
    }
}
