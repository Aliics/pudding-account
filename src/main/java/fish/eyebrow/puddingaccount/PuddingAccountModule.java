package fish.eyebrow.puddingaccount;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import fish.eyebrow.puddingaccount.handler.AccountFetchHandler;
import fish.eyebrow.puddingaccount.handler.AccountLoginHandler;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PuddingAccountModule extends AbstractModule {

    private static final Logger logger = LoggerFactory.getLogger(PuddingAccountModule.class);


    @Override
    protected void configure() {
        try {
            bind(AccountFetchHandler.class).toInstance(new AccountFetchHandler());
            bind(AccountLoginHandler.class).toInstance(new AccountLoginHandler());

            final Properties puddingAccountProperties = new Properties();
            final InputStream propertyStream = ClassLoader.getSystemResourceAsStream("pudding_account.properties");
            puddingAccountProperties.load(Objects.requireNonNull(propertyStream));

            Names.bindProperties(binder(), puddingAccountProperties);
        }
        catch (final Exception e) {
            logger.warn("Exception occurred when setting up module: {}", e.getMessage());
        }
    }


    @Provides
    @Singleton
    private JsonObject puddingAccountConfig(@Named("server.port") final int port,
                                            @Named("server.uri.account") final String accountURI) {
        return new JsonObject()
                .put("port", port)
                .put("accountURI", accountURI);
    }


    @Inject
    @Provides
    @Singleton
    private DeploymentOptions deploymentOptions(final JsonObject puddingAccountConfig) {
        return new DeploymentOptions().setConfig(puddingAccountConfig);
    }


    @Inject
    @Provides
    @Singleton
    private PuddingAccountVerticle puddingAccountVerticle(final AccountFetchHandler accountFetchHandler, final AccountLoginHandler accountLoginHandler) {
        return new PuddingAccountVerticle(accountFetchHandler, accountLoginHandler);
    }
}
