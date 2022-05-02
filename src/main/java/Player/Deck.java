package Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.List;


public class Deck {
	private int numCards;
	private List<Card> cards;
	
	public Deck() {
		numCards = 0;
		cards = new ArrayList<Card>();
		
	}
	
	public void add(Card card) {
		if(!this.contains(card)) {
			cards.add(card);
			numCards ++;
		}
	}
	
	public boolean contains(Card c) {
		return cards.contains(c);
	}
	
	public int getNumCards() {
		return numCards;
	}
//	public void setNumCards(int numCards) {
//		this.numCards = numCards;
//	}
	
	public Card draw() {
		int rand = (int) (numCards * Math.random());
		return cards.get(rand);
	}

	public void defaultDeck(){
		ArrayList<Card.action> actions = new ArrayList<Card.action>();
		//move
		Card card = new Card("move1Forward");
		actions.add(Card.action.move);
		card.setAction(actions);
		add(card);
		card = new Card("move1Forward");
		actions.add(Card.action.move);
		card.setAction(actions);
		add(card);
		//move2
		actions = new ArrayList<Card.action>();
		card = new Card("move2Forward");
		actions.add(Card.action.move);
		actions.add(Card.action.move);
		card.setAction(actions);
		add(card);
		//turnLeft
		actions = new ArrayList<Card.action>();
		card = new Card("turnL");
		actions.add(Card.action.turnL);
		card.setAction(actions);
		add(card);
		//turnRight
		actions = new ArrayList<Card.action>();
		card = new Card("turnR");
		actions.add(Card.action.turnR);
		card.setAction(actions);
		add(card);
		//turn180
		actions = new ArrayList<Card.action>();
		card = new Card("turn180");
		actions.add(Card.action.turnR);
		actions.add(Card.action.turnR);
		card.setAction(actions);
		add(card);

	}
}
