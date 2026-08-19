package com.florian.ceramicaswari.repository;

import com.florian.ceramicaswari.model.Artesano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtesanoRepository
        extends JpaRepository<Artesano, Integer> {

}