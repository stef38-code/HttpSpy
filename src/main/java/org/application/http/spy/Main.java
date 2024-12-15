package org.application.http.spy;

import org.mockserver.configuration.Configuration;
import org.mockserver.configuration.ConfigurationProperties;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.action.ExpectationResponseCallback;
import org.mockserver.model.Delay;
import org.mockserver.model.Format;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.mockserver.model.HttpRequest.request;
import static org.slf4j.LoggerFactory.getLogger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger log = getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        clientAndServerLoad();

    }

    private static void clientAndServerLoad() {
        log.info("start clientAndServer");
        ConfigurationProperties.watchInitializationJson(true);
        ConfigurationProperties.initializationJsonPath("init.json");
        ExpectationResponseCallback sample = new MyExpectationResponseCallback();
        ClientAndServer.startClientAndServer(Configuration.configuration(), 8081);
//                .when(request().withPath(".*"))
//                .respond(sample, Delay.delay(TimeUnit.MILLISECONDS,0L));
//                .retrieveRecordedRequestsAndResponses(
//                request().withPath(".*"),
//                Format.JAVA
//        );
        log.info("=================================");

    }
}
