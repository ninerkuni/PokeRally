package Player;
import java.util.ArrayList;
//import java.util.stream.Collectors;

import Elements.Robot;



public class Hand {
	private int capacity;
	private ArrayList<Card> cards;
	private int spaces;
	private Card[] order;

	// constructor for the hand, so that it is initialised with the following things
	public Hand(int capacity) {
		this.capacity = capacity;
		cards = new ArrayList<Card>(capacity);
		cards.ensureCapacity(capacity);
		order = new Card[capacity];
		spaces = capacity;
	}

	// empty hand, used for testing scenarios
	public void empty() {
		cards.clear();
	} // used

	// a boolean that checks if there are any cards left on hand
	public boolean isEmpty() {
		return (cards.isEmpty());
	} // used

	// getter for the amount of spaces on hand
	public int getSpaces() {
		return capacity - cards.size();
	} // used

	// drawing cards from the deck
	public void draw(Deck deck) {
		cards.add(deck.draw());
	} // used

	// a boolean that checks if the card is of a certain type
	public boolean contains(Card card) {
		return cards.contains(card);
	}
	// fills the hand with cards from the deck, as long as there are free spots on the hand
	public void fill(Deck deck) { // used
		if (getSpaces() > 0) {
			draw(deck);
			fill(deck);
		}
	}
	// gets the position for a certain type of card from the hand
	public int getPosition(Card card) {
		for(int i = 0; i < capacity; i++) {
			if(order[i] == card) return i;
		}
		return capacity;
	}

	// set po
	public void setPosition(Card card, int i) {
		int old = getPosition(card);
		if(i >= capacity) return;
		if (order[i] == null) {
			order[i] = card;
		} else {
			Card c = order[i];
			order[i] = card;
			if(old < capacity) order[old] = c;
		}
	}

	public String[] getTitles() {
		String[] titles = new String[capacity];
		int i = 0;
		for(Card c : cards) {
			titles[i] = c.getTitle();
			i++;
		}
		return titles;
	}

	public Card findCard(String title){
		return (cards.stream().filter(o -> o.getTitle().equals(title)).findFirst().orElse(null));
	}

	public void ordered(boolean b) {
		if(!b) order = new Card[capacity];
	}

	public boolean isOrdered() {
		for(int i = 0; i < order.length; i++) {
			if(order[i] == null) {
				return false;
			}
		}
		return true;
	}

	public String play(Robot robot) {
		String card;
		if(order.length > 0){
			card = order[0].getTitle();
			order[0].play(robot);
			Card[] temp = new Card[order.length-1];
			if(temp.length>0) {
				for(int i=1;i<order.length;i++) {
					temp[i-1] = order[i];
				}
				order = temp;
			} else {
				empty();
				ordered(false);
			}
			return card;
		}
		return null;
	}

	public Card getCard(int i) {
		if (i >= capacity) return null;
		return cards.get(i);
	}
}
