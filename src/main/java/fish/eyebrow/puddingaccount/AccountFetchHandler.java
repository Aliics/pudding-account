package fish.eyebrow.puddingaccount;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class AccountFetchHandler implements Handler<RoutingContext> {

    @Override
    public void handle(final RoutingContext context) {
        context.response().end("Hello, World!");
    }
}
