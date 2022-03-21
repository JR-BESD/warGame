package warGame;

public class App {

    public static void main(String[] args) {

	Deck deck = new Deck();
	Player player1 = new Player("Tom");
	Player player2 = new Player("Susan");

	deck.shuffle(deck.cards);

	/*
	 * DEAL: The deck is divided evenly, with each player receiving 26 cards, dealt
	 * one at a time, face down. Anyone may deal first. Each player places their
	 * stack of cards face down, in front of them.
	 */
	for (int i = 0; i < 52; i++) {
	    if (i % 2 == 0) {
		player1.draw(deck);
	    } else {
		player2.draw(deck);
	    }
	}

	/*
	 * PLAY: Each player turns up a card at the same time and the player with the
	 * higher card takes both cards and puts them, face down, on the bottom of his
	 * stack.
	 * 
	 * If the cards are the same rank, it is War.
	 */
	while (player1.hand.size() != 0 && player2.hand.size() != 0) {
	    Card playCard1 = player1.flip();
	    Card playCard2 = player2.flip();

	    if (playCard1.getValue() > playCard2.getValue()) {
		player1.incrementScore();
		player1.hand.add(playCard1);
		player1.hand.add(playCard2);
	    } else if (playCard2.getValue() > playCard1.getValue()) {
		player2.incrementScore();
		player2.hand.add(playCard1);
		player2.hand.add(playCard2);
	    } else {
		war(player1, player2, playCard1, playCard2);
	    }
	}

	declareWinner(player1, player2);

    }

    /*
     * WAR: Each player turns up one card face down and one card face up. The player
     * with the higher cards takes both piles (six cards). If the turned-up cards
     * are again the same rank, each player places another card face down and turns
     * another card face up. The player with the higher card takes all 10 cards, and
     * so on
     * 
     * If a player runs out of cards during a war, their last card is used for all
     * battles in that war. If this happens to both players in a war and their last
     * cards are equal, the game is a draw.
     */
    private static void war(Player player1, Player player2, Card playCard1, Card playCard2) {
	while (player1.hand.size() != 0 && player2.hand.size() != 0) {
	    for (int i = 0; i < 2; i++) {
		if (player1.hand.size() != 0) {
		    playCard1 = player1.flip();
		}

		if (player2.hand.size() != 0) {
		    playCard2 = player2.flip();
		}
	    }

	    if (playCard1.getValue() > playCard2.getValue()) {
		player1.incrementScore();
		player1.hand.add(playCard1);
		player1.hand.add(playCard2);
	    } else if (playCard2.getValue() > playCard1.getValue()) {
		player2.incrementScore();
		player2.hand.add(playCard1);
		player2.hand.add(playCard2);
	    } else {
		if (player1.hand.size() == 0 && player2.hand.size() == 0) {
		    declareWinner(player1, player2);
		}
		war(player1, player2, playCard1, playCard2);
	    }
	}

    }
    
    /*
     * The game ends when one player has won all the cards or both players have
     * played all cards to a draw.
     */

    private static void declareWinner(Player player1, Player player2) {
	if (player1.hand.size() == 0 && player2.hand.size() != 0) {
	    System.out.println(player1.getName() + " won " + player1.getScore() + " of "
		    + (player1.getScore() + player2.getScore()) + " battles");
	    System.out.println(player2.getName() + " won " + player2.getScore() + " of "
		    + (player1.getScore() + player2.getScore()) + " battles\n");
	    System.out.println(player1.getName() + " is out of cards.");
	    System.out.println(player2.getName() + " WINS!!!");
	    System.exit(0);
	} else if (player2.hand.size() == 0 && player1.hand.size() != 0) {
	    System.out.println(player1.getName() + " won " + player1.getScore() + " of "
		    + (player1.getScore() + player2.getScore()) + " battles");
	    System.out.println(player2.getName() + " won " + player2.getScore() + " of "
		    + (player1.getScore() + player2.getScore()) + " battles\n");
	    System.out.println(player2.getName() + " is out of cards.");
	    System.out.println(player1.getName() + " WINS!!!");
	    System.exit(0);
	} else {
	    System.out.println("Both " + player1.getName() + " AND " + player2.getName() + " have run out of cards!\n");
	    System.out.println("The game ends in a DRAW!!!");
	}
    }
}
