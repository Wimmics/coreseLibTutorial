/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.wimmics.corese.tutorial.server;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 *
 * @author edemairy
 */
public class Request {
	public static void query() throws URISyntaxException, MalformedURLException, IOException {

		String query = "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			+ "SELECT distinct ?x ?p ?y WHERE"
			+ "{"
			+ "     ?x ?p ?y ."
			+ "}"
			+ "     LIMIT 10";

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(new URI("http://localhost:" + RestEndpointTest.port + "/kgram"));

		System.out.println(service.path("sparql").path("reset").post(String.class).toString());

		MultivaluedMap formData = new MultivaluedMapImpl();
		formData.add("remote_path", "http://nyx.unice.fr/~gaignard/data/neurolog.rdf");
//        formData.add("remote_path", "/Users/gaignard/Desktop/bsbmtools-0.2/dataset.ttl");
		service.path("sparql").path("load").post(formData);
		System.out.println(service.path("sparql").queryParam("query", query).accept("application/sparql-results+xml").get(String.class));
//        System.out.println(service.path("sparql").queryParam("query", query).accept("application/json").get(String.class));
	}	
}
