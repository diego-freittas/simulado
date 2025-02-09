package br.com.razila.simulado.controller.v1;

import br.com.razila.simulado.model.Resposta;
import br.com.razila.simulado.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulado/api/respostas")
public class RespostaController {

    private @Autowired RespostaService respostaService;

    @GetMapping
    public ResponseEntity<List<Resposta>> listarTodas() {
        List<Resposta> respostas = respostaService.listarTodas();
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resposta> buscarPorId(@PathVariable Long id) {
        Resposta resposta = respostaService.buscarPorId(id);
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Resposta> criarResposta(@Valid @RequestBody Resposta resposta) {
        Resposta novaResposta = respostaService.criarResposta(resposta);
        return new ResponseEntity<>(novaResposta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resposta> atualizarResposta(@PathVariable Long id, @Valid @RequestBody Resposta resposta) {
        Resposta respostaAtualizada = respostaService.atualizarResposta(id, resposta);
        return ResponseEntity.ok(respostaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
