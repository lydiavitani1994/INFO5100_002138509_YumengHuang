package hym;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.readJson("book_shelf.json");
        main.readXml("book_shelf.xml");
    }

    public void readJson(String fileName) throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

        URL jsonUrl = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        BookShelf bookShelf = jsonMapper.readValue(jsonUrl, BookShelf.class);

        System.out.println(bookShelf.toString());

        bookShelf.getBooks().add(
                new Book(
                        new BookInfo("book4", "4000", 400, new String[]{"author4"})
                )
        );
        String jsonString = jsonMapper.writeValueAsString(bookShelf);
        System.out.println(jsonString);
    }

    public void readXml(String fileName) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookShelf.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        URL xmlUrl = this.getClass()
                .getClassLoader()
                .getResource(fileName);

        BookShelf bookShelf = (BookShelf) jaxbUnmarshaller.unmarshal(new File(xmlUrl.getFile()));

        System.out.println(bookShelf.toString());

        bookShelf.getBooks().add(
                new Book(
                        new BookInfo("book4", "4000", 400, new String[]{"author4"})
                )
        );
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(bookShelf, sw);
        String xmlString = sw.toString();
        System.out.println(xmlString);
    }
}
