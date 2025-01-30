package br.com.razila.simulado.controller.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDTO {

    private Long id;

    @NotBlank(message = "O nome da matéria é obrigatório")
    @Size(max = 200, message = "O nome da matéria deve ter no máximo 200 caracteres")
    private String nomeMateria;

    public @NotBlank(message = "O nome da matéria é obrigatório") @Size(max = 200, message = "O nome da matéria deve ter no máximo 200 caracteres") String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(@NotBlank(message = "O nome da matéria é obrigatório") @Size(max = 200, message = "O nome da matéria deve ter no máximo 200 caracteres") String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }


}
