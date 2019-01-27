/* Deck.java
 *
 *
 */


import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;
import javax.swing.ImageIcon;


public class Deck {

    // --------------------- Private Data
    private int numCards;
    private Card[] myDeck;
    private int nextCard;
    private int numDecks;
    private ImageIcon[] DeckIcons;

    // Constructor - create a deck with 52*numdecks cards
    public Deck() {
        numDecks = 1;
        numCards = 52;
        initialize();
        shuffle();
    }
    
    

    public Deck(int decks) {
        if (decks <= 1) {
            numDecks = 1;
        } else {
            numDecks = decks;
        }
        numCards = numDecks * 52;
        initialize();
        shuffle();
    }



    public Card DealCard() {
        // This will return the next card in the deck.  If the deck is empty
        // it will shuffle the deck and start over.
        // Precondition:    The deck is initialized
        // Postcondition:   Returns a card and possibly shuffles the deck
        if (nextCard == numCards) {
            shuffle();
        }
        return myDeck[nextCard++];
    }



    public void shuffle() {
        // This will shuffle the cards in the deck by randomly switching two
        // cards in the deck for a fixed number of times.
        // Preconditions:   myDeck is initialized with new Cards
        // Postconditions:  the deck is "shuffled" and nextCard is 0
        Random gen = new Random();
        for (int i = 1; i < 200; i++) {
            int loc1 = gen.nextInt(numCards);
            int loc2 = gen.nextInt(numCards);
            Card tmp = myDeck[loc1];
            myDeck[loc1] = myDeck[loc2];
            myDeck[loc2] = tmp;
        }
        nextCard = 0;
    }



    public ImageIcon getIcon(Card c) {
        // This will return the ImageIcon associated with the Card
        int val = c.getFaceValue();
        int iconloc;
        if (val > 1) // images are from K to 2 so reverse
        {
            iconloc = (14 - val) * 4 + c.getSuit();
        } else // 1 is an ace in both
        {
            iconloc = c.getSuit();
        }

        return DeckIcons[iconloc - 1];
    }



    public int remainingCards() {
        // This will return the number of remaining cards in the deck
        return numCards - nextCard;
    }



    public String toString() {
        String tmp = "---" + " Decks " + numDecks + "---\n";
        for (int i = nextCard; i < numCards; i++) {
            tmp += myDeck[i].toString() + "\n";
        }
        tmp += "\n----------------\n";
        return tmp;
    }



    private void initialize() {
        // This will add NUMCARDS unique cards to the deck and initialize the image
        // icon array with 52 images
        // Precondition:
        // Postcondition:   myDeck has NUMCARDS added
        //                  images has been initialize with 52 pictures
        myDeck = new Card[numCards];
        int count = 0;
        for (int d = 1; d <= numDecks; d++) {
            for (int v = 1; v <= 13; v++) {
                for (int s = 1; s <= 4; s++) {
                    myDeck[count++] = new Card(v, s);
                }
            }
        }



        // images - 1-4 ace, 5-8 kings, 9-12 queens, ... 49-52 twos
        ClassLoader cloader = Deck.class.getClassLoader();
        DeckIcons = new ImageIcon[52];
        for (int i = 0; i < 52; i++) {
            String filename = (i + 1) + ".png";
            Image img = Toolkit.getDefaultToolkit().getImage(cloader.getResource(filename));
            DeckIcons[i] = new ImageIcon(img);

        }
    }
}
