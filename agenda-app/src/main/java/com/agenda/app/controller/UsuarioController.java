package com.agenda.app.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.app.model.Usuario;
import com.agenda.app.repository.UsuarioRepository;

@RestController //define que ira trabalahar com a API
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired //cria a instancia da Interface
    private UsuarioRepository usuarioRepository ;

    @PostMapping() //rota usuarios
        public ResponseEntity<Object> criaNovoUsuario(@RequestBody Usuario usuario){

          

            try {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(usuarioRepository.save(usuario));
            } catch (DataIntegrityViolationException d) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Tipo de usuário já existente." + d.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao criar tipo de usuário." + e.getMessage());
            }
        }

       
        @GetMapping() //rota 
        public List <Usuario> obterUsuarios(){
                return usuarioRepository.findAll();
        }

        @GetMapping(value = "/{id}") 
        public Optional<Usuario> obterUsuarioPeloId(@PathVariable("id") int id){
                return usuarioRepository.findById(id);
                //return tipoUsuarioRepository.findById(id).get(); usando esse tirar o "Optional" 
        }

        @DeleteMapping(value = "/{id}") 
        public String deletarUsuarioPeloId(@PathVariable("id") int id){
            usuarioRepository.deleteById(id);
            return "Tipo deletado com Sucesso !" ;
               
        }

        @PutMapping(value = "/usuarios") //rota usuarios
        public Usuario atualizarUsuario(@RequestBody Usuario tipoUsuario){
                Usuario tipoUsuarioBD= usuarioRepository.findById(tipoUsuario.getIdUsuario()).get();


                tipoUsuarioBD.setNome(tipoUsuario.getNome()); //usuario a ser alterado
                return usuarioRepository.save(tipoUsuarioBD);
        }


    
        @GetMapping(value = "/tipo/{id}")
        public ResponseEntity<List<Usuario>> obterUsuariosPeloTipoId(@PathVariable("id") int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByIdTipoUsuario(id));
    }
    
    }
        

       
