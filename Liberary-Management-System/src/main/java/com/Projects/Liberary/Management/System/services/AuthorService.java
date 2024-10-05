package com.Projects.Liberary.Management.System.services;

import com.Projects.Liberary.Management.System.model.entity.Author;
import com.Projects.Liberary.Management.System.repo.AuthorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {


    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepo authorRepo;

    @Autowired
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;

    }

    public List<Author> getAllAuthors() {
        return authorRepo.findAll();

    }

    public Author getAuthorById(int id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public void saveAuthorOrUpdate(Author author) {
        authorRepo.save(author);
    }
    public void deleteAuthorById(int id) {
        Optional<Author> optional = authorRepo.findById(id);
        if (optional.isPresent()) {
            authorRepo.deleteById(id);
            log.info("Author deleted successfully: {}", optional.get().getName());
        }


    }


}
