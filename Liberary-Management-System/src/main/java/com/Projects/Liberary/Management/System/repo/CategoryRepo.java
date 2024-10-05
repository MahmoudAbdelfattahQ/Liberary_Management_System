package com.Projects.Liberary.Management.System.repo;

import com.Projects.Liberary.Management.System.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {
}
