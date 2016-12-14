package com.ro.learn.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by rohanw on 11/17/2015.
 */
public class SystemHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
