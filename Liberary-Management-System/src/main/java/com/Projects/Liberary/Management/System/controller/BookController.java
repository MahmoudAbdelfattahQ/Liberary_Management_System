package com.Projects.Liberary.Management.System.controller;

import com.Projects.Liberary.Management.System.model.entity.Books;
import com.Projects.Liberary.Management.System.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")

public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
       List<Books> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PostMapping
    public ResponseEntity<Books> saveBook(@RequestBody Books book) {
        bookService.saveBookOrUpdate(book);
        log.info("Book {} saved! ", book);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Books> deleteBook(@PathVariable int id) {
        Optional<Books> booksOptional = bookService.deleteBookById(id);
        if (booksOptional.isPresent()) {
            log.info("Book {} deleted! ", booksOptional.get());
            return ResponseEntity.ok(booksOptional.get());

        } else {
            log.info("Can not delete Book not found! ");
            return ResponseEntity.of(booksOptional);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable int id, @RequestBody Books book) {
        Books book1 = bookService.getBooksById(id);
        if (book1 != null) {
            book1.setTitle(book.getTitle());
            book1.setAuthors(book.getAuthors());
            book1.setCategories(book.getCategories());
            book1.setPublishers(book.getPublishers());
            log.info("Book {} updated! ", book);
            return ResponseEntity.ok("Book {} updated successfully!"+ book.getTitle());
        }else {
            log.info("Can not update book not found ! ");
            return ResponseEntity.notFound().build();
        }


    }

}
