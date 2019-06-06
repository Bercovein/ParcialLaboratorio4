package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.DTO.UsuarioDTO;
import com.example.SimulacroParcial.domain.Usuario;
import com.example.SimulacroParcial.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping ("/usuarios")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("")
    public void add(@RequestBody Usuario usuario){
        usuarioService.add(usuario);
    }

    @PutMapping("")
    public void update(@RequestBody Usuario usuario){
        usuarioService.update(usuario);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.delete(id);
    }

    @GetMapping("")
    public List<UsuarioDTO> getAll(){

        List<Usuario> usuarios = usuarioService.getAll();

        return  usuarios.stream().map(usuario->convertToDto(usuario)).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public UsuarioDTO getById(@PathVariable("id") Integer id){

        Usuario usuario = usuarioService.getById(id);

        return this.convertToDto(usuario);
    }

    @GetMapping("/header/{id}")
    public void header(@PathVariable Integer id, @RequestHeader String browser){
        usuarioService.addBrowser(id,browser);
    }


    public UsuarioDTO convertToDto(Usuario usuario){

        return new ModelMapper().map(usuario,UsuarioDTO.class);
    }

}
