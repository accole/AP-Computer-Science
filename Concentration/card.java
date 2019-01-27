* Card.java */

import java.util.Random;

public class Card implements Comparable {

    // Declare the Private Data
    private int myValue;            // 1-13 (1 Ace, 11 Jack,...)
    private int mySuit;             // 1 club, 2 spade, 3 heart, 4 diamond
    private Random gen = new Random();

    // Constructors
    public Card() {
        myValue = gen.nextInt(13) + 1;
        mySuit = gen.nextInt(4) + 1;
    }

    public Card(int value, int suit) {
        myValue = value;
        mySuit = suit;
    }

    // The public methods
    public int getFaceValue() {
        return myValue;
    }

    public int getCardValue() {
        if (myValue >= 10) // 10, J, Q, K
        {
            return 10;
        } else if (myValue == 1) // Ace
        {
            return 11;
        } else {
            return myValue;
        }
    }

    public int getSuit() {
        return mySuit;
    }

    public String toString() {
        String tmp = "";
        if (myValue == 1) {
            tmp = "Ace";
        } else if (myValue == 11) {
            tmp = "Jack";
        } else if (myValue == 12) {
            tmp = "Queen";
        } else if (myValue == 13) {
            tmp = "King";
        } else {
            tmp += myValue;
        }

        if (mySuit == 1) {
            tmp += " of Clubs";
        } else if (mySuit == 2) {
            tmp += " of Spades";
        } else if (mySuit == 3) {
            tmp += " of Hearts";
        } else {
            tmp += " of Diamonds";
        }
        return tmp;
    }

    public int compareTo(Object obj) {
        Card c = (Card) obj;
        int myval = this.getFaceValue();
        if (myval == 1) {
            myval = 14;         // Ace
        }
        int cval = c.getFaceValue();
        if (cval == 1) {
            cval = 14;           // Ace
        }
        return myval - cval;
    }

    public boolean sameCardValue(Card c) {
        return this.getCardValue() == c.getCardValue();
    }

    public boolean sameFaceValue(Card c) {
        return this.getFaceValue() == c.getFaceValue();
    }

    public boolean sameSuit(Card c) {
        return this.getSuit() == c.getSuit();
    }
}
