package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.parsers.IXmlConverter;

import java.io.File;

public class Context {
    IXmlConverter iXmlConverter;

    public void setConverter(IXmlConverter iXmlConverter){
        this.iXmlConverter = iXmlConverter;
    }

    public Object convertXml(File file) {
        return iXmlConverter.convertXml(file);
    }
}
