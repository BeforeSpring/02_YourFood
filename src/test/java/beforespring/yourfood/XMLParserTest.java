package beforespring.yourfood;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;

import java.util.List;

@SpringBootTest
class XMLParserTest {

    @Autowired
    private XmlParser xmlParser;

    @Test
    void testXMLParsingAndOutput() throws Exception {
        Document document = xmlParser.parsing("./src/main/resources/xml/list.xml");

        String xmlContent = xmlParser.documentToString(document);
        System.out.println(xmlContent);
    }

    @Test
    void testXmlToObject() throws Exception {
        Document document = xmlParser.parsing("./src/main/resources/xml/list.xml");
        XmlMapper xmlMapper = new XmlMapper();

        XmlDtoList xmlDtoList = xmlMapper.readValue(xmlParser.documentToString(document), XmlDtoList.class);
        List<XmlDto> items = xmlDtoList.getItems();
        for (XmlDto item : items) {
            System.out.println(item.toString());
        }
    }
}