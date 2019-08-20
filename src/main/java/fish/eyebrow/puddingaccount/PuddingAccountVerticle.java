package fish.eyebrow.puddingaccount;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class PuddingAccountVerticle extends AbstractVerticle {

    private AccountFetchHandler accountFetchHandler;

    private HttpServer httpServer;

    private AccountLoginHandler accountLoginHandler;


    PuddingAccountVerticle(final AccountFetchHandler accountFetchHandler) {
        this.accountFetchHandler = accountFetchHandler;
    }


    @Override
    public void start() {
        final Integer port = config().getInteger("port");
        final String accountURI = config().getString("accountURI");

        final Router router = Router.router(vertx);
        router.get(accountURI).handler(accountFetchHandler);
        router.post(accountURI).handler(accountLoginHandler);

        httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router);
        httpServer.listen(port);
    }


    @Override
    public void stop() {
        httpServer.close();
    }
}
