package com.jb.supermercado.config.exception.response;

import java.time.LocalDateTime;

public record ErroPadraoResponse(
        String mensagem,
        Integer status,
        LocalDateTime timestamp
) {
}

