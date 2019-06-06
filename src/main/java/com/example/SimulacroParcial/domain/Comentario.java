package com.example.SimulacroParcial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private LocalDateTime fecha;
    private String owner;
    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Publicacion publicacion;

}
