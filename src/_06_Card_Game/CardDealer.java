package _06_Card_Game;

import java.util.ArrayList;
import java.util.Random;

public class CardDealer {

	ArrayList<Card> deck;
	Random rand;

	CardDealer() {
		rand = new Random();
		deck = new ArrayList<>();

		for (Rank r : Rank.values()) {
			for (Suit s : Suit.values()) {
				deck.add(new Card(r, s));
			}
		}
	}

	public void shuffle() {
		for (int i = 0; i < deck.size(); i++) {
			int random = rand.nextInt(2);
			if (random == 0) {
				int deckRandom = rand.nextInt(deck.size());
				Card temp = deck.get(deckRandom);
				deck.set(deckRandom, deck.get(i));
				deck.set(i, temp);
			}
		}
	}

	public ArrayList<Card> deal(int amount) {
		ArrayList<Card> cards = new ArrayList<>();
    	for (int i = 0; i < amount; i++) {
    		int random = rand.nextInt(deck.size());
    		cards.add(deck.remove(random));
    	}
    	return cards;
    }
	
	public Card pop() {
		return deck.remove(deck.size() - 1);
	}
	
	

}
