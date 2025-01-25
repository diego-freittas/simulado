package br.com.razila.simulado.service;

import br.com.razila.simulado.config.ModelMapperConfig;
import br.com.razila.simulado.controller.dto.MateriaDTO;
import br.com.razila.simulado.controller.exception.ResourceNotFoundException;
import br.com.razila.simulado.model.Materia;
import br.com.razila.simulado.repository.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MateriaService {

    private @Autowired MateriaRepository materiaRepository;
    private @Autowired ModelMapper modelMapper;

    public List<MateriaDTO> listarTodas() {
        List<Materia> materias = materiaRepository.findAll();
        return materias.stream()
                .map(materia -> modelMapper.map(materia, MateriaDTO.class))
                .collect(Collectors.toList());
    }

    public MateriaDTO buscarPorId(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id " + id));
        return modelMapper.map(materia, MateriaDTO.class);
    }

    public MateriaDTO criarMateria(MateriaDTO materiaDTO) {
        Materia materia = modelMapper.map(materiaDTO, Materia.class);
        Materia novaMateria = materiaRepository.save(materia);
        return modelMapper.map(novaMateria, MateriaDTO.class);
    }

    public MateriaDTO atualizarMateria(Long id, MateriaDTO materiaDTO) {
        Materia materiaExistente = materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id " + id));

        materiaExistente.setNomeMateria(materiaDTO.getNomeMateria());
        // Adicione outros campos se houver

        Materia materiaAtualizada = materiaRepository.save(materiaExistente);
        return modelMapper.map(materiaAtualizada, MateriaDTO.class);
    }

    public void deletarMateria(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Matéria não encontrada com id " + id));
        materiaRepository.delete(materia);
    }
}

