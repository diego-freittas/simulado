package br.com.razila.simulado.repository;


import br.com.razila.simulado.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long> {
    Optional<Materia> findByNomeMateria(String materia);
    // Métodos de consulta personalizados podem ser adicionados aqui
}