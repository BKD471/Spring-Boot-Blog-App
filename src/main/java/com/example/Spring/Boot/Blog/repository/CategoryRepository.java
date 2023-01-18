package com.example.Spring.Boot.Blog.repository;

import com.example.Spring.Boot.Blog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
