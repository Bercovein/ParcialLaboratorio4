package com.example.SimulacroParcial.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String apellido;
    private String browser;
    @OneToMany (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    @JsonIgnore
    private List<Publicacion> publicaciones;
}
