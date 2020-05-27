package CONT;

import java.util.List;
import MOD.*;
import VIEW.Window;
import java.util.ArrayList;

public class Board {
    // INSTANCE VARIABLES
    private Window _w;
    private int right;
    private int wrong;

    // VARIABLES PERTAINING TO THE PANEL
    private static final String[] BUTTONS = { "Higher", "Lower", "EXIT" };
    private static final int BOARD_SIZE = 1;

    // VARIABLES PERTAINING TO CARDS
    private static final String[] RANKS = {"ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs"};
    private static final int[] POINT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private Card[] cards;
    private Deck deck;

    // CONSTRUCTOR
    public Board() {
        _w = new Window();
        cards = new Card[BOARD_SIZE];
        deck = new Deck(RANKS, SUITS, POINT_VALUES);
        newGame();
        Game();
    }

    // ACCESSOR METHODS
    public int size() {     return cards.length;    }

    public int deckSize() {     return deck.size();     }

    public Card cardAt(int k) {     return cards[k];    }

    /**
     * This method takes care of running the game:
     *  - the introduction
     *  - While the game is being played, it deals with the points. If the person gets it right, they gain a point;
     *    also checks for if the player has won after getting five right. If the person get it wrong, they get a
     *    strike; also check for if the player has three strikes before they lose.
     *  - the messages for indicating the point/strike they have gain
     *  - exit option
     */
    public void Game() {
        _w.msg("Welcome!!!!!!"
                + "\n - Rules: Guess if the next card is lower or higher than the one before"
                + "\n - If you guess five cards correctly, you win!!"
                + "\n - If you guess three cards incorrectly, you lose!!"
                + "\n - LET'S BEGIN THE GAME OF HIGH OR LOW!!!!");

        while (true) {
            Card y = deck.deal();
            int x = _w.option(BUTTONS, y);
            Card z = deck.deal();
            if(x == 0) {
                if(y.getPointValue() < z.getPointValue()) {
                    right += 1;
                    _w.msg("The following card is: " + z
                            + "\nCorrect!!"
                            + "\nRight: " + right + "Wrong: " + wrong);
                    right();
                }
                else if(y.getPointValue() == z.getPointValue()) {
                    _w.msg("The following card is: " + z
                            + "\nSame value!!! Let's keep going."
                            + "\nRight: " + right + "Wrong: " + wrong);
                }
                else {
                    wrong += 1;
                    _w.msg("The following card is: " + z
                            + "\nSorry you got it wrong!! Better luck next time."
                            + "\nRight: " + right + "Wrong: " + wrong);
                    wrong();
                }
            }
            else if(x == 1) {
                if(y.getPointValue() > z.getPointValue()) {
                    right += 1;
                    _w.msg("The following card is: " + z
                            + "\nCorrect!!"
                            + "\nRight: " + right + "Wrong: " + wrong);
                    right();
                }
                else if(y.getPointValue() == z.getPointValue()) {
                    _w.msg("The following card is: " + z
                            + "\nSame value!!! Let's keep going."
                            + "\nRight: " + right + "Wrong: " + wrong);
                }
                else {
                    wrong += 1;
                    _w.msg("The following card is: " + z
                            + "\nSorry you got it wrong!! Better luck next time."
                            + "\nRight: " + right + "Wrong: " + wrong);
                    wrong();
                }
            }
            else{
                System.exit(0);
            }
        }
    }

    /**
     * This method starts a new game by shuffling and dealing the cards out.
     */
    public void newGame() {
        deck.shuffle();
        dealMyCards();
    }

    /**
     * This method check to see if the board is empty.
     * @return true if the board is empty, false otherwise.
     */
    public boolean isEmpty() {
        for (int i = 0; i < cards.length; i++)
            if (cards[i] != null)
                return false;

        return true;
    }

    /**
     * This method deal cards at the start of the game.
     */
    private void dealMyCards() {
        for (int i = 0; i < cards.length; i++)
            cards[i] = deck.deal();
    }

    /**
     * This method deals a card to a specific position in the board. If the deck is empty, the specific card is null.
     * @param i the index of the card to be dealt.
     */
    public void deal(int i) {
        cards[i] = deck.deal();
    }

    /**
     * This method replaces cards that are selected on the board by dealing new cards.
     * @param selectedCards is a list of the indices of the cards to be replaced.
     */
    public void replaceSelectedCards(List<Integer> selectedCards) {
        for(int i = 0; i < selectedCards.size(); i++)
            deal(selectedCards.get(i).intValue());
    }

    /**
     * This method gets the index cards on the board.
     * @return selected is a list that contains the indexes of the cards on the board.
     */
    public List<Integer> cardIndexes() {
        List<Integer> selected = new ArrayList<>();

        for (int i = 0; i < cards.length; i++)
            if (cards[i] != null)
                selected.add(new Integer(i));

        return selected;
    }

    /**
     * This method focuses on telling the player the game is done after getting three turns incorrect.
     */
    public void wrong() {
        if(wrong == 3) {
            _w.msg("Start Over :(");
            right = 0; // reset the values
            wrong = 0;
        }
    }

    /**
     * This method focuses on telling the player they won after getting five turns correct.
     */
    public void right() {
        if(right == 5) {
            _w.msg("YOU WIN :)");
            right = 0; // reset the values
            wrong = 0;
        }
    }

    /**
     * This method determine whether the game has been won or lost.
     * @return true when the player have won; false otherwise.
     */
    public boolean gameIsWon() {
        if (deck.isEmpty()) {
            for(int i = 0; i < cards.length; i++)
                if (cards[i] != null)
                    return false;

            return true;
        }
        return false;
    }

    /**
     * This method creates and returns a string form of the board.
     * @return a string representation of the board.
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < cards.length; i++) {
            s += i + ": " + cards[i] + "\n";
        }
        return s;
    }
}