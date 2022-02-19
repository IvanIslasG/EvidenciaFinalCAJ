package com.example.demo.app.models.service;

import java.util.List;

import com.example.demo.app.models.entity.Persona;

public interface IPersonaService {
	
	public List<Persona> listarTodos();
	public void guardar(Persona persona);
	public Persona buscarPorId(Long id);
	public void eliminar(Long id);

}
