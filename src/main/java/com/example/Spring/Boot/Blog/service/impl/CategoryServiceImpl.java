package com.example.Spring.Boot.Blog.service.impl;

import com.example.Spring.Boot.Blog.dto.CategoryDto;
import com.example.Spring.Boot.Blog.exception.ResourceNotFoundException;
import com.example.Spring.Boot.Blog.model.Category;
import com.example.Spring.Boot.Blog.repository.CategoryRepository;
import com.example.Spring.Boot.Blog.repository.PostRepository;
import com.example.Spring.Boot.Blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper,
                               PostRepository postRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * @param categoryDto
     * @return
     */
    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        //Convert dto to category entity
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        //convert entity to dt0;
        CategoryDto savedCategoryDto = modelMapper.map(savedCategory, CategoryDto.class);
        return savedCategoryDto;
    }

    /**
     * @param categoryId
     * @return
     */
    @Override
    public CategoryDto getCategoryByCategoryId(long categoryId) {
        Category fecthedCategory = categoryRepository.findById(categoryId).get();
        return modelMapper.map(fecthedCategory, CategoryDto.class);
    }



    /**
     * @return
     */
    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> fetchedCategroies = categoryRepository.findAll();
        return fetchedCategroies.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
    }


    /**
     * @param categoryId
     * @return
     */
    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto categoryDto) {
        Category fetchedCategory = categoryRepository.findById(categoryId).get();
        fetchedCategory.setName(categoryDto.getName());
        fetchedCategory.setDescription(categoryDto.getDescription());
        Category savedCategory = categoryRepository.save(fetchedCategory);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    /**
     * @param categoryId
     */
    @Override
    public void deleteCategories(long categoryId) {
        Optional<Category> fectchedCategory = categoryRepository.findById(categoryId);
        if (!fectchedCategory.isPresent()) throw new ResourceNotFoundException("category", "id", categoryId);
        categoryRepository.deleteById(categoryId);
    }

}
