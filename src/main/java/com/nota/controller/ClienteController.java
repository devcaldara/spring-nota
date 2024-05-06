package com.nota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nota.model.cliente;
import com.nota.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/salvar")
    public ResponseEntity<cliente> salvarCliente(@RequestBody cliente cli){
        cliente novoCliente = clienteRepository.save(cli);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

}
