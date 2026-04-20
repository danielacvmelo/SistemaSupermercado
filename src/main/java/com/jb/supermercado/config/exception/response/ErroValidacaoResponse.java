package com.jb.supermercado.config.exception.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErroValidacaoResponse(
        String mensagem,
        Integer status,
        LocalDateTime timestamp,
        List<ErroDetalhe> erros
) {
    public record ErroDetalhe(String campo, String mensagem) {
    }
}

