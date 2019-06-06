package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPublicacionRepository extends JpaRepository<Publicacion,Integer> {
}
