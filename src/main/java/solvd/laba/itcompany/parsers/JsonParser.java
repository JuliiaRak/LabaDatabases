package solvd.laba.itcompany.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.domain.ProjectEmployee;

import java.io.File;
import java.io.IOException;

public class JsonParser {

    private static final Logger LOGGER = LogManager.getLogger(JsonParser.class);

    public static void main(String[] args) {
        //LOGGER.info("PROGRAM STARTED");
        File certificationFile = new File("src/main/resources/json/certification.json");
        File projectEmployeeFile = new File("src/main/resources/json/projectEmployee.json");

        ObjectMapper mapper = new ObjectMapper();
        try {
            Certification certification = mapper.readValue(certificationFile, Certification.class);
            ProjectEmployee projectEmployee = mapper.readValue(projectEmployeeFile, ProjectEmployee.class);

            LOGGER.info(certification);
            LOGGER.info(projectEmployee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}