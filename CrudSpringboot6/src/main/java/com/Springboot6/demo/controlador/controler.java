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
	
	//CONEXION CON EL LOGIN----------------------------------------------------
	
	@RequestMapping("/loginn")
	public String loginn (String username, String password, HttpSession session) {
	Usuario usuario = iusuario.findByUsername(username);
	
	//VALIDACION CON LA BASE DE DATOS-------------------------------------
	
	if (usuario !=null && usuario.getPassword().equals(password)) {
		session.setAttribute("usuario", usuario);
		return "redirect:/listar";
	}else {
		System.out.println("Autenticación fallida");
		return "loginn";	}
	
	}
	
	//BOTON REGISTRO------------------------------------------
	
	@GetMapping("/registro")
	public String registro() { 
	    return "registro";
	}
	
	//CONEXION CON REGISTRO PARA QUE PASE A LA BASE DE DATOS---------------------------------
	
	@GetMapping("/registrar")
	public String registro(String nombres, String apellidos, String dni, String username, String password, Model model) {
		//PARA QUE LOS CAMPOS DE REGISTRO TIENEN QUE ESTAR LLENOS SON OBLIGATORIOS PARA Q EN LA BASE DE DATOS NO ESTE VACIO---------------------------------------
		if (nombres == null || nombres.isEmpty() ||
		        apellidos == null || apellidos.isEmpty() ||
		        dni == null || dni.isEmpty() ||
		        username == null || username.isEmpty() ||
		        password == null || password.isEmpty()) {
			model.addAttribute("error", "Todos los campos son obligatorios.");
			return "registro";
		}
		
		//SI EL USUARIO EXISTE MANDA MENSAJE DE USUARIO EXISTENTE-----------ELSE SI EL USUARIO NO EXISTE LO REGISTRA-----------------
		Usuario usuarioExistente = iusuario.findByUsername(username);
		if (usuarioExistente != null) {
			 model.addAttribute("error", "El nombre de usuario ya esta registrado intente otro.");
			return "registro";
		}else {
		
		Usuario nuevoUsuario = new Usuario();
		nuevoUsuario.setNombre(nombres);
		nuevoUsuario.setApellido(apellidos);
		nuevoUsuario.setDni(dni);
		nuevoUsuario.setUsername(username);
		nuevoUsuario.setPassword(password);
		
		iusuario.save(nuevoUsuario);
		
		return "redirect:/loginn";
	}
	}
	
	//CODIGO DEL CRUD VALIDACION CON BASE DE DATOS----------------------------------------
			
	@Autowired
	private Iclienteservice service;

	@RequestMapping("/listar")
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
