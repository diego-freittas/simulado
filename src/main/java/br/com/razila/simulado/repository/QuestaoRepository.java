package br.com.razila.simulado.repository;

import br.com.razila.simulado.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestaoRepository extends JpaRepository<Questao, Long> {

    @Query("""
            SELECT q FROM Questao q
            WHERE q.materia.id = :codigoMateria
            """)
    List<Questao> findQuestaosByMateria(Long codigoMateria);
    // Métodos de consulta personalizados podem ser adicionados aqui
}