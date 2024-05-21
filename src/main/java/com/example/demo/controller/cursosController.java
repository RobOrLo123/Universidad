package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.cursos.Cursos;
import com.example.demo.cursos.repository.CursosRepository;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class cursosController {

	@Autowired
	private CursosRepository cursosRepository;
	
	@GetMapping("/verCursos")
	public String listaCursos(Model model) {
		List<Cursos> listarCursos = cursosRepository.findAll();
		model.addAttribute("listarCursos", listarCursos);
		return "/listaCursos";
	}
	
	@GetMapping("/curso/nuevo")
	public String formularioCrearCurso() {
		return "cursos/nuevoCurso";
	}
	
	@PostMapping("/crearCurso")
	public String crearCurso(@ModelAttribute Cursos curso) {
		cursosRepository.save(curso);
		return "redirect:/";
	}
	
	@PostMapping("/editarCurso")
	public String editarCurso(@RequestParam String codigo, @ModelAttribute Cursos curso) {
		curso.setCodigo(codigo);
		cursosRepository.save(curso);
		return "redirect:/verCursos";
	}
	
	
}
