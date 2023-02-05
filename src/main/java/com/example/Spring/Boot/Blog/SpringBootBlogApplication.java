package com.example.Spring.Boot.Blog;

import com.example.Spring.Boot.Blog.repository.RolesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SpringBootBlogApplication  {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	RolesRepository rolesRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBlogApplication.class, args);
	}

//	/**
//	 * @param args
//	 * @throws Exception
//	 */
//	@Override
//	public void run(String... args) throws Exception {
//		Role adminRole=new Role();
//		adminRole.setName("ROLE_ADMIN");
//		rolesRepository.save(adminRole);
//
//		Role userRole=new Role();
//		userRole.setName("ROLE_USER");
//		rolesRepository.save(userRole);
//
//	}
}
