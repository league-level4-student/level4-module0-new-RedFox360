package _06_Card_Game;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class CardTable {
	CardDealer cardDealer;
	ArrayList<Card> player1;
	ArrayList<Card> player2;
	int cp;

	public void run() {
		cardDealer = new CardDealer();
		cardDealer.shuffle();
		player1 = cardDealer.deal(4);
		player2 = cardDealer.deal(4);
		cp = 1;
		while (true) {
			System.out.println("1: "+player1.toString());
			System.out.println("2: "+player2.toString());
			if (cardDealer.deck.size() == 0) {

				System.out.println("The Deck is empty!"
						);
				if (player1.size() < player2.size()) {
					System.out.println("Player 1 won!");
				} else if (player2.size() < player1.size()) {
					System.out.println("Player 2 won!");
				} else {
					System.out.println("It's a draw!");
				}
				break;
			}
			boolean has = pairs(cp == 1 ? player1 : player2);
			if (!has) {
				System.out.println("You do not have any pairs that add up to 10\nGo Fish: Player, taking from other " + cp);

				take(cp == 1 ? player1 : player2, cp == 1 ? player2 : player1);
			}
			cp = cp == 1 ? 2 : 1;
			hasWon(cp == 1 ? player1 : player2, cp == 1 ? player2 : player1);
		}
	}

	public boolean pairs(ArrayList<Card> currentPlayer) {
		for (int i = currentPlayer.size() - 1; i >=0; i--) {
			for (int j = currentPlayer.size() - 1; j >= 0; j--) {
				if (j!=i) {
					Card c = currentPlayer.get(i);
					Card k = currentPlayer.get(j);
					int cValue = c.getRank().getValue();
					int kValue = k.getRank().getValue();
					if (cValue + kValue == 10) {
						System.out.println("You have set down the cards " + cValue + " and " + kValue + " (+=10)\nGo Fish: Player " + cp);
						currentPlayer.remove(i);
						currentPlayer.remove(j > i ? j - 1 : j);
						return true;
					}
				}
			}
		}
		return false;
	}

	public void goFish(ArrayList<Card> currentPlayer) {
		currentPlayer.addAll(cardDealer.deal(1));
		System.out.println(currentPlayer.toString());
		pairs(cp == 1 ? player1 : player2);
	}

	public void take(ArrayList<Card> currentPlayer, ArrayList<Card> otherPlayer) {
		for (int i = currentPlayer.size() - 1; i >= 0; i--) {
			Card c = currentPlayer.get(i);
			int neededValue = 10 - c.getRank().getValue();
			int value = findValue(getValues(otherPlayer), neededValue);
			if (value != -1) {
				Card card = otherPlayer.remove(value);
				currentPlayer.add(card);
				pairs(cp == 1 ? player1 : player2);
				return;
			}
		}
		System.out.println("Go fish\nGo Fish: Player " + cp);
		goFish(cp == 1 ? player1 : player2);
	}

	public int findValue(ArrayList<Integer> a, int value) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == value) {
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Integer> getValues(ArrayList<Card> a) {
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		for ( int i = 0; i < a.size(); i++) {
			toReturn.add(a.get(i).getRank().getValue());
		}
		return toReturn;
	}
	
	public void hasWon(ArrayList<Card> currentPlayer, ArrayList<Card> otherPlayer) {
		if (currentPlayer.isEmpty()) {
			System.out.println("Player "+cp+" won!");
			System.exit(0);
		}
		if (otherPlayer.isEmpty()) {
			System.out.println("Player "+(cp==1 ? 2 : 1)+" won!");
			System.exit(0);
		}
	}
}