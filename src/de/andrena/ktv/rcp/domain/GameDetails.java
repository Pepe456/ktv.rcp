package de.andrena.ktv.rcp.domain;

public class GameDetails {
	String tableId;
	String gameId;
	String teamName1;
	String teamName2;

	public String getTableId() {
		return this.tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getGameId() {
		return this.gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getTeamName1() {
		return this.teamName1;
	}

	public void setTeamName1(String teamName1) {
		this.teamName1 = teamName1;
	}

	public String getTeamName2() {
		return this.teamName2;
	}

	public void setTeamName2(String teamName2) {
		this.teamName2 = teamName2;
	}
}
