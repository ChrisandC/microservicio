package com.Springboot6.demo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String nombres;
	private String apellidos;
	private int dni;
	private String direccion;
	private int telefono;

public Cliente() {
	// TODO Auto-generated constructor stub
}
public Cliente(int id, String nombres, String apellidos, int dni, String direccion, int telefono) {
	super();
	this.id = id;
	this.nombres = nombres;
	this.apellidos = apellidos;
	this.dni = dni;
	this.direccion = direccion;
	this.telefono = telefono;
}
public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNombres() {
	return nombres;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public int getDni() {
	return dni;
}

public void setDni(int dni) {
	this.dni = dni;
}

public String getDireccion() {
	return direccion;
}

public void setDireccion(String direccion) {
	this.direccion = direccion;
}

public int getTelefono() {
	return telefono;
}

public void setTelefono(int telefono) {
	this.telefono = telefono;
}

}
