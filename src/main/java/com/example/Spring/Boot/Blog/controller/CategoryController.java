package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping("/v1/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategoryDto=categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDto);
    }


    @GetMapping("/v1/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryByCategoryId(@PathVariable(name="id") long categoryId){
        CategoryDto categoryDto=categoryService.getCategoryByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @GetMapping("/v1/categories")
    public  ResponseEntity<List<CategoryDto>> getAllCategories(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAllCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/categories/{id}")
    public  ResponseEntity<CategoryDto> updateCategory(@PathVariable(name="id") long categoryId,
                                                       @RequestBody CategoryDto categoryDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.updateCategory(categoryId,categoryDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name="id") long categoryId){
        categoryService.deleteCategories(categoryId);
        String response=String.format("Message with id %s got deleted successfully",categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
