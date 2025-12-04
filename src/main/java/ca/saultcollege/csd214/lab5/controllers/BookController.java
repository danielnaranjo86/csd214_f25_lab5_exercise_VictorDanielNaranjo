package ca.saultcollege.csd214.lab5.controllers;

import ca.saultcollege.csd214.lab5.entities.BookEntity;
import ca.saultcollege.csd214.lab5.repositories.BookEntityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookEntityRepository bookRepository;

    public BookController(BookEntityRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // READ – list + optional search
    @GetMapping
    public String listBooks(@RequestParam(required = false) String author, Model model) {

        List<BookEntity> books;

        if (author != null && !author.isBlank()) {
            books = bookRepository.findByAuthor(author);  // from Lab 4
            model.addAttribute("author", author);
        } else {
            books = bookRepository.findAll();
        }

        model.addAttribute("books", books);
        return "bookList";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BookEntity());
        return "bookForm";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book id: " + id));

        model.addAttribute("book", book);
        return "bookForm";
    }

    // CREATE + UPDATE (save)
    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") BookEntity book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
