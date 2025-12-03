package ca.saultcollege.csd214.lab5.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("BOOK")
public class BookEntity extends PublicationEntity {

    @Column   // or just remove the annotation completely
    private String author;

    public BookEntity() {}

    public BookEntity(String title,
                      double price,
                      int copies,
                      String isbn10,
                      String description,
                      String author) {
        super(title, price, copies, isbn10, description);
        this.author = author;
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                '}';
    }
}
