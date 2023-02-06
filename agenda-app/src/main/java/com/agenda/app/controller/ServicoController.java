package com.agenda.app.controller;

import java.util.List;
import java.util.Objects;

import org.aspectj.util.LangUtil.ProcessController.Thrown;
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

import com.agenda.app.model.Servico;
import com.agenda.app.repository.ServicoRepository;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;


@RestController //define que ira trabalahar com a API
@RequestMapping(value = "/servicos")
public class ServicoController {

    @Autowired //cria a instancia da Interface
    private ServicoRepository servicoRepository ;
   
   

       @PostMapping() //rota usuarios
      
       public ResponseEntity<Object> criaNovoServico(@RequestBody Servico servico){
          // Objects.requireNonNull(servico.getUsuario().getIdUsuario(), "ID  usuario nula!");
        
       //   if (servico.getUsuario().getIdUsuario()==0) {
       //         throw new NullPointerException(" nulo !!!!!!!");
      //  }

            try {
                
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(servicoRepository.save(servico));
            } catch (DataIntegrityViolationException d) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Tipo de Serviço sss já existente." + d.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Erro ao criar tipo de serviço." + e.getMessage());
            }
        } 

      


        @GetMapping() //rota 
        public List <Servico> obterServico(){
                return servicoRepository.findAll();
        }

   
        @DeleteMapping(value = "/{id}") 
        public String deletarUsuarioPeloId(@PathVariable("id") int id){
            servicoRepository.deleteById(id);
            return "Servico deletado com Sucesso !" ;
               
        }

        /* ante atualizar 
        @PutMapping() 
            public Servico atualizarServico(@RequestBody Servico servico){
            Servico servicoBD= servicoRepository.findById(servico.getIdServico()).get();


            servicoBD.setNome(servico.getNome()); 
            servicoBD.setDescricao(servico.getDescricao()); 
            servicoBD.setUsuario(servico.getUsuario()); 


            return servicoRepository.save(servicoBD);
        }
            */
            
        @PutMapping
        public ResponseEntity<Servico> atualizarServico(@RequestBody Servico servico) {
        return ResponseEntity.status(HttpStatus.OK).body(servicoRepository.save(servico));
    }

}
