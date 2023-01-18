package com.example.Spring.Boot.Blog.controller;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.dto.PostDto;
import com.example.Spring.Boot.Blog.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategoryDto=categoryService.addCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoryDto);
    }


    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryByCategoryId(@PathVariable(name="categoryId") long categoryId){
        CategoryDto categoryDto=categoryService.getCategoryByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }

    @GetMapping("/{categoryId}/posts")
    public List<PostDto> getAllPostsByCategoryId(@PathVariable(name="categoryId") long categoryId){
        return categoryService.getAllPostsByCategoryId(categoryId);
    }

    @GetMapping
    public  List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }
}
