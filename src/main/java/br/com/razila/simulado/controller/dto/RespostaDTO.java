package br.com.razila.simulado.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespostaDTO {

    private Long id;

    @NotBlank(message = "A resposta é obrigatória")
    private String resposta;

    private boolean correta;
}