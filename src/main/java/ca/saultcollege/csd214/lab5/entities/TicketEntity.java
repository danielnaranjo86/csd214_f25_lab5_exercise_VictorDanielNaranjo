package ca.saultcollege.csd214.lab5.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TICKET")
public class TicketEntity extends ProductEntity {

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    public TicketEntity() {}

    public TicketEntity(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "TicketEntity{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
