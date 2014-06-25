var NAME;

/*reads user data from server*/

/* $.get("/rest/RESTEasyHelloWorldService",
		function(name){
			NAME = name;
		}, "text"); */

/* stores card */
var Card = function(s,n){
	var suit = s;
	var number = n;
	this.getNumber = function(){
		return number;
	};
	this.getSuit = function(){
		return suit;
	};
	this.getValue = function() {
		if (number > 10){
		return 10;
		}	
		else if (number === 1){
			return 11;
		}
		else {
			return number;
		}
	};
};

/* stores deck and provides a method to return a card */
var Deck = function() {
	var deck = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7],[1,8],[1,9],[1,10],[1,11],[1,12],[1,13],[2,1],[2,2],[2,3],[2,4],[2,5],[2,6],[2,7],[2,8],[2,9],[2,10],[2,11],[2,12],[2,13],[3,1],[3,2],[3,3],[3,4],[3,5],[3,6],[3,7],[3,8],[3,9],[3,10],[3,11],[3,12],[3,13],[4,1],[4,2],[4,3],[4,4],[4,5],[4,6],[4,7],[4,8],[4,9],[4,10],[4,11],[4,12],[4,13]];
	this.takeCard = function() {
		CardSelect = Math.floor(Math.random()*deck.length);
		var ranSuit = deck[CardSelect][0];
		var ranNumber = deck[CardSelect][1];
		deck.splice[CardSelect,1];
		return new Card(ranSuit, ranNumber);
	};
	
};

//sets up a hand of cards
var Hand = function(deck){
	var hand = [];
	hand[0]=deck.takeCard();
	hand[1]=deck.takeCard();
	
	this.getHand = function(){
		return hand;	
	};
	
	this.score = function() {
		var score = 0;
		var aces = 0;
		for (var i=0; i < hand.length; i++){
			score += this.getHand()[i].getValue();
			if (this.getHand()[i].getNumber() === 1){
				aces++;
				}}
		while (aces > 0 && score > 21){
					score -= 10;
					aces--;
					}
		return score;
	};
	
	this.printHand = function(){
		string = "";
		ID = "";
		Suit = "";
	
	for (var y = 0; y<hand.length; y++){
		if (hand[y].getNumber() === 11){
			ID = "Jack";
		}
		else if (hand[y].getNumber() === 12){
			ID = "Queen";
		}
		else if (hand[y].getNumber() === 13){
			ID = "King";
		}
		else if (hand[y].getNumber() === 1){
			ID = "Ace";
		}
		else {
			ID = hand[y].getNumber();
		}
		if (hand[y].getSuit() === 1){ 
			Suit = "Clubs";
		}
		else if (hand[y].getSuit() === 2){
			Suit = "Diamonds";
		}
		else if (hand[y].getSuit() === 3){
			Suit = "Hearts";
		}
		else if (hand[y].getSuit() === 4){
			Suit = "Spades";
		}
		
		string = string + ID + " of " + Suit + " . ";
	}
		return string;
	};
	
		this.hitMe = function() {
			var newCard = deck.takeCard();
			hand.push(newCard);
		};
};

//initialise dealer
var playAsDealer = function(deck) {
	var dhand = new Hand(deck);
	while (dhand.score() < 17){
		dhand.hitMe();
	}
	return dhand;
};

//initialise user
var playAsUser = function(deck) {
	var player = new Hand(deck);
	var response = confirm(player.printHand() + " Your total score is " + player.score() + ". Hit Me?");
	
	while (response === true && player.score() < 21) {
		player.hitMe();
		response = confirm(player.printHand() + " Your score is " + player.score() + ". Hit Me?");
	}
	return player;
};

var declareWinner = function(uHand,dHand){
	var userHand = uHand.score();
	var dealerHand = dHand.score();
		if (userHand > 21 && dealerHand > 21){
			return "You tied!";
		}
		else if (userHand <= 21 && dealerHand <= 21){
			if (21 - userHand < 21 - dealerHand){
				return "You win!";
			}
			else {
				return "You lose!";
			}
		}
		else if (userHand > 21 && dealerHand <= 21){
			return "You lose!";
		}
		else if (userHand <= 21 && dealerHand > 21){
			return "You win!";
		}
		else if (userHand.length === 5){
			return "You win!";
		}
		else if (userHand === dealerHand){
			return "You draw!";
		}
};

var init = function(name) {
	var deck = new Deck();
	var User = playAsUser(deck);
	var Dealer = playAsDealer(deck);
	var winMessage = declareWinner(User, Dealer);
	document.write("Dealer got " + Dealer.printHand() + " for a score of " + Dealer.score() + "<br>");
	document.write(name + " got " + User.printHand() + " for a score of " + User.score() + "<br>");
	document.write(winMessage + "<br>");
};

init();