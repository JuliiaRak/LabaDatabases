package solvd.laba.itcompany.parsers;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParser implements IXmlConverter {

    @Override
    public Object convertXml(File xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            SAXCertificationHandler handler = new SAXCertificationHandler();

            saxParser.parse(xmlFile, handler);

            return handler.certification;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}