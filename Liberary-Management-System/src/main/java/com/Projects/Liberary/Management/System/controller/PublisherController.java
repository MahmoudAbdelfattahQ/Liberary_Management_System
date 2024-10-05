package com.Projects.Liberary.Management.System.controller;

import com.Projects.Liberary.Management.System.model.entity.Publisher;
import com.Projects.Liberary.Management.System.services.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    private final PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    public ResponseEntity<List<Publisher>> getAllPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @PostMapping
    public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
        publisherService.savePublisherOrUpdate(publisher);
        log.info("publisher {} saved! ", publisher);
        return ResponseEntity.ok(publisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable int id) {
        Optional<Publisher> publisherOptional = publisherService.deletePublisherById(id);
        if (publisherOptional.isPresent()) {
            log.info("Publisher {} deleted! ", publisherOptional.get());
            return ResponseEntity.ok(publisherOptional.get());

        } else {
            log.info("Can not delete Publisher not found! ");
            return ResponseEntity.of(publisherOptional);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Publisher publisher) {
        Publisher publisher1 = publisherService.getPublisherById(id);
        if (publisher1 != null) {
            publisher1.setName(publisher.getName());
            publisher1.setBooks(publisher.getBooks());
            publisherService.savePublisherOrUpdate(publisher);
            log.info("Publisher {} updated! ", publisher);
            return ResponseEntity.ok("Publisher {} updated successfully!"+ publisher.getName());
        }else {
            log.info("Can not update Publisher not found ! ");
            return ResponseEntity.notFound().build();
        }


    }


}
