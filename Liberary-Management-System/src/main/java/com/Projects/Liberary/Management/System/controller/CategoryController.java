package com.Projects.Liberary.Management.System.controller;

import com.Projects.Liberary.Management.System.model.entity.Category;
import com.Projects.Liberary.Management.System.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping

    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        categoryService.saveCategoryOrUpdate(category);
        log.info("category {} saved! ", category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable int id) {
        Optional<Category> categoryOptional = categoryService.deleteCategoryById(id);
        if (categoryOptional.isPresent()) {
            log.info("category {} deleted! ", categoryOptional.get());
            return ResponseEntity.ok(categoryOptional.get());

        } else {
            log.info("Can not delete category not found! ");
            return ResponseEntity.of(categoryOptional);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable int id, @RequestBody Category category) {
        Category category1 = categoryService.getCategoryById(id);
        if (category1 != null) {
            category1.setCategoryName(category.getCategoryName());
            category1.setBooks(category.getBooks());

            log.info("category {} updated! ", category);
            return ResponseEntity.ok("category {} updated successfully!"+ category.getCategoryName());
        }else {
            log.info("Can not update category not found ! ");
            return ResponseEntity.notFound().build();
        }


    }

}
