package hym;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import java.util.Arrays;

public class BookInfo {
    @JsonProperty("title")
    @XmlElement(name="title")
    private String title;
    @JsonProperty("publishedYear")
    @XmlElement(name="publishedYear")
    private String publishedYear;
    @JsonProperty("numberOfPages")
    @XmlElement(name="numberOfPages")
    private int numberOfPages;
    @JsonProperty("authors")
    @XmlElement(name="authors")
    private String[] authors;

    public BookInfo() {
    }

    public BookInfo(String title, String publishedYear, int numberOfPages, String[] authors) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.numberOfPages = numberOfPages;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "title='" + title + '\'' +
                ", publishedYear='" + publishedYear + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", authors=" + Arrays.toString(authors) +
                '}';
    }
}
