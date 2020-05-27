package MOD;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    // INSTANCE VARIABLES
    private List<Card> cards;
    private int size;

    // CONSTRUCTOR
    public Deck(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();

        for (int i = 0; i < ranks.length; i++)
            for(int j = 0; j < suits.length; j++)
                cards.add(new Card(ranks[i], suits[j], values[i]));

        size = cards.size();
        shuffle();
    }

    // ACCESSOR METHOD
    public int size() {     return size;    }

    /**
     * This method checks if the deck is empty.
     * @return true if deck is empty, false otherwise.
     */
    public boolean isEmpty() {      return size == 0;      }

    /**
     * This method randomly shuffles the deck of cards and reset the size to the entire deck.
     */
    public void shuffle() {     Collections.shuffle(cards);     }

    /**
     * This method deals a card from the deck.
     * @return the card just dealt, or null if the cards have been dealt.
     */
    public Card deal() {
        if (isEmpty())
            return null;

        size--;
        Card card = cards.get(size);
        return card;
    }

    /**
     * This method creates and returns a string form of this deck.
     * @return a string representation of this deck.
     */
    @Override
    public String toString() {
        StringBuilder rtn = new StringBuilder("size = " + size + "\nUndealt cards: \n");

        for (int i = size - 1; i >= 0; i--) {
            rtn.append(cards.get(i));
            if (i != 0)
                rtn.append(", ");

            if ((size - i) % 2 == 0)
                // Insert carriage returns so entire deck is visible on console.
                rtn.append("\n");
        }

        rtn.append("\nDealt cards: \n");
        for (int i = cards.size() - 1; i >= size; i--) {
            rtn.append(cards.get(i));
            if (i != size)
                rtn.append(", ");

            if ((i - cards.size()) % 2 == 0)
                // Insert carriage returns so entire deck is visible on console.
                rtn.append("\n");
        }

        rtn.append("\n");
        return rtn.toString();
    }
}