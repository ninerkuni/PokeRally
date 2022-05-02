package Player;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Elements.Robot;

public class Card {

    //A list of all cards available
	static public enum action {
		move,turnL,turnR;
		
		@Override
		public String toString() {
			switch(this) {
			case move: return "move";
			case turnL: return "turnL";
			default: return "turnR";
			}
		}
	}
	
	private List<action> actions;
	
    private String title;
    
    
    public Card(String title) {
    	this.title =  title;
    }
    
    public String getTitle() {
    	return title;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Card card = (Card) obj;
        if(this.title == null || card.getTitle() == null) {
        	return false;
        }
        
        return (this.title == card.getTitle());
    }
    
//    public void setTitle(String s) {
//    	title = s;
//    }
//    
    public void setAction(List<action> actions) {
    	this.actions = actions;
    }
    
    public boolean play(Robot robot) {
    	boolean works = false;
    	for (action a : actions) {
    		if( a == action.move) {
//    			System.out.println("Card 'move'");
    			robot.moved();
    			works = true;
    		}
    		else if (a == action.turnL){
//    			System.out.println("Card 'turn left'");
    			robot.turnL();
    			works = true;
    		}
    		else if( a == action.turnR){
//    			System.out.println("Card 'turn right'");
    			robot.turnR();
    			works = true;
    		}
    		}
    	return works;
    }
    
    public String printActions() {
    	String str = Arrays.toString(actions.toArray());
//    	System.out.println(Arrays.toString(actions.toArray()));
    	return str;
    }


    
    
    
//    protected String[] CardsOnHand = new String[5];
//    // Should there be a "MoveBackwards"???
//
//    //A method that shuffles the cards and prints them out.
//    public String showRandomCards() {
//        Random rand = new Random();
//        int numberOfCards = 5;
//        for (int i = 0; i < numberOfCards; i++) {
//            int randomIndex = rand.nextInt(AllCards.size());
//            String randomCards = AllCards.get(randomIndex);
//            CardsOnHand[i] = randomCards;
//        }
//        System.out.println(Arrays.toString(CardsOnHand));
//        return null;
//    }
}


