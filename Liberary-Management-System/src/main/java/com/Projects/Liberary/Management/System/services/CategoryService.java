package com.Projects.Liberary.Management.System.services;


import com.Projects.Liberary.Management.System.model.entity.Category;
import com.Projects.Liberary.Management.System.repo.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CategoryService {


    private final CategoryRepo categoryRepo;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();

    }

    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void saveCategoryOrUpdate(Category category) {
        categoryRepo.save(category);
    }
    public Optional<Category> deleteCategoryById(int id) {
        Optional<Category> optional = categoryRepo.findById(id);
        if (optional.isPresent()) {
            categoryRepo.deleteById(id);
            log.info("Category deleted successfully: {}", optional.get().getCategoryName());
        }
return optional;
    }
}
