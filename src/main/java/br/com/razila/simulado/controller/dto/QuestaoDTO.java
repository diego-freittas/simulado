package br.com.razila.simulado.controller.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestaoDTO {

    private Long id;

    private String materia; // Nome da matéria

    @NotBlank(message = "A pergunta é obrigatória")
    @Size(max = 800, message = "A pergunta deve ter no máximo 800 caracteres")
    private String pergunta;

    private List<RespostaDTO> respostas; // Lista de respostas associadas
}
