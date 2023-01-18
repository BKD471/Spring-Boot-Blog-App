package com.example.Spring.Boot.Blog.service;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.dto.PostDto;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto getCategoryByCategoryId(long categoryId);
    List<PostDto> getAllPostsByCategoryId(long categoryId);
    List<CategoryDto> getAllCategories();
}
