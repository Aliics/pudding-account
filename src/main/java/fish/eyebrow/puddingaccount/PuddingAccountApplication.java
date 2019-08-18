package fish.eyebrow.puddingaccount;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class PuddingAccountApplication {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new PuddingAccountModule());
        final DeploymentOptions verticleOptions = injector.getInstance(DeploymentOptions.class);

        Vertx.vertx().deployVerticle(PuddingAccountVerticle.class, verticleOptions);
    }
}
