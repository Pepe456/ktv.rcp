package de.andrena.ktv.rcp.domain;

public class Team {
	String key;
	String name;
	String player1;
	String player2;

	public Team() {

	}

	public Team(String teamName, String player1Name, String player2Name) {
		this.name = teamName;
		this.player1 = player1Name;
		this.player2 = player2Name;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

}
