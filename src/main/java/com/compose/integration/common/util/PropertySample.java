package com.compose.integration.common.util;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class PropertySample {

    private static final Logger logger = LoggerFactory.getLogger(PropertySample.class);

    private final Environment environment;

    @Autowired
    public PropertySample(Environment environment, ConfigurableEnvironment configurableEnvironment) {
        this.environment = environment;
    }

    @Value("${compose.property.environment}")
    private String composePropertyEnvironment;

    @PostConstruct
    public void printPropertySample() {
        logger.info("AZURE_KEYVAULT_URI" + ": " + environment.getProperty("AZURE_KEYVAULT_URI"));
        logger.info("AZURE_KEYVAULT_TENANT_ID" + ": " + environment.getProperty("AZURE_KEYVAULT_TENANT_ID"));
        logger.info("AZURE_KEYVAULT_CLIENT_ID" + ": " + environment.getProperty("AZURE_KEYVAULT_CLIENT_ID"));
        logger.info(environment.getProperty("AZURE_KEYVAULT_CLIENT_SECRET") != null ? "AZURE_KEYVAULT_CLIENT_SECRET: EXIST" : "AZURE_KEYVAULT_CLIENT_SECRET: NULL");
        logger.info("APM_ENABLE" + ": " + environment.getProperty("APM_ENABLE"));
        logger.info("NEW_RELIC_APP_NAME" + ": " + environment.getProperty("NEW_RELIC_APP_NAME"));
        logger.info(environment.getProperty("NEW_RELIC_LICENSE_KEY") != null ? "NEW_RELIC_LICENSE_KEY: EXIST" : "NEW_RELIC_LICENSE_KEY: NULL");
        logger.info("PropertySample composePropertyEnvironment: " + composePropertyEnvironment);
    }

}
