package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.estudiante.Estudiante;
import com.example.demo.estudiante.repository.estudianteRepository;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

@Controller
public class estudiante_controller {

	
	@Autowired
	private estudianteRepository EstudianteRepository;
	
	@GetMapping("/")
	public String listarEstudiantes(Model modelo) {
		List<Estudiante> listaEstudiantes = EstudianteRepository.findAll();
		modelo.addAttribute("listaEstudiantes", listaEstudiantes);
		return "index";
	}
	
	@GetMapping("/estudiante/nuevo")
	public String nuevoEstudiante() {
		return "nuevoEstudiante";
	}
	
	@PostMapping("/CrearEstudiante")
	public String CrearEstudiante(@ModelAttribute Estudiante estudiante) {
		EstudianteRepository.save(estudiante);
		return "redirect:/";
	}
	
	@GetMapping("/buscarPorCedula")
	public String buscarEstudiante(@RequestParam Integer cedula, Model modelo) {
	    List<Estudiante> estudiantes = EstudianteRepository.findByCedula(cedula);
	    modelo.addAttribute("listaEstudiantes", estudiantes);
	    return "fragmentoTablaEstudiantes";
	    
	}
	@PostMapping("/eliminarEstudiante")
	public String eliminarEstudiante(@RequestParam Integer cedula) {
		EstudianteRepository.deleteById(cedula);
		return "redirect:/";
		
	}
	
	@PostMapping("/editarEstudiante")
	public String editarEstudiante(@RequestParam Integer cedula, @ModelAttribute Estudiante estudiante) {
		estudiante.setCedula(cedula);
		EstudianteRepository.save(estudiante);
		return "redirect:/";
		
	}
}
