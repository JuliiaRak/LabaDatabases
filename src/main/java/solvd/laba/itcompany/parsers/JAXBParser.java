package solvd.laba.itcompany.parsers;

import jakarta.xml.bind.*;
import solvd.laba.itcompany.domain.Certification;

import java.io.File;

public class JAXBParser implements IXmlConverter {

    @Override
    public Object convertXml(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Certification.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Certification certification = (Certification) jaxbUnmarshaller.unmarshal(file);
            return certification;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
