package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{

	@Autowired
	private UsersRepository usersRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Users> userList = usersRepository.findAll();
		System.out.println(userList);

		userList = usersRepository.searchUser("johndoe", "password");
		System.out.println(userList);

		userList = usersRepository.searchUserError("johndoe", "password");
		System.out.println(userList);

		userList = usersRepository.searchUserError("johndoe", "123' OR 'TRUE");
		System.out.println(userList);
	}

}
