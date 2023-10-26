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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Springboot6.demo.interfaces.Iusuario;
import com.Springboot6.demo.modelo.Cliente;
import com.Springboot6.demo.modelo.Usuario;
import com.Springboot6.demo.serviciointerface.Iclienteservice;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class controler {
	/*
	@Autowired
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	*/
	
	@Autowired
	private Iusuario iusuario ;
	@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
	public String login (@RequestParam String username, @RequestParam String contraseña, HttpSession session) {
	Usuario usuario = iusuario.findByUsername(username);
	
	if (usuario !=null && usuario.getContraseña().equals(contraseña)) {
		session.setAttribute("usuario", usuario);
		return "redirect:/index2";
	}else {
		return "redirect:/login";
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
