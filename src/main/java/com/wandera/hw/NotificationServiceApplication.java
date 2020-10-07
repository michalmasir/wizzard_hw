package com.wandera.hw;

import com.wandera.hw.resources.NotificationResource;
import com.wandera.hw.resources.UsersResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class NotificationServiceApplication extends Application<NotificationServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new NotificationServiceApplication().run("server", "notification-service.yaml");
    }

    @Override
    public String getName() {
        return "NotificationService";
    }

    @Override
    public void initialize(final Bootstrap<NotificationServiceConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

    @Override
    public void run(final NotificationServiceConfiguration configuration,
                    final Environment environment) {
        configuration.loadSampleData();
        final NotificationResource notificationResource = new NotificationResource(configuration.getDataStructureWrapper());
        final UsersResource usersResource = new UsersResource(configuration.getDataStructureWrapper());
        environment.jersey().register(notificationResource);
        environment.jersey().register(usersResource);
    }

}
