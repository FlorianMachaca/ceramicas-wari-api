package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNombresRazonSocialContainingIgnoreCase(String nombre);

}