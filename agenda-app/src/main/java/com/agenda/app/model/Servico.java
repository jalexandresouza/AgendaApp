package com.agenda.app.model;

import org.hibernate.annotations.NotFound;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //construotres
@AllArgsConstructor
@Data  //getseters
@Entity(name = "tb_servico")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idServico ;

    @Column(length=45, name="nome", nullable=false)
    private String nome;

    @Column(length=200, name="descricao", nullable=false)
    private String descricao;

    @NotFound
    @JoinColumn(name="idUsuario", nullable=false )
    @OneToOne
    private Usuario usuario;
    
}

