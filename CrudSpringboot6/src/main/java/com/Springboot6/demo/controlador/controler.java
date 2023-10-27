package com.Springboot6.demo.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Springboot6.demo.interfaces.Iusuario;
import com.Springboot6.demo.modelo.Cliente;
import com.Springboot6.demo.modelo.Usuario;
import com.Springboot6.demo.serviciointerface.Iclienteservice;

import jakarta.servlet.http.HttpSession;

@Controller
public class controler {

	
	@Autowired
	private Iusuario iusuario ;
	@RequestMapping("/loginn")
	public String loginn (String username, String password, HttpSession session) {
	Usuario usuario = iusuario.findByUsername(username);
	
	if (usuario !=null && usuario.getPassword().equals(password)) {
		session.setAttribute("usuario", usuario);
		return "/index2";
	}else {
		return "/loginn";
	}

	}


	@Autowired
	private Iclienteservice service;
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Cliente>clientes=service.listar();
		model.addAttribute("clientes", clientes);
		return "index2";
	}
	@GetMapping("/new")
	public String agregar(Model model) {
		model.addAttribute("cliente" ,new Cliente());
		return "form";
	}
	@PostMapping("/save")
	public String save(@Validated Cliente c, Model model){
		service.save(c);
		return "redirect:/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable int id, Model model) {
		Optional<Cliente>cliente=service.listarId(id);
		model.addAttribute("cliente", cliente);
		return "form";
	}
	@GetMapping("eliminar/{id}")
	public String delete(Model model, @PathVariable int id) {
		service.delete(id);
		return "redirect:/listar";
	}
	
}
