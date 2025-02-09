package br.com.razila.simulado.controller.v1;

import br.com.razila.simulado.controller.dto.MateriaDTO;
import br.com.razila.simulado.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulado/api/materias")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> listarTodas() {
        List<MateriaDTO> materias = materiaService.listarTodas();
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> buscarPorId(@PathVariable Long id) {
        MateriaDTO materia = materiaService.buscarPorId(id);
        return ResponseEntity.ok(materia);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> criarMateria(@Valid @RequestBody MateriaDTO materia) {
        MateriaDTO novaMateria = materiaService.criarMateria(materia);
        return new ResponseEntity<>(novaMateria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> atualizarMateria(@PathVariable Long id, @Valid @RequestBody MateriaDTO materia) {
        MateriaDTO materiaAtualizada = materiaService.atualizarMateria(id, materia);
        return ResponseEntity.ok(materiaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria(@PathVariable Long id) {
        materiaService.deletarMateria(id);
        return ResponseEntity.noContent().build();
    }
}
