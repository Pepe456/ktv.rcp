package de.andrena.ktv.model;

public class Team {

	private String name;
	private String player1;
	private String player2;

	public Team() {
	}

	public Team(String name, String player1, String player2) {
		this.name = name;
		this.player1 = player1;
		this.player2 = player2;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String id) {
		this.name = id;
	}

	public String getPlayer1() {
		return this.player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return this.player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	@Override
	public String toString() {
		return new StringBuffer("")//
				.append(" ID : ").append(this.name)//
				.append(" Player 1 : ").append(this.player1)//
				.append(" Player 2 : ").append(this.player2).toString();
	}

}
