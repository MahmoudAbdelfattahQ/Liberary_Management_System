package com.Projects.Liberary.Management.System.repo;

import com.Projects.Liberary.Management.System.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {


}
