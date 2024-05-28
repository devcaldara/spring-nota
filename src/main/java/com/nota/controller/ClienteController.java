package com.nota.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nota.model.cliente;
import com.nota.repository.ClienteRepository;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:3000")

public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/salvar")
    public ResponseEntity<cliente> salvarCliente(@RequestBody cliente cli){
        cliente novoCliente = clienteRepository.save(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<cliente>> listarCliente(){
        List<cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        Optional<cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // build é para renderizar na tela qnd não tem valor
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<cliente> atualizarCliente(@PathVariable Long id, @RequestBody cliente cli){
        Optional<cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            cli.setId(id); //para atualizar precisa de um objeto inteiro (com id tbm)
            clienteRepository.save(cli);
            return ResponseEntity.ok(cli);
        }

    }

    /*@PutMapping("/{id}")
    public ResponseEntity<cliente> atualizarCliente2( @RequestBody cliente cli){
        Optional<cliente> clienteOptional = clienteRepository.findById(cli.getId());
        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            clienteRepository.save(cli);
            return ResponseEntity.ok(cli);
        }

    }*/

}
