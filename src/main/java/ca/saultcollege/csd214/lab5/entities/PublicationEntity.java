package ca.saultcollege.csd214.lab5.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PUB")
public abstract class PublicationEntity extends ProductEntity {

    @Column
    private String title;

    @Column
    private double price;

    @Column
    private int copies;

    @Column(name = "isbn_10")
    private String isbn10;

    @Column(length = 1000)
    private String description;

    public PublicationEntity() {}

    public PublicationEntity(String title,
                             double price,
                             int copies,
                             String isbn10,
                             String description) {
        this.title = title;
        this.price = price;
        this.copies = copies;
        this.isbn10 = isbn10;
        this.description = description;
    }

    // getters / setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getCopies() { return copies; }
    public void setCopies(int copies) { this.copies = copies; }

    public String getIsbn10() { return isbn10; }
    public void setIsbn10(String isbn10) { this.isbn10 = isbn10; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "PublicationEntity{" +
                "id=" + getId() +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", copies=" + copies +
                ", isbn10='" + isbn10 + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
