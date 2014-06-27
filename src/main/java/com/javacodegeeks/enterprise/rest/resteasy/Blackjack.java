package com.javacodegeeks.enterprise.rest.resteasy;

import java.util.LinkedList;

public class Blackjack {
	/* stores card */
	
	public static class Card {
		private int suit;
		private int number;
		private int value;
		
		public int getNumber() {
			return this.number;
		}
		
		public int getSuit() {
			return this.suit;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public Card(int suit, int number) {
			this.suit = suit;
			if(number > 10){
				this.value = 10;
			}
			else if (number == 1){
				this.value = 11;
			}
			else {
				this.value = number;
			}
			this.number = number;
		}
	}
	 
	/* stores deck and provides a method to return a card */
	
	public static class Deck {
		int[][] deck = {{1,1},{1,2},{1,3},{1,4},{1,5},{1,6},{1,7},{1,8},{1,9},{1,10},{1,11},{1,12},{1,13},{2,1},{2,2},{2,3},{2,4},{2,5},{2,6},{2,7},{2,8},{2,9},{2,10},{2,11},{2,12},{2,13},{3,1},{3,2},{3,3},{3,4},{3,5},{3,6},{3,7},{3,8},{3,9},{3,10},{3,11},{3,12},{3,13},{4,1},{4,2},{4,3},{4,4},{4,5},{4,6},{4,7},{4,8},{4,9},{4,10},{4,11},{4,12},{4,13}};
		int deckLength = 52;
		
		public Card takeCard() {
			int cardPos = (int)Math.floor(Math.random()*(this.deckLength));
			int offset = 0;
			int ranSuit = deck[cardPos][0];
			int ranNumber = deck[cardPos][1];
			int[][] temp = new int[this.deckLength-1][2];
			for(int i=0;i<this.deckLength;i++){
				if(i == cardPos){
					offset = 1;
				}
				else {
					temp[i-offset]=deck[i];
				}
			}
			this.deck = temp;
			this.deckLength--;
			return new Card(ranSuit,ranNumber);
		}
	}
	
	//sets up a hand of cards
	public static class Hand {
		LinkedList<Card> hand = new LinkedList<Card>();
		
		public Hand(Deck deck) {
			hand.add(deck.takeCard());
			hand.add(deck.takeCard());
		}
		
		public LinkedList<Card> getHand(){
			return hand;
		}
		
		public int getScore() {
			int score =0;
			int aces = 0;
			for(int i = 0;i < hand.size();i++){
				Card card = hand.get(i);
				score += card.getValue();
				if (card.getNumber() == 1){
					aces++;
				}
			}
			while (aces > 0 && score > 21){
				score -= 10;
				aces--;
			}
			return score;
		}
		
		//add printHand in the post method in the API
		
		public void hitMe(Deck deck) {
			Card newcard = deck.takeCard();
			hand.add(newcard);
		}
	}
	
	//initialise dealer
	public static Hand dealerPlay(Deck deck) {
		Hand dHand = new Hand(deck);
		while (dHand.getScore() < 17){
			dHand.hitMe(deck);
		}
		return dHand;
	}

	//initialise user
	public static Hand userPlay(Deck deck) {
		Hand pHand = new Hand(deck);
		return pHand;
	}
	
	public static GameSpace init(String name){
		Deck deck = new Deck();
		Hand user = userPlay(deck);
		Hand dealer = dealerPlay(deck);
		GameSpace gameSpace = new GameSpace(user,dealer,name,deck);
		return gameSpace;
	}
}
