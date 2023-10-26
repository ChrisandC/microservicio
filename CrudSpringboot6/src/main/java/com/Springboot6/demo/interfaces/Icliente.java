package com.Springboot6.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Springboot6.demo.modelo.Cliente;

@Repository
public interface Icliente extends CrudRepository<Cliente, Integer>{

}
