package com.example.SimulacroParcial.DTO;

import com.example.SimulacroParcial.domain.Comentario;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

public class PublicacionDTO {

    private Integer id;
    private String titulo;
    private String descripcion;
    private String foto;
    private LocalDateTime fechaPublicacion;
    private Integer liked;
    private List<Comentario> comentarios;
}
