package nl.utwente.di.first.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.client.WebTarget;


public class TestHelloClient {
	
	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(getBaseURI());
		String url_pattern = "rest";
		String path = "hello";
		
		// Get plain text
		System.out.println("--> GET text/plain message from rest/hello:");
		System.out.println(target.path(url_pattern).path(path).request(
						"text/plain").get(String.class));
		// Get XML
		System.out.println("\n--> GET text/xml message from rest/hello:");
		System.out.println(target.path(url_pattern).path(path).request(
				"text/xml").get(String.class));
		// Get HTML
		System.out.println("\n--> GET text/hmtl message from rest/hello:");
		System.out.println(target.path(url_pattern).path(path).request(
						"text/html").get(String.class));
				
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/restFirst").build();
	}

}
