package com.Springboot6.demo.serviciointerface;

import java.util.List;
import java.util.Optional;

import com.Springboot6.demo.modelo.Cliente;

public interface Iclienteservice {
	public List<Cliente>listar();
	public Optional<Cliente>listarId(int id);
	public int save(Cliente c);
	public void delete (int id);
}
