package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.estudiante.Estudiante;
import com.example.demo.estudiante.repository.estudianteRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class estudianteRepositoryTest {

	@Autowired
	private estudianteRepository repository;
	
	@Test
	public void testEstudianteRepository() {
		Estudiante estudianteSave = repository.save(new Estudiante(1001996330, "Roberto", "Ortega", "3015453293", "mail@mail.com"));
		assertThat(estudianteSave.getCedula()).isGreaterThan(0);
	}
	
	
}
