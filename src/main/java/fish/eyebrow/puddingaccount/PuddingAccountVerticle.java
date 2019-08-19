package fish.eyebrow.puddingaccount;

import com.google.inject.Inject;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class PuddingAccountVerticle extends AbstractVerticle {

    @Inject
    private AccountFetchHandler accountFetchHandler;

    private HttpServer httpServer;


    @Override
    public void start() {
        httpServer = vertx.createHttpServer();

        final Router router = Router.router(vertx);
        router.get("/account").handler(accountFetchHandler);

        httpServer.requestHandler(router);
        httpServer.listen(config().getInteger("port"));
    }


    @Override
    public void stop() {
        httpServer.close();
    }
}
