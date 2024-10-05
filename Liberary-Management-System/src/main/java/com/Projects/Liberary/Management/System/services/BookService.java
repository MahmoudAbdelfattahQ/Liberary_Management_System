package com.Projects.Liberary.Management.System.services;

import com.Projects.Liberary.Management.System.model.entity.Books;
import com.Projects.Liberary.Management.System.repo.BookRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    private final BookRepo bookRepo;

    @Autowired
    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public List<Books> getAllBooks() {
        return bookRepo.findAll();

    }

    public Books getBooksById(int id) {
        return  bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public void saveBookOrUpdate(Books book) {
        bookRepo.save(book);
    }
    public Optional<Books> deleteBookById(int id) {
        Optional<Books> optional = bookRepo.findById(id);
        if (optional.isPresent()) {
            bookRepo.deleteById(id);
            log.info("Book deleted successfully: {}", optional.get().getTitle());

        }else {
            throw new RuntimeException("Book not found");

        }
        return optional;


    }

}
