package com.example.demo.estudiante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.estudiante.Estudiante;

public interface estudianteRepository extends JpaRepository<Estudiante, Integer>{

	
	@Query("SELECT e FROM Estudiante e WHERE CAST(e.cedula AS string) LIKE CONCAT(:cedula, '%')")
	List<Estudiante> findByCedula(@Param("cedula") Integer cedula);
}
