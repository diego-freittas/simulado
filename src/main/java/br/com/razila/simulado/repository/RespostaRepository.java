package br.com.razila.simulado.repository;

import br.com.razila.simulado.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    // Métodos de consulta personalizados podem ser adicionados aqui
}
