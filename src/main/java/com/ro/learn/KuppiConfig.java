package com.ro.learn;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by rohan on 2016-12-13.
 */
public class KuppiConfig extends Configuration {
    @JsonProperty("database")
    public Map database;

    @Valid
    @NotNull
    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration jerseyClient;

    @Valid
    @NotNull
    @JsonProperty("documentation")
    public SwaggerBundleConfiguration swaggerBundleConfiguration;
}
