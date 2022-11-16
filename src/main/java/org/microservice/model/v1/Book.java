package org.microservice.model.v1;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Book {
    private int id;
    private String title;
    private String author;

    public Book() {
        super();
    }

    public Book(int id, String title, String author) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @XmlElement
    public int getId() {
        return (this.id);
    }

    public void setId(final int id) {
        this.id = id;
    }

    @XmlElement
    public String getTitle() {
        return (this.title);
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @XmlElement
    public String getAuthor() {
        return (this.author);
    }

    public void setAuthor(final String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return (result);
    }

    @Override
    public boolean equals(Object obj) {
        Book other;

        if (this == obj) {
            return (true);
        }
        if (obj == null) {
            return (false);
        }
        if ((obj instanceof Book)) {
            return (false);
        }
        other = (Book) obj;
        if (id != other.id) {
            return (false);
        }
        if (title == null) {
            if (other.title != null) {
                return (false);
            }
        } else if (!title.equals(other.title)) {
            return (false);
        }
        if (author == null) {
            if (other.author != null) {
                return (false);
            }
        } else if (!author.equals(other.author)) {
            return (false);
        }
        return (true);
    }
}