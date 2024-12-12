package org.application.http.spy;

import org.mockserver.client.MockServerClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpResponse;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try (MockServerClient mockServerClient = new MockServerClient("127.0.0.1", 8081)) {

            // Configuration pour écouter sur le port 8080
            HttpForward httpForward = HttpForward.forward().withScheme(HttpForward.Scheme.HTTPS).withHost("swapi.dev").withPort(443);
            HttpResponse reponse = HttpResponse.response();
            mockServerClient.when(
                            HttpRequest.request()
                                    .withPath("/api/people/.*")
                                    .withMethod("GET")
                    ).respond(reponse);//.forward(httpForward);
            mockServerClient.openUI();
//                    .respond(
//                            (ExpectationResponseCallback) httpForward
//                    )
//                    .withCallback(
//                            (HttpRequest request) -> {
//                                ObjectMapper mapper = new ObjectMapper();
//                                try {
//                                    // Enregistrer la requête
//                                    mapper.writeValue(new File("request.json"), request);
//
//                                    // Enregistrer la réponse (sera complétée après l'appel à swapi.dev)
//                                    // ...
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                                return HttpResponse.response();
//                            }
//                    );
        }
    }
}
//public static class TestExpectationResponseCallback implements ExpectationResponseCallback {
//
//    @Override
//    public HttpResponse handle(HttpRequest httpRequest) {
//        if (httpRequest.getPath().getValue().endsWith("/path")) {
//            return response()
//                    .withStatusCode(HttpStatusCode.ACCEPTED_202.code())
//                    .withHeaders(
//                            header("x-callback", "test_callback_header"),
//                            header("Content-Length", "a_callback_response".getBytes(UTF_8).length),
//                            header("Connection", "keep-alive")
//                    )
//                    .withBody("a_callback_response");
//        } else {
//            return notFoundResponse();
//        }
//    }
//}
//
//
