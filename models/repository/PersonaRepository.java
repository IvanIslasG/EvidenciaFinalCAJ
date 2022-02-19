package com.example.demo.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.app.models.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

}
