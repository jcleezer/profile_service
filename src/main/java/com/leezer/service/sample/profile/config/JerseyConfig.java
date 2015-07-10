package com.leezer.service.sample.profile.config;

import com.leezer.service.sample.profile.endpoint.ProfileEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        register(ProfileEndpoint.class);
    }
}
