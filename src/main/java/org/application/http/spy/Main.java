package org.application.http.spy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.Expectation;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpResponse;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.slf4j.LoggerFactory.getLogger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger log = getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        clientAndServerLoad();

    }
    private static void clientAndServerLoad() throws IOException {
        log.info("start clientAndServer");
        ClientAndServer clientAndServer = new ClientAndServer(8081);

        // Charger le fichier init.json
        ObjectMapper objectMapper = new ObjectMapper();
        URL resource = Main.class.getClassLoader().getResource("init.json");
        assert resource != null;
        Expectation[] expectations = objectMapper.readValue(new File(resource.getFile()), Expectation[].class);

        // Envoyer les attentes à MockServer
        clientAndServer.upsert(expectations);

    }

    private static void clientAndServer() {
        log.info("start clientAndServer");
        HttpForward httpForward = HttpForward.forward().withScheme(HttpForward.Scheme.HTTPS).withHost("dog.ceo").withPort(443);
        ClientAndServer clientAndServer = new ClientAndServer(8081);

        log.info("when");
        clientAndServer
                .when(
                        request().withPath("/api/breeds/image/.*")
                                .withMethod("GET")
                )
                .forward(
                        httpForward
                );

    }

}
