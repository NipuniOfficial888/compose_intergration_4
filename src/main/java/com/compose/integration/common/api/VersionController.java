package com.compose.integration.common.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@Tag(name = "VersionController API", description = "REST API for manipulating App Version related information")
public class VersionController extends BaseController {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    @Value("${compose.property.environment}")
    private String environment;

    @Operation(summary = "Get the application version along with the configured environment")
    @GetMapping(path = "/version", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> version() {
        log.info("appName: {}, version: {}, environment: {}", appName, version, environment);
        log.debug("appName: {}, version: {}, environment: {}", appName, version, environment);
        return ResponseEntity.ok().body(Map.of("appName", appName, "version", version, "environment", environment));
    }

}
