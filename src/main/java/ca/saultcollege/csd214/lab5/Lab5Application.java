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

        System.out.println("===== BOOK CRUD =====");
        demoBookCrud(faker);

        System.out.println("\n===== MAGAZINE CRUD =====");
        demoMagazineCrud(faker);

        System.out.println("\n===== DISCMAG CRUD =====");
        demoDiscMagCrud(faker);

        System.out.println("\n===== TICKET CRUD =====");
        demoTicketCrud(faker);
    }

    private void demoBookCrud(Faker faker) {
        String title = faker.book().title();
        String author = faker.book().author();
        String isbn10 = faker.code().isbn10();
        String description = faker.lorem().sentence(10);
        double price = faker.number().randomDouble(2, 10, 50);
        int copies = faker.number().numberBetween(1, 20);

        // CREATE
        BookEntity book = new BookEntity(title, price, copies, isbn10, description, author);
        book = bookRepo.save(book);
        System.out.println("Created: " + book);

        // READ BY ID
        var found = bookRepo.findById(book.getId()).orElseThrow();
        System.out.println("Read by id: " + found);

        // CUSTOM QUERY: findByAuthor
        System.out.println("Books by " + author + ": " + bookRepo.findByAuthor(author));

        // READ ALL
        System.out.println("All books: " + bookRepo.findAll());

        // UPDATE (change copies)
        found.setCopies(found.getCopies() + 5);
        found = bookRepo.save(found);
        System.out.println("Updated: " + found);

        // DELETE
        bookRepo.deleteById(found.getId());
        System.out.println("After delete, findAll: " + bookRepo.findAll());
    }

    private void demoMagazineCrud(Faker faker) {
        String title = "Magazine " + faker.book().title();
        String isbn10 = faker.code().isbn10();
        String description = faker.lorem().sentence(8);
        double price = faker.number().randomDouble(2, 5, 20);
        int copies = faker.number().numberBetween(5, 50);
        int orderQty = faker.number().numberBetween(10, 100);
        LocalDate issue = LocalDate.now();

        // CREATE
        MagazineEntity mag = new MagazineEntity(
                title, price, copies, isbn10, description, orderQty, issue
        );
        mag = magRepo.save(mag);
        System.out.println("Created: " + mag);

        // READ
        var found = magRepo.findById(mag.getId()).orElseThrow();
        System.out.println("Read: " + found);

        // READ ALL
        System.out.println("All magazines: " + magRepo.findAll());

        // UPDATE (change orderQty)
        found.setOrderQty(found.getOrderQty() + 20);
        found = magRepo.save(found);
        System.out.println("Updated: " + found);

        // DELETE
        magRepo.deleteById(found.getId());
        System.out.println("After delete, findAll: " + magRepo.findAll());
    }

    private void demoDiscMagCrud(Faker faker) {
        String title = "DiscMag " + faker.book().title();
        String isbn10 = faker.code().isbn10();
        String description = faker.lorem().sentence(8);
        double price = faker.number().randomDouble(2, 7, 25);
        int copies = faker.number().numberBetween(5, 50);
        int orderQty = faker.number().numberBetween(10, 100);
        LocalDate issue = LocalDate.now().minusDays(7);

        String mediaFormat = faker.options().option("DVD", "Blu-ray", "USB", "Download");
        String topic = faker.book().genre();

        // CREATE
        DiscMagEntity discMag = new DiscMagEntity(
                title, price, copies, isbn10, description,
                orderQty, issue, mediaFormat, topic
        );
        discMag = discMagRepo.save(discMag);
        System.out.println("Created: " + discMag);

        // READ
        var found = discMagRepo.findById(discMag.getId()).orElseThrow();
        System.out.println("Read: " + found);

        // READ ALL
        System.out.println("All disc mags: " + discMagRepo.findAll());

        // UPDATE (toggle mediaFormat for fun)
        found.setMediaFormat("Streaming");
        found = discMagRepo.save(found);
        System.out.println("Updated: " + found);

        // DELETE
        discMagRepo.deleteById(found.getId());
        System.out.println("After delete, findAll: " + discMagRepo.findAll());
    }

    private void demoTicketCrud(Faker faker) {
        String description = "Admission: " + faker.commerce().productName();
        double price = faker.number().randomDouble(2, 1, 15);

        // CREATE
        TicketEntity ticket = new TicketEntity(description, price);
        ticket = ticketRepo.save(ticket);
        System.out.println("Created: " + ticket);

        // READ
        var found = ticketRepo.findById(ticket.getId()).orElseThrow();
        System.out.println("Read: " + found);

        // READ ALL
        System.out.println("All tickets: " + ticketRepo.findAll());

        // UPDATE (increase price)
        found.setPrice(found.getPrice() + 1.0);
        found = ticketRepo.save(found);
        System.out.println("Updated: " + found);

        // DELETE
        ticketRepo.deleteById(found.getId());
        System.out.println("After delete, findAll: " + ticketRepo.findAll());
    }
}
