package com.agenda.app.model;



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
@Entity(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario ;

    @Column(length=45, name="nome",  nullable=false)
    private String nome;

    @Column(length=45, name="sobrenome", nullable=false)
    private String sobrenome;

    @Column(length=45, name="email", unique = true, nullable=false)
    private String email;

    @Column(length=45, name="senha", nullable=false)
    private String senha;
     

   @JoinColumn(name="idTipoUsuario", nullable=false )
   @OneToOne
   private TipoUsuario  tipoUsuario;









    
}
