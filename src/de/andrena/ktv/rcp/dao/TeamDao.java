package de.andrena.ktv.rcp.dao;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import de.andrena.ktv.model.TeamImpl;

public class TeamDao {
	private static final String SERVER_URI = "http://localhost:8080/de.andrena.ktv.server/team/";

	public static TeamImpl getTeam(String name) {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		WebResource resource = client.resource(SERVER_URI + "get/" + name);
		TeamImpl team = null;
		try {
			team = resource.get(TeamImpl.class);
		} catch (UniformInterfaceException e) {
			return null;
		}
		return team;
	}

	public static TeamImpl[] getCurrentTeams() {

		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getClasses().add(JacksonJsonProvider.class);
		Client client = Client.create(clientConfig);
		WebResource resource = client.resource(SERVER_URI + "getall");

		return resource.get(new GenericType<TeamImpl[]>() {
		});
	}

	public static boolean addNewTeam(TeamImpl newTeam) {
		WebResource webResource = Client.create().resource(SERVER_URI + "create");
		String input = getJSONFString(newTeam);
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

		return response.getStatus() == 200 ? true : false;
	}

	public static boolean deleteTeam(String name) {
		WebResource webResource = Client.create().resource(SERVER_URI + "delete" + "/" + name);
		ClientResponse response = webResource.type("application/json").delete(ClientResponse.class);
		return response.getStatus() == 200 ? true : false;
	}

	private static String getJSONFString(TeamImpl team) {
		return ""//
				+ "{"//
				+ "\"name\": \"" + team.getName() + "\"," //
				+ "\"player1\": \"" + team.getPlayer1() + "\","//
				+ "\"player2\": \"" + team.getPlayer2() + "\""//
				+ "}";
	}
}
