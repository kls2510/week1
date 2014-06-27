package com.javacodegeeks.enterprise.rest.resteasy;

import com.javacodegeeks.enterprise.rest.resteasy.Blackjack.Deck;
import com.javacodegeeks.enterprise.rest.resteasy.Blackjack.Hand;

public class GameSpace {
	Hand userHand;
	String name;
	Deck deck;
	int handSize;
	String dealerHand;
	String returnHand;
	int userScore;
	int dealerScore;
	String outcomeMessage;
	String outcomeSingle;
	
	public GameSpace(Hand user, Hand dealer, String name, Deck deck){
		this.userHand = user;
		this.dealerHand = makeString(dealer);
		this.dealerScore = dealer.getScore();
		this.name = name;
		this.deck = deck;
		this.userScore = user.getScore();
		this.returnHand = makeString(user);
		this.handSize = 2;
	}
	
	public String getOutcomeSingle() {
		return this.outcomeSingle;
	}
	
	public void addCard() {
		this.handSize++;
		userHand.hitMe(deck);
		this.returnHand = makeString(userHand);
		this.userScore = userHand.getScore();
	}
	
	public void makeOutcome() {
		String outcome = "";
		if (this.userScore > 21 && this.dealerScore > 21){
			outcome = "You tied!";
			this.outcomeSingle = "tie";
		}
		else if (this.userScore <= 21 && this.dealerScore <= 21){
			if (21 - this.userScore < 21 - this.dealerScore){
				outcome = "You win!";
				this.outcomeSingle = "win";
			}
			else {
				outcome = "You lose!";
				this.outcomeSingle = "loss";
			}
		}
		else if (this.userScore > 21 && this.dealerScore <= 21){
			outcome = "You lose!";
			this.outcomeSingle = "loss";
		}
		else if (this.userScore <= 21 && this.dealerScore > 21){
			outcome = "You win!";
			this.outcomeSingle = "win";
		}
		else if (this.userHand.getHand().size() == 5){
			outcome = "You win!";
			this.outcomeSingle = "win";
		}
		else if (this.userScore == this.dealerScore){
			outcome = "You draw!";
			this.outcomeSingle = "tie";
		}
		
		this.outcomeMessage = "The dealer got " + this.dealerHand + " for a score of " + this.dealerScore + ". So " + outcome;
 	}
	
	private String makeString(Hand hand) {
		String ID = "";
		String suit = "";
		String returnString = "";
		for (int i = 0; i<hand.getHand().size(); i++){
			if (hand.getHand().get(i).getNumber() == 11){
				ID = "Jack";
			}
			else if (hand.getHand().get(i).getNumber() == 12){
				ID = "Queen";
			}
			else if (hand.getHand().get(i).getNumber() == 13){
				ID = "King";
			}
			else if (hand.getHand().get(i).getNumber() == 1){
				ID = "Ace";
			}
			else {
				ID = Integer.toString(hand.getHand().get(i).getNumber());
			}
			if (hand.getHand().get(i).getSuit() == 1){ 
				suit = "Clubs";
			}
			else if (hand.getHand().get(i).getSuit() == 2){
				suit = "Diamonds";
			}
			else if (hand.getHand().get(i).getSuit() == 3){
				suit = "Hearts";
			}
			else if (hand.getHand().get(i).getSuit() == 4){
				suit = "Spades";
			}
			
			returnString += ID + " of " + suit + ". ";
		}
		return returnString;
	}
	
	public String getDHand() {
		return this.dealerHand;
	}
	
	public int returnHandSize() {
		return this.handSize;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getReturnHand() {
		return this.returnHand;
	}
	
	public int getUserScore() {
		return this.userScore;
	}
	public int getDealerScore() {
		return this.dealerScore;
	}
	public String getOutcome() {
		return this.outcomeMessage;
	}
}
