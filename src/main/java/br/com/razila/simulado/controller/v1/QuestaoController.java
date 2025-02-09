package br.com.razila.simulado.controller.v1;


import br.com.razila.simulado.controller.dto.QuestaoDTO;
import br.com.razila.simulado.service.QuestaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simulado/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService questaoService;

    /**
     * Retorna uma lista de QuestaoDTO.
     *
     * @return ResponseEntity<List<QuestaoDTO>>
     */
    @GetMapping
    public ResponseEntity<List<QuestaoDTO>> listarTodas() {
        List<QuestaoDTO> questoesDTO = questaoService.listarTodas();
        return ResponseEntity.ok(questoesDTO);
    }

    @GetMapping("/materia/{codigoMateria}")
    public ResponseEntity<List<QuestaoDTO>> listarPorMaterias(@PathVariable Long codigoMateria) {
        List<QuestaoDTO> questoesDTO = questaoService.listarPorMaterias(codigoMateria);
        return ResponseEntity.ok(questoesDTO);
    }

    /**
     * Retorna um QuestaoDTO por ID.
     *
     * @param id Identificador da Questao
     * @return ResponseEntity<QuestaoDTO>
     */
    @GetMapping("/{id}")
    public ResponseEntity<QuestaoDTO> buscarPorId(@PathVariable Long id) {
        QuestaoDTO questaoDTO = questaoService.buscarPorId(id);
        return ResponseEntity.ok(questaoDTO);
    }

    /**
     * Cria uma nova Questao.
     *
     * @param questaoDTO Dados da nova Questao
     * @return ResponseEntity<QuestaoDTO>
     */
    @PostMapping
    public ResponseEntity<QuestaoDTO> criarQuestao(@Valid @RequestBody QuestaoDTO questaoDTO) {
        QuestaoDTO novaQuestaoDTO = questaoService.criarQuestao(questaoDTO);
        return new ResponseEntity<>(novaQuestaoDTO, HttpStatus.CREATED);
    }

    /**
     * Atualiza uma Questao existente.
     *
     * @param id         Identificador da Questao a ser atualizada
     * @param questaoDTO Dados atualizados da Questao
     * @return ResponseEntity<QuestaoDTO>
     */
    @PutMapping("/{id}")
    public ResponseEntity<QuestaoDTO> atualizarQuestao(@PathVariable Long id, @Valid @RequestBody QuestaoDTO questaoDTO) {
        QuestaoDTO questaoAtualizadaDTO = questaoService.atualizarQuestao(id, questaoDTO);
        return ResponseEntity.ok(questaoAtualizadaDTO);
    }

    /**
     * Deleta uma Questao por ID.
     *
     * @param id Identificador da Questao a ser deletada
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarQuestao(@PathVariable Long id) {
        questaoService.deletarQuestao(id);
        return ResponseEntity.noContent().build();
    }
}