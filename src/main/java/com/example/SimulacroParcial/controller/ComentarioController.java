package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Comentario;
import com.example.SimulacroParcial.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;

@RequestMapping("/comentarios")
@RestController
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/publicacion/{id}")
    public void add(Comentario comentario, Integer id){
        comentarioService.add(comentario,id);
    }

    @DeleteMapping("/{id}")
    public void delete(Integer id){
        comentarioService.delete(id);
    }


}
