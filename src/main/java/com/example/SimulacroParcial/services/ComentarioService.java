package com.example.SimulacroParcial.services;

import com.example.SimulacroParcial.domain.Comentario;
import com.example.SimulacroParcial.domain.Publicacion;
import com.example.SimulacroParcial.domain.Usuario;
import com.example.SimulacroParcial.repository.IComentarioRepository;
import com.example.SimulacroParcial.repository.IPublicacionRepository;
import com.example.SimulacroParcial.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class ComentarioService {

    private String USUARIO_NOT_FOUND = "Usuario no encontrado";
    private String PUBLICACION_NOT_FOUND = "Publicacion no encontrada";
    private String COMENTARIO_NOT_FOUND = "Comentario no encontrado";

    @Autowired
    IComentarioRepository comentarios;

    @Autowired
    IPublicacionRepository publicaciones;

    @Autowired
    IUsuarioRepository usuarios;

    public void add(Comentario coment, Integer publi){

        Publicacion publicacion = publicaciones.findById(publi).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(PUBLICACION_NOT_FOUND,publi))
        );

        Usuario usuario = usuarios.findById(publicacion.getUsuario().getId()).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(USUARIO_NOT_FOUND,publicacion.getUsuario().getId()))

        );

        publicacion.getComentarios().add(coment);
        usuario.getPublicaciones().add(publicacion);

        comentarios.save(coment);
        publicaciones.save(publicacion);
        usuarios.save(usuario);
    }

    public void delete(Integer comentId){

        Comentario comentario = comentarios.findById(comentId).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(COMENTARIO_NOT_FOUND,comentId))

        );

        Publicacion publicacion = publicaciones.findById(comentario.getPublicacion().getId()).orElseThrow(
                ()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(PUBLICACION_NOT_FOUND,comentario.getPublicacion().getId()))
        );

        Usuario usuario = usuarios.findById(publicacion.getUsuario().getId()).orElseThrow(
                ()->new HttpClientErrorException(HttpStatus.NOT_FOUND,String.format(USUARIO_NOT_FOUND,publicacion.getUsuario().getId()))

        );

        publicacion.getComentarios().remove(comentario);
        usuario.getPublicaciones().set(publicacion.getId(),publicacion);

        comentarios.delete(comentario);
        publicaciones.save(publicacion);
        usuarios.save(usuario);
    }

    @Scheduled(fixedRate = timeRate) //no recuerdo como era el formato del fixedRate
    public void deleteTimer(){
        comentarios.deleteTimer();
    }

}
