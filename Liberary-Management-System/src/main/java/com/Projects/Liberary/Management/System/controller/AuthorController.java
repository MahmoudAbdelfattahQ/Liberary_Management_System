package com.Projects.Liberary.Management.System.controller;

import com.Projects.Liberary.Management.System.model.entity.Author;
import com.Projects.Liberary.Management.System.services.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {


    private static final Logger log = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        log.info("Get all authors successfully!");
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        authorService.saveAuthorOrUpdate(author);
        log.info("Save new author successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            log.info("Get author successfully!");
            return ResponseEntity.ok(author);
        } else {
            log.error("Get author failed!");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable int id) {
        Author author = authorService.getAuthorById(id);
        if (author != null) {
            authorService.deleteAuthorById(id);
            log.info("Delete author successfully!");
            return ResponseEntity.ok("Deleted successfully!");
        } else {
            log.error("Delete author failed!");
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        Author author1 = authorService.getAuthorById(id);
        if (author1 != null) {
            author1.setName(author.getName());
            author1.setBooks(author.getBooks());
            authorService.saveAuthorOrUpdate(author1);
            log.info("Update author successfully!");
            return ResponseEntity.ok("Updated successfully! {}" + author);
        } else {
            log.error("Update author failed!");
            return ResponseEntity.notFound().build();
        }

    }

}
