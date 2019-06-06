package com.example.SimulacroParcial.controller;


import com.example.SimulacroParcial.DTO.PublicacionDTO;
import com.example.SimulacroParcial.domain.Publicacion;
import com.example.SimulacroParcial.services.PublicacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/publicaciones")
@RestController
public class PublicacionController {

    @Autowired
    PublicacionService publicacionService;

    @PostMapping("/usuario/{id}")
    public void add (@RequestBody Publicacion publicacion,@PathVariable Integer id){
        publicacionService.add(publicacion,id);
    }

    @GetMapping("/usuario/{id}")
    public List<PublicacionDTO> getAllbyUser(@PathVariable Integer id){

        List<Publicacion> publicaciones = publicacionService.getAll(id);

        return publicaciones.stream().map(publicacion->convertToDto(publicacion)).collect(Collectors.toList());
    }

    public PublicacionDTO convertToDto(Publicacion publicacion){

        return new ModelMapper().map(publicacion,PublicacionDTO.class);
    }



}
