package hym;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name="BookShelf")
public class BookShelf {
    @JsonProperty("BookShelf")
    @XmlElement(name="Book")
    private List<Book> books;

    public BookShelf() {
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "bookShelf=" + books +
                '}';
    }
}
