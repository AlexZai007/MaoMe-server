package com.maome.springjwt;

import com.maome.springjwt.controllers.NoteControler;
import com.maome.springjwt.models.Note;
import com.maome.springjwt.models.Photo;
import com.maome.springjwt.repository.NoteRepository;
import com.maome.springjwt.repository.PhotoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MaoMe {

	public static void main(String[] args) {

 		SpringApplication.run(MaoMe.class, args);


	}

}
