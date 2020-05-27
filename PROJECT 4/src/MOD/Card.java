package MOD;

public class Card {

    // INSTANCE VARIABLES
    private String suit;
    private String rank;
    private int pointValue;

    // CONSTRUCTOR
    public Card(String cardRank, String cardSuit, int cardPointValue) {
        rank = cardRank;
        suit = cardSuit;
        pointValue = cardPointValue;
    }

    // ACCESSOR METHODS
    public String getSuit() {   return suit;    }

    public String getRank() {   return rank;    }

    public int getPointValue() {    return pointValue;    }

    /**
     * This method compares a card with the another card.
     * @param otherCard the other card to compare to the original card
     * @return true if the rank, suit, and point value of this card are equal to those of the other card,
     *          false otherwise.
     */
    public boolean matches(Card otherCard) {
        return otherCard.getSuit().equals(this.getSuit())
                && otherCard.getRank().equals(this.getRank())
                && otherCard.getPointValue() == this.getPointValue();
    }

    /**
     * This method converts the rank, suit, and point value into a String
     *  - "[Rank] of [Suit] (point value = [PointValue])"
     * @return a String that has the rank, suit, and point value of the card.
     */
    @Override
    public String toString() {
        return rank + " of " + suit + " (point value = " + pointValue + ")";
    }
}
