package com.Projects.Liberary.Management.System.services;

import com.Projects.Liberary.Management.System.model.entity.Publisher;
import com.Projects.Liberary.Management.System.repo.PublisherRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepo;

    public List<Publisher> getAllPublishers() {
        return publisherRepo.findAll();

    }

    public Publisher getPublisherById(int id) {
        return publisherRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

    public void savePublisherOrUpdate(Publisher publisher) {
        publisherRepo.save(publisher);
    }

    public Optional<Publisher> deletePublisherById(int id) {
        Optional<Publisher> optional = publisherRepo.findById(id);
        if (optional.isPresent()) {
            publisherRepo.deleteById(id);
            log.info("Publisher deleted successfully: {}", optional.get().getName());
        }

        return optional;
    }

}
