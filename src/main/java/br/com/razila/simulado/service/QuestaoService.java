package br.com.razila.simulado.service;

import br.com.razila.simulado.controller.dto.QuestaoDTO;
import br.com.razila.simulado.controller.dto.RespostaDTO;
import br.com.razila.simulado.controller.exception.ResourceNotFoundException;
import br.com.razila.simulado.model.Questao;
import br.com.razila.simulado.model.Resposta;
import br.com.razila.simulado.repository.QuestaoRepository;
import br.com.razila.simulado.repository.MateriaRepository;
import br.com.razila.simulado.repository.RespostaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestaoService {

    private final QuestaoRepository questaoRepository;
    private final MateriaRepository materiaRepository;
    private final RespostaRepository respostaRepository;
    private final ModelMapper modelMapper;

    /**
     * Retorna uma lista de QuestaoDTO convertida a partir das entidades Questao.
     *
     * @return List<QuestaoDTO>
     */
    public List<QuestaoDTO> listarTodas() {
        List<Questao> questoes = questaoRepository.findAll();
        return questoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma lista de QuestaoDTO convertida a partir das entidades Questao.
     *
     * @return List<QuestaoDTO>
     */
    public List<QuestaoDTO> listarPorMaterias(Long codigoMateria) {
        List<Questao> questoes = questaoRepository.findQuestaosByMateria(codigoMateria);
        return questoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Busca uma Questao por ID e retorna o QuestaoDTO correspondente.
     *
     * @param id Identificador da Questao
     * @return QuestaoDTO
     */
    public QuestaoDTO buscarPorId(Long id) {
        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questão não encontrada com id " + id));
        return convertToDTO(questao);
    }

    /**
     * Cria uma nova Questao a partir do QuestaoDTO recebido.
     *
     * @param questaoDTO Dados da nova Questao
     * @return QuestaoDTO da Questao criada
     */
    public QuestaoDTO criarQuestao(QuestaoDTO questaoDTO) {
        Questao questao = convertToEntity(questaoDTO);
        Questao novaQuestao = questaoRepository.save(questao);

        // Salva as respostas associadas
        if (questaoDTO.getRespostas() != null) {
            List<Resposta> respostas = questaoDTO.getRespostas().stream()
                    .map(respostaDTO -> {
                        Resposta resposta = modelMapper.map(respostaDTO, Resposta.class);
                        resposta.setQuestao(novaQuestao);
                        return resposta;
                    })
                    .collect(Collectors.toList());
            respostaRepository.saveAll(respostas);
            novaQuestao.setRespostas(new java.util.HashSet<>(respostas));
        }

        return convertToDTO(novaQuestao);
    }

    /**
     * Atualiza uma Questao existente com os dados fornecidos no QuestaoDTO.
     *
     * @param id         Identificador da Questao a ser atualizada
     * @param questaoDTO Dados atualizados da Questao
     * @return QuestaoDTO da Questao atualizada
     */
    public QuestaoDTO atualizarQuestao(Long id, QuestaoDTO questaoDTO) {
        Questao questaoExistente = questaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questão não encontrada com id " + id));

        // Atualiza os campos necessários
        questaoExistente.setPergunta(questaoDTO.getPergunta());

        // Atualiza a matéria, se fornecida
        if (questaoDTO.getMateria() != null) {
            questaoExistente.setMateria(materiaRepository.findByNomeMateria(questaoDTO.getMateria())
                    .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com nome " + questaoDTO.getMateria())));
        }

        // Atualiza as respostas, se fornecidas
        if (questaoDTO.getRespostas() != null) {
            // Remove respostas existentes
            respostaRepository.deleteAll(questaoExistente.getRespostas());

            // Adiciona novas respostas
            List<Resposta> novasRespostas = questaoDTO.getRespostas().stream()
                    .map(respostaDTO -> {
                        Resposta resposta = modelMapper.map(respostaDTO, Resposta.class);
                        resposta.setQuestao(questaoExistente);
                        return resposta;
                    })
                    .collect(Collectors.toList());
            respostaRepository.saveAll(novasRespostas);
            questaoExistente.setRespostas(new java.util.HashSet<>(novasRespostas));
        }

        Questao questaoAtualizada = questaoRepository.save(questaoExistente);
        return convertToDTO(questaoAtualizada);
    }

    /**
     * Deleta uma Questao por ID.
     *
     * @param id Identificador da Questao a ser deletada
     */
    public void deletarQuestao(Long id) {
        Questao questao = questaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Questão não encontrada com id " + id));
        questaoRepository.delete(questao);
    }

    /**
     * Converte uma entidade Questao para QuestaoDTO.
     *
     * @param questao Entidade Questao
     * @return QuestaoDTO
     */
    private QuestaoDTO convertToDTO(Questao questao) {
        QuestaoDTO questaoDTO = modelMapper.map(questao, QuestaoDTO.class);
        if (questao.getMateria() != null) {
            questaoDTO.setMateria(questao.getMateria().getNomeMateria());
        }

        if (questao.getRespostas() != null) {
            List<RespostaDTO> respostasDTO = questao.getRespostas().stream()
                    .map(resposta -> modelMapper.map(resposta, RespostaDTO.class))
                    .collect(Collectors.toList());
            questaoDTO.setRespostas(respostasDTO);
        }

        return questaoDTO;
    }

    /**
     * Converte um QuestaoDTO para entidade Questao.
     *
     * @param questaoDTO QuestaoDTO
     * @return Questao Entidade Questao
     */
    private Questao convertToEntity(QuestaoDTO questaoDTO) {
        Questao questao = modelMapper.map(questaoDTO, Questao.class);
        if (questaoDTO.getMateria() != null) {
            questao.setMateria(materiaRepository.findByNomeMateria(questaoDTO.getMateria())
                    .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com nome " + questaoDTO.getMateria())));
        }
        return questao;
    }
}