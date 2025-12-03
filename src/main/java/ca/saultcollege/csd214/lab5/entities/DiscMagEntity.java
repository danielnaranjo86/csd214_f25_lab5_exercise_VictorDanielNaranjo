package ca.saultcollege.csd214.lab5.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("DISCMAG")
public class DiscMagEntity extends MagazineEntity {

    @Column
    private String mediaFormat;

    @Column
    private String topic;

    public DiscMagEntity() {}

    public DiscMagEntity(String title,
                         double price,
                         int copies,
                         String isbn10,
                         String description,
                         int orderQty,
                         LocalDate currIssue,
                         String mediaFormat,
                         String topic) {

        super(title, price, copies, isbn10, description, orderQty, currIssue);
        this.mediaFormat = mediaFormat;
        this.topic = topic;
    }

    public String getMediaFormat() { return mediaFormat; }
    public void setMediaFormat(String mediaFormat) { this.mediaFormat = mediaFormat; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    @Override
    public String toString() {
        return "DiscMagEntity{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", price=" + getPrice() +
                ", copies=" + getCopies() +
                ", orderQty=" + getOrderQty() +
                ", currIssue=" + getCurrIssue() +
                ", mediaFormat='" + mediaFormat + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
