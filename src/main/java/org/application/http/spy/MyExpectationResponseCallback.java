package org.application.http.spy;

import org.mockserver.mock.action.ExpectationResponseCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

public class MyExpectationResponseCallback implements ExpectationResponseCallback {
    @Override
    public HttpResponse handle(HttpRequest httpRequest) throws Exception {
        return null;
    }
}
