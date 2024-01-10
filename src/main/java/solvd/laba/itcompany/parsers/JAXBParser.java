package solvd.laba.itcompany.parsers;

import jakarta.xml.bind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.itcompany.domain.Certification;

import java.io.File;

public class JAXBParser {

    private static final Logger LOGGER = LogManager.getLogger(JAXBParser.class);

    public static void main(String[] args) {
        //LOGGER.info("PROGRAM STARTED");

        try {
            File file = new File("src/main/resources/xml/certification.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Certification.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Certification certification = (Certification) jaxbUnmarshaller.unmarshal(file);
            LOGGER.info(certification);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
