package com.example.SimulacroParcial.repository;

import com.example.SimulacroParcial.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario,Integer> {
    @Query(value = "Delete * from Comentarios where fecha < ?1", nativeQuery = true)
    void deleteTimer();
}
