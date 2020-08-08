package com.stoom.controller;

import com.google.maps.errors.ApiException;
import com.stoom.model.Endereco;
import com.stoom.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;
    private Endereco endereco;

    @GetMapping
    public List<Endereco> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
        return new ResponseEntity<Endereco>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Endereco> save(@RequestBody Endereco endereco) throws InterruptedException, ApiException, IOException {
        return new ResponseEntity<Endereco>(service.save(endereco), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable("id") Integer id, @RequestBody Endereco enderecoUpdated) throws InterruptedException, ApiException, IOException {
        return new ResponseEntity<Endereco>(service.update(id, enderecoUpdated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Endereco> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<Endereco>(endereco, HttpStatus.OK);
    }
}
