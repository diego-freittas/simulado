package br.com.razila.simulado.config;

import br.com.razila.simulado.controller.dto.QuestaoDTO;
import br.com.razila.simulado.controller.dto.RespostaDTO;
import br.com.razila.simulado.model.Questao;
import br.com.razila.simulado.model.Resposta;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class ModelMapperConfig {

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();

            // Condição para ignorar campos nulos durante a cópia
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

            // Mapeamento de Questao para QuestaoDTO
            TypeMap<Questao, QuestaoDTO> questaoToDtoTypeMap = modelMapper.createTypeMap(Questao.class, QuestaoDTO.class);
            questaoToDtoTypeMap.addMappings(mapper -> {
                mapper.map(src -> src.getMateria().getNomeMateria(), QuestaoDTO::setMateria);
                mapper.skip(QuestaoDTO::setRespostas); // As respostas serão mapeadas automaticamente
            });

            // Mapeamento de Resposta para RespostaDTO
            TypeMap<Resposta, RespostaDTO> respostaToDtoTypeMap = modelMapper.createTypeMap(Resposta.class, RespostaDTO.class);

            // Mapeamento de QuestaoDTO para Questao
            TypeMap<QuestaoDTO, Questao> dtoToQuestaoTypeMap = modelMapper.createTypeMap(QuestaoDTO.class, Questao.class);
            dtoToQuestaoTypeMap.addMappings(mapper -> {
                mapper.skip(Questao::setMateria); // A matéria será setada manualmente no serviço
                mapper.skip(Questao::setRespostas); // As respostas serão setadas manualmente no serviço
            });

            return modelMapper;
        }
    }