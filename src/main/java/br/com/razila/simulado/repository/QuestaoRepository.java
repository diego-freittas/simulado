package br.com.razila.simulado.repository;

import br.com.razila.simulado.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}