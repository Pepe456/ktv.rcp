package de.andrena.ktv.model;

import java.util.ArrayList;
import java.util.List;

public class Teams {
	List<Team> teams = new ArrayList<>();

	public void addTeam(Team... teams) {
		for (Team team : teams) {
			this.teams.add(team);
		}
	}

	public Team[] getTeamsAsArray() {
		return this.teams.toArray(new Team[this.teams.size()]);
	}

	public Team getTeam(String name) {
		for (Team team : this.teams) {
			if (team.getName().equals(name)) {
				return team;
			}
		}
		return null;
	}
}
