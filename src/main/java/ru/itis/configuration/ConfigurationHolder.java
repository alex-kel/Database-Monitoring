package ru.itis.configuration;

import org.springframework.stereotype.Component;
import ru.itis.configuration.beans.Configuration;

/**
 * Created by Alex on 14.10.16.
 */

@Component
public class ConfigurationHolder {

    private Configuration connectionsConfiguration;

    public Configuration getConnectionsConfiguration() {
        return connectionsConfiguration;
    }

    public void setConnectionsConfiguration(Configuration connectionsConfiguration) {
        this.connectionsConfiguration = connectionsConfiguration;
    }

}
