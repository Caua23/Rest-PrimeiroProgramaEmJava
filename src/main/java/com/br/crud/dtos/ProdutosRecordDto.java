package com.br.crud.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutosRecordDto(@NotBlank String name,@NotNull BigDecimal value) {


}
