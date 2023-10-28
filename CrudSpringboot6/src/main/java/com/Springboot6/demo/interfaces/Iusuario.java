package com.Springboot6.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Springboot6.demo.modelo.Usuario;

@Repository
public interface Iusuario extends JpaRepository<Usuario, Integer>{
 Usuario findByUsername(String username);
}
