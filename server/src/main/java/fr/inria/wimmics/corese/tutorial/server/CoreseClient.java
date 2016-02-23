/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.wimmics.corese.tutorial.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.ws.rs.core.MultivaluedMap;

/**
 *
 * @author edemairy
 */
public class CoreseClient {

	static public int PORT = 8080;

	public static void reset() throws URISyntaxException {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(new URI("http://localhost:" + PORT + "/"));
		System.out.println(service.path("sparql").path("reset").post(String.class).toString());
	}

	public static void query() throws URISyntaxException, MalformedURLException, IOException {

		String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "SELECT distinct ?x ?p ?y WHERE"
			+ "{"
			+ "     ?x ?p ?y ."
			+ "}"
			+ "     LIMIT 10";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(new URI("http://localhost:" + PORT + "/kgram"));

		System.out.println(service.path("sparql").path("reset").post(String.class).toString());

		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("remote_path", "http://nyx.unice.fr/~gaignard/data/neurolog.rdf");
//        formData.add("remote_path", "/Users/gaignard/Desktop/bsbmtools-0.2/dataset.ttl");
		service.path("sparql").path("load").post(formData);
		System.out.println(service.path("sparql").queryParam("query", query).accept("application/sparql-results+xml").get(String.class));
//        System.out.println(service.path("sparql").queryParam("query", query).accept("application/json").get(String.class));
	}

	public static void main(String[] args) throws IOException, URISyntaxException {
		String menu = "1. reset\n"
			+ "2. load\n"
			+ "3. query\n"
			+ "4. exit\n";
		int value = -1;
		do {
			do {
				System.out.println(menu);
				System.out.print("Your choice: ");
				value = System.in.read();
			} while (value < Character.valueOf('1') && value > Character.valueOf('4'));
			value = value - Character.valueOf('1') + 1;
				System.out.println("value read = " + value);
			switch (value) {
				case 1:
					reset();
					break;
//				case 2: load(); break;
				case 3:
					query();
					break;
			}
		} while (value != 4);
	}
}
