package hym;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Book {
    @JsonProperty("Book")
    @XmlElement(name="Book")
    private BookInfo book;

    public Book() {
    }

    public Book(BookInfo book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book=" + book +
                '}';
    }
}
