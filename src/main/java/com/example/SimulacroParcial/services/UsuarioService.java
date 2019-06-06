package com.example.SimulacroParcial.services;

import com.example.SimulacroParcial.domain.Usuario;
import com.example.SimulacroParcial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UsuarioService {

    private String USUARIO_NOT_FOUND = "Usuario no encontrado";

    @Autowired
    IUsuarioRepository usuarios;

    public void add(Usuario usuario){
        usuarios.save(usuario);
    }

    public void update(Usuario usuario){

        Usuario oldUsuario = usuarios.findById(usuario.getId()).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,usuario.getId())));

        if(!oldUsuario.equals(usuario))
            usuarios.save(usuario);
    }

    public void delete(Integer id){
        Usuario usuario = usuarios.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,id)));
        usuarios.delete(usuario);
    }

    public List<Usuario> getAll(){
        return usuarios.findAll();
    }

    public Usuario getById(Integer id){
        return usuarios.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,id)));
    }

    public void addBrowser(Integer id, String browser) {
        Usuario usuario = usuarios.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,id)));

        usuario.setBrowser(browser);
        usuarios.save(usuario);
    }
}
