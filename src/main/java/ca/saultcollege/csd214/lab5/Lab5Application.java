package ca.saultcollege.csd214.lab5;

import ca.saultcollege.csd214.lab5.entities.BookEntity;
import ca.saultcollege.csd214.lab5.entities.MagazineEntity;
import ca.saultcollege.csd214.lab5.entities.DiscMagEntity;
import ca.saultcollege.csd214.lab5.entities.TicketEntity;
import ca.saultcollege.csd214.lab5.repositories.BookEntityRepository;
import ca.saultcollege.csd214.lab5.repositories.MagazineEntityRepository;
import ca.saultcollege.csd214.lab5.repositories.DiscMagEntityRepository;
import ca.saultcollege.csd214.lab5.repositories.TicketEntityRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Lab5Application implements CommandLineRunner {

    private final BookEntityRepository bookRepo;
    private final MagazineEntityRepository magRepo;
    private final DiscMagEntityRepository discMagRepo;
    private final TicketEntityRepository ticketRepo;

    public Lab5Application(BookEntityRepository bookRepo,
                           MagazineEntityRepository magRepo,
                           DiscMagEntityRepository discMagRepo,
                           TicketEntityRepository ticketRepo) {
        this.bookRepo = bookRepo;
        this.magRepo = magRepo;
        this.discMagRepo = discMagRepo;
        this.ticketRepo = ticketRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(Lab5Application.class, args);
    }

    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        // Optional: avoid double seeding if ddl-auto is not "create"
        if (bookRepo.count() == 0) {
            seedBooks(faker);
        }
        if (magRepo.count() == 0) {
            seedMagazines(faker);
        }
        if (discMagRepo.count() == 0) {
            seedDiscMags(faker);
        }
        if (ticketRepo.count() == 0) {
            seedTickets(faker);
        }

        System.out.println("Database seeded for Lab 5.");
    }

    private void seedBooks(Faker faker) {
        // some random books
        for (int i = 0; i < 10; i++) {
            String title = faker.book().title();
            String author = faker.book().author();
            String isbn10 = faker.code().isbn10();
            String description = faker.lorem().sentence(10);
            double price = faker.number().randomDouble(2, 10, 50);
            int copies = faker.number().numberBetween(1, 20);

            BookEntity book = new BookEntity(title, price, copies, isbn10, description, author);
            bookRepo.save(book);
        }

        // example hard-coded book like in lecture
        BookEntity caesar = new BookEntity(
                "Julius Caesar",
                19.99,
                5,
                "1234567890",
                "Shakespeare classic.",
                "William Shakespeare"
        );
        bookRepo.save(caesar);
    }

    private void seedMagazines(Faker faker) {
        for (int i = 0; i < 5; i++) {
            String title = "Magazine " + faker.book().title();
            String isbn10 = faker.code().isbn10();
            String description = faker.lorem().sentence(8);
            double price = faker.number().randomDouble(2, 5, 20);
            int copies = faker.number().numberBetween(5, 50);
            int orderQty = faker.number().numberBetween(10, 100);
            LocalDate issue = LocalDate.now().minusMonths(i);

            MagazineEntity mag = new MagazineEntity(
                    title, price, copies, isbn10, description, orderQty, issue
            );
            magRepo.save(mag);
        }
    }

    private void seedDiscMags(Faker faker) {
        for (int i = 0; i < 5; i++) {
            String title = "DiscMag " + faker.book().title();
            String isbn10 = faker.code().isbn10();
            String description = faker.lorem().sentence(8);
            double price = faker.number().randomDouble(2, 7, 25);
            int copies = faker.number().numberBetween(5, 50);
            int orderQty = faker.number().numberBetween(10, 100);
            LocalDate issue = LocalDate.now().minusWeeks(i);

            String mediaFormat = faker.options().option("DVD", "Blu-ray", "USB", "Download");
            String topic = faker.book().genre();

            DiscMagEntity discMag = new DiscMagEntity(
                    title, price, copies, isbn10, description,
                    orderQty, issue, mediaFormat, topic
            );
            discMagRepo.save(discMag);
        }
    }

    private void seedTickets(Faker faker) {
        for (int i = 0; i < 10; i++) {
            String description = "Admission: " + faker.commerce().productName();
            double price = faker.number().randomDouble(2, 1, 15);

            TicketEntity ticket = new TicketEntity(description, price);
            ticketRepo.save(ticket);
        }
    }
}
