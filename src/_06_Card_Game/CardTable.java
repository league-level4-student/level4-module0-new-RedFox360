package _06_Card_Game;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CardTable {
	CardDealer cardDealer;
	ArrayList<Card> player1;
	ArrayList<Card> player2;
	ArrayList<Card> currentPlayer;
	int cp;

	public void run() {
		cardDealer = new CardDealer();
		cardDealer.shuffle();
		player1 = cardDealer.deal(5);
		player2 = cardDealer.deal(5);
		currentPlayer = player1;
		cp = 1;
		ArrayList<Card[]> sets = new ArrayList<>();

		while (currentPlayer.size() != 0) {
			if (cardDealer.deck.size() == 0) {
				JOptionPane.showMessageDialog(null, "The Deck is empty!", "Go Fish: Player " + cp,
						JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			boolean has = pairs();
			if (!has) {
				int o = JOptionPane.showOptionDialog(null, "You do not have any pairs that add up to 10",
						"Go Fish: Player " + cp, 1, 1, null, new String[] { "Go Fish", "Take From Player " + (cp == 1 ? 2 : 1) },
						JOptionPane.YES_OPTION);
				if (o == 0) {
					goFish();
				} else {
					take();
				}
			}
		}
	}

	public boolean pairs() {
		for (int i = currentPlayer.size() - 1; i >= 0; i--) {
			for (int j = currentPlayer.size() - 1; j >= 0; j--) {
				if (j != i) {
					Card c = currentPlayer.get(i);
					Card k = currentPlayer.get(j);
					int cValue = c.getRank().getValue();
					int kValue = k.getRank().getValue();
					if (cValue + kValue == 10) {
						JOptionPane.showMessageDialog(null,
								String.format("You have set down the cards %d and %d (+=10)", cValue, kValue),
								"Go Fish: Player " + cp, 1);
						currentPlayer.remove(i);
						currentPlayer.remove(j > i ? j - 1 : j);
						currentPlayer = player2;
						return true;
					}
					if (currentPlayer.size() == 0) {
						JOptionPane.showMessageDialog(null, "Player " + cp + " won!");
						System.exit(0);
					}
				}
			}
		}
		return false;
	}

	public void goFish() {
		if (cp == 1) {
			player1.add(cardDealer.pop());
			currentPlayer = player1;
		} else {
			player2.add(cardDealer.pop());
			currentPlayer = player2;
		}
		System.out.println(currentPlayer.toString());
		cp = cp == 1 ? 2 : 1;
		currentPlayer = cp == 1 ? player1 : player2;
	}

	public void take() {
		for (Card c : currentPlayer) {
			int neededValue = 10 - c.getRank().getValue();
			System.out.println("Checking if other player has value " + neededValue);
			int value = findValue((cp == 1 ? player2 : player1), neededValue);
			if (value != -1) {
				Card card = player2.remove(value);
				player1.add(card);
				System.out.println("Got value!");
				return;
			}
		}
		System.out.println("Player does not have that value");
		JOptionPane.showOptionDialog(null, "...", "Go Fish: Player " + cp, 1, 1, null, new String[] { "Go Fish" },
				JOptionPane.YES_OPTION);
		goFish();
	}

	public int findValue(ArrayList<Card> a, int value) {
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).getRank().getValue() == value) {
				return i;
			}
		}
		return -1;
	}
}