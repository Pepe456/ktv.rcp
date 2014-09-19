package de.andrena.ktv.rcp.dao;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.andrena.ktv.rcp.domain.Team;

public class TeamDao {
	private static final String REST_URL = "http://localhost:8080/ktv/aggregators/teams/";

	public static Team getTeam(String key) {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		WebResource resource = client.resource(REST_URL + key);
		Team team = null;
		try {
			team = resource.get(Team.class);
		} catch (UniformInterfaceException e) {
			return null;
		}
		return team;
	}

	public static Team[] getCurrentTeams() throws UniformInterfaceException, ClientHandlerException {

		Team[] result = null;

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		result = client.resource(REST_URL).get(new GenericType<Team[]>() {
		});

		return result;
	}

	public static boolean addNewTeam(Team newTeam) {
		WebResource webResource = Client.create().resource(REST_URL);
		String input = getJSONFString(newTeam);
		ClientResponse response = webResource.type(APPLICATION_JSON).post(ClientResponse.class, input);

		return response.getStatus() == 201 ? true : false;
	}

	public static boolean updateTeam(Team team) {
		WebResource webResource = Client.create().resource(REST_URL + team.getKey());
		String input = getJSONFStringWithKey(team);
		ClientResponse response = webResource.type(APPLICATION_JSON).put(ClientResponse.class, input);
		return response.getStatus() == 201 ? true : false;
	}

	public static boolean deleteTeam(String key) {
		WebResource webResource = Client.create().resource(REST_URL + key);
		ClientResponse response = webResource.type(APPLICATION_JSON).delete(ClientResponse.class);
		return response.getStatus() == 200 ? true : false;
	}

	private static String getJSONFString(Team team) {
		return ""//
				+ "{"//
				+ "\"name\": \"" + team.getName() + "\"," //
				+ "\"player1\": \"" + team.getPlayer1() + "\","//
				+ "\"player2\": \"" + team.getPlayer2() + "\""//
				+ "}";
	}

	private static String getJSONFStringWithKey(Team team) {
		return ""//
				+ "{"//
				+ "\"key\": \"" + team.getKey() + "\"," //
				+ "\"name\": \"" + team.getName() + "\"," //
				+ "\"player1\": \"" + team.getPlayer1() + "\","//
				+ "\"player2\": \"" + team.getPlayer2() + "\""//
				+ "}";
	}
}
