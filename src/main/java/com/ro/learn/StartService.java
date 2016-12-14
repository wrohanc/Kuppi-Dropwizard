package com.ro.learn;

import com.ro.learn.health.SystemHealthCheck;
import com.ro.learn.persistence.PersistenceManager;
import com.ro.learn.resources.OrderResource;
import com.ro.learn.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Created by rohan on 2016-12-13.
 */
public class StartService extends Application<KuppiConfig> {
    public static void main(String[] args) throws Exception {
        if (args == null || args.length < 2) {
            args = new String[]{"server", "config.yaml"};
        }
        new StartService().run(args);
    }

    @Override
    public void run(KuppiConfig kuppiConfig, Environment environment) throws Exception {
        PersistenceManager.initialize(kuppiConfig.database);
        environment.jersey().register(new OrderResource());
        environment.jersey().register(new UserResource());

        final SystemHealthCheck healthCheck = new SystemHealthCheck();
        environment.healthChecks().register("healthCheck", healthCheck);
    }

    @Override
    public void initialize(Bootstrap<KuppiConfig> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<KuppiConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(KuppiConfig configuration) {
                return configuration.swaggerBundleConfiguration;
            }
        });
    }
}
