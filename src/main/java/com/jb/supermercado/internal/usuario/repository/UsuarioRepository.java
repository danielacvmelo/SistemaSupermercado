package com.jb.supermercado.internal.usuario.repository;

import com.jb.supermercado.internal.usuario.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Boolean existsByEmail(String email);
}

