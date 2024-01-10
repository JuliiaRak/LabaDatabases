package solvd.laba.itcompany.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParser {
    private static final Logger LOGGER = LogManager.getLogger(SAXParser.class);

    public static void main(String[] args) {
        //LOGGER.info("PROGRAM STARTED");

        try {
            File xmlFile = new File("src/main/resources/xml/certification.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            SAXCertificationHandler handler = new SAXCertificationHandler();

            saxParser.parse(xmlFile, handler);

            LOGGER.info(handler.certification);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}