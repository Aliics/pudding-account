package fish.eyebrow.puddingaccount;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import io.vertx.core.DeploymentOptions;
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
    private DeploymentOptions deploymentOptions(@Named("server.verticle.instances") final int verticlesInstances) {
        return new DeploymentOptions().setInstances(verticlesInstances);
    }
}
