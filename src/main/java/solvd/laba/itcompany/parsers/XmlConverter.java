package solvd.laba.itcompany.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class XmlConverter {

    private static final Logger LOGGER = LogManager.getLogger(XmlConverter.class);

    public static void main(String[] args) {
        File file = new File("src/main/resources/xml/certification.xml");
        Object xmlObject = convertXml(file, "SAX");
        LOGGER.info(xmlObject);
    }

    public static Object convertXml(File file, String how) {
        if (how == null || how.isBlank()) {
            return null;
        }
        if (how.equalsIgnoreCase("SAX")) {
            SAXParser saxParser = new SAXParser();
            return saxParser.convertXml(file);
        } else if (how.equalsIgnoreCase("DOM")) {
            DOMParser domParser = new DOMParser();
            return domParser.convertXml(file);
        } else if (how.equalsIgnoreCase("JAXB")) {
            JAXBParser jaxbParser = new JAXBParser();
            return jaxbParser.convertXml(file);
        } else return null;
    }
}
