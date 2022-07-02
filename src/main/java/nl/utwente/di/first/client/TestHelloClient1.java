package nl.utwente.di.first.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.WebTarget;


public class TestHelloClient1 {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		String url_pattern = "rest";
		String path = "hello";

		String response = target.path(url_pattern).path(path).request().accept(MediaType.TEXT_PLAIN).get(Response.class)
				.toString();

		String plainAnswer = target.path(url_pattern).path(path).request().accept(MediaType.TEXT_PLAIN).get(String.class);
		String xmlAnswer = target.path(url_pattern).path(path).request().accept(MediaType.TEXT_XML).get(String.class);
		String htmlAnswer = target.path(url_pattern).path(path).request().accept(MediaType.TEXT_HTML).get(String.class);

		System.out.println(response);
		System.out.println(plainAnswer);
		System.out.println(xmlAnswer);
		System.out.println(htmlAnswer);
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/restFirst").build();
	}

}
