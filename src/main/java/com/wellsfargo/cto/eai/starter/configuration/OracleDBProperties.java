package com.wellsfargo.cto.eai.starter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("oracle")
@Component
public class OracleDBProperties {
}
