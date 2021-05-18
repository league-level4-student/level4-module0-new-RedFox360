package _06_Card_Game;

public enum Rank {
	ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(0), QUEEN(0), KING(0);
	private int value;
	
	private Rank(int value) {
		this.value = value;
	}
	int getValue() {
		return value;
	}
	
}
