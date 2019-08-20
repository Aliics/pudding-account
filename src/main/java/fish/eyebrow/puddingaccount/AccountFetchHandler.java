package fish.eyebrow.puddingaccount;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;

public class AccountFetchHandler implements Handler<RoutingContext> {

    @Override
    public void handle(final RoutingContext context) {
        final HttpServerResponse response = context.response();
        response.setStatusCode(200);
        response.end();
    }
}
