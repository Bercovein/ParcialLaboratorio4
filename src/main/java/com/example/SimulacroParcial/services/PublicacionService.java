package com.example.SimulacroParcial.services;

import com.example.SimulacroParcial.domain.Publicacion;
import com.example.SimulacroParcial.domain.Usuario;
import com.example.SimulacroParcial.repository.IComentarioRepository;
import com.example.SimulacroParcial.repository.IPublicacionRepository;
import com.example.SimulacroParcial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class PublicacionService {

    private String USUARIO_NOT_FOUND = "Usuario no encontrado";
    private String PUBLICACION_NOT_FOUND = "Publicacion no encontrada";

    @Autowired
    IPublicacionRepository publicaciones;

    @Autowired
    IUsuarioRepository usuarios;

    @Autowired
    IComentarioRepository comentarios;

    public void add(Publicacion publi, Integer id){

        Usuario usuario = usuarios.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,id))
        );

        usuario.getPublicaciones().add(publi);
        publi.setUsuario(usuario);

        publicaciones.save(publi);
        usuarios.save(usuario);
    }

    public void update(Publicacion publi, Integer id){

        Usuario usuario = usuarios.findById(id).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format(USUARIO_NOT_FOUND,id))
        );

        Publicacion oldPublicacion = publicaciones.findById(publi.getId()).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(PUBLICACION_NOT_FOUND,publi.getId()))
        );

        if(!oldPublicacion.equals(publi)) {
            usuario.getPublicaciones().add(publi);
            publi.setUsuario(usuario);

            publicaciones.save(publi);
            usuarios.save(usuario);
        }
    }

    public List<Publicacion> getAll(Integer id){

        Usuario usuario = usuarios.findById(id).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(USUARIO_NOT_FOUND,id))
        );

        return publicaciones.findAll();
    }



}