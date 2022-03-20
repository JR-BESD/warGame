package warGame;

public class App {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		Player player1 = new Player("Tom");
		Player player2 = new Player("Susan");
		
		String winner;
		
		deck.shuffle(deck.cards);		
		
		for (int i = 0; i < 52; i++) {
			if (i % 2 == 0) {
				player1.draw(deck);
			} else {
				player2.draw(deck);
			}			
		}
		
		for (int i = 0; i < 26; i++) {
			Card playCard1 = player1.flip();
			Card playCard2 = player2.flip();
			
			if (playCard1.getValue() > playCard2.getValue()) {
				player1.incrementScore();
			} else if (playCard2.getValue() > playCard1.getValue()) {
				player2.incrementScore();
			}
		}
		
		if (player1.getScore() > player2.getScore()) {
			winner = player1.getName();
		} else if (player2.getScore() > player1.getScore()) {
			winner = player2.getName();
		} else {
			winner = "Draw";
		}
		
		System.out.println("Final Score: \n"
				+ player1.getName() + " - " + player1.getScore() + "\n"
				+ player2.getName() + " - " + player2.getScore() + "\n");
		if (player1.getScore() == player2.getScore()) {
			System.out.println("It's a Draw!");
		} else {
			System.out.println(winner + " WINS!!!");
		}
		
	}
}
