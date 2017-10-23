package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bartoszpietrzak on 22/10/2017.
 */
@Configuration
@ComponentScan(basePackages = { "model.signal", "service" }, excludeFilters = @ComponentScan.Filter(value = Configuration.class))
public class DigitalSignalProcessingConfiguration
{
}