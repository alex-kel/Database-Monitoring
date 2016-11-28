package ru.itis.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.itis.configuration.ConfigurationHolder;
import ru.itis.configuration.beans.Configuration;
import ru.itis.configuration.beans.ObjectFactory;
import ru.itis.core.db.DatabaseInitializer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by Alex on 14.10.16.
 */

@Component
public class Runner implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigurationHolder configurationHolder;

    @Autowired
    private DatabaseInitializer databaseInitializer;

    @Override
    public void run(String... strings) throws Exception {
        logger.info("Started");
        if (strings.length > 0 && strings[0] != null) {
            readXmlConfiguration(strings[0]);
            initializeDatabasesForMonitoring();
        }
    }

    private void initializeDatabasesForMonitoring() {
        databaseInitializer.initialize();
    }

    private void readXmlConfiguration(String fileName) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ObjectFactory.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(fileName);
        Configuration configuration = (Configuration) unmarshaller.unmarshal(file);
        configurationHolder.setConnectionsConfiguration(configuration);
    }
}
