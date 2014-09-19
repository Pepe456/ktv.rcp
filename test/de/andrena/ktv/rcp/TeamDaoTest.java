package de.andrena.ktv.rcp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.andrena.ktv.rcp.dao.TeamDao;
import de.andrena.ktv.rcp.domain.Team;

public class TeamDaoTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void addNewTeam() throws Exception {
		Team team = new Team("Gallien", "Asterix", "Obelix");
		assertThat(TeamDao.addNewTeam(team), is(true));
	}

	@Test
	public void getTeam() throws Exception {
		String key = "97accd2c-1304-4403-9cc8-20fdbc810acd";
		Team team = TeamDao.getTeam(key);
		assertThat(team.getKey(), is(equalTo(key)));
	}

	@Test
	public void getAllTeams() throws Exception {
		assertThat(TeamDao.getCurrentTeams().length, is(notNullValue()));
	}

	@Test
	public void deleteTeams() throws Exception {
		String key = this.getValidKey();
		assertThat(TeamDao.deleteTeam(key), is(true));
		assertThat(TeamDao.getTeam(key), is(nullValue()));
	}

	private String getValidKey() {
		Team[] teams = TeamDao.getCurrentTeams();
		Team team = teams[teams.length - 1];
		return team.getKey();
	}
}
