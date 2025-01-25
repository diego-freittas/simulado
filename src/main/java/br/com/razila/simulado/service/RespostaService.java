package br.com.razila.simulado.service;

import br.com.razila.simulado.controller.exception.ResourceNotFoundException;
import br.com.razila.simulado.model.Resposta;
import br.com.razila.simulado.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    private @Autowired RespostaRepository respostaRepository;

    public List<Resposta> listarTodas() {
        return respostaRepository.findAll();
    }

    public Resposta buscarPorId(Long id) {
        return respostaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resposta não encontrada com id " + id));
    }

    public Resposta criarResposta(Resposta resposta) {
        return respostaRepository.save(resposta);
    }

    public Resposta atualizarResposta(Long id, Resposta respostaDetails) {
        Resposta resposta = buscarPorId(id);
        resposta.setResposta(respostaDetails.getResposta());
        resposta.setCorreta(respostaDetails.getCorreta());
        resposta.setQuestao(respostaDetails.getQuestao());
        return respostaRepository.save(resposta);
    }

    public void deletarResposta(Long id) {
        Resposta resposta = buscarPorId(id);
        respostaRepository.delete(resposta);
    }
}
