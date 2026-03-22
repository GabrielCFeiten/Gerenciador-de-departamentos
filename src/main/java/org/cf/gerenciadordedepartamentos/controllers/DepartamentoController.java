package org.cf.gerenciadordedepartamentos.controllers;

import org.cf.gerenciadordedepartamentos.models.DepartamentoModel;
import org.cf.gerenciadordedepartamentos.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping
    public ResponseEntity<List<DepartamentoModel>> getAll() {
        List<DepartamentoModel> request = departamentoService.findAll();
        return ResponseEntity.ok(request);
    }

    @PostMapping
    public ResponseEntity<DepartamentoModel> create(@RequestBody DepartamentoModel departamento) {
        DepartamentoModel request = departamentoService.save(departamento);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(departamento.getId())
                .toUri();
        return  ResponseEntity.created(uri).body(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        departamentoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DepartamentoModel departamento) {
        departamentoService.update(id, departamento);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartamentoModel> findById(@PathVariable Long id) {
        DepartamentoModel departamento = departamentoService.findById(id);

        if (departamento != null) {
            return ResponseEntity.ok(departamento);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
