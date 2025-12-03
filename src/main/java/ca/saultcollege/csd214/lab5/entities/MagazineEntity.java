package ca.saultcollege.csd214.lab5.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("MAG")
public class MagazineEntity extends PublicationEntity {

    @Column(name = "order_qty")
    private int orderQty;           // no nullable = false

    @Column(name = "curr_issue")
    private LocalDate currIssue;    // nullable allowed

    public MagazineEntity() {}

    public MagazineEntity(String title,
                          double price,
                          int copies,
                          String isbn10,
                          String description,
                          int orderQty,
                          LocalDate currIssue) {
        super(title, price, copies, isbn10, description);
        this.orderQty = orderQty;
        this.currIssue = currIssue;
    }

    public int getOrderQty() { return orderQty; }
    public void setOrderQty(int orderQty) { this.orderQty = orderQty; }

    public LocalDate getCurrIssue() { return currIssue; }
    public void setCurrIssue(LocalDate currIssue) { this.currIssue = currIssue; }

    @Override
    public String toString() {
        return "MagazineEntity{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                ", orderQty=" + orderQty +
                ", currIssue=" + currIssue +
                '}';
    }
}
