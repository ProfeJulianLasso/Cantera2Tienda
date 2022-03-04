package com.sofka.dao;

import com.sofka.domain.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {

    @Modifying
    @Query("update Usuario usu set usu.nombre = :nombre where usu.id = :id")
    public void updateNombre(
            @Param(value = "id") Long id,
            @Param(value = "nombre") String nombre
    );

    @Modifying
    @Query("update Usuario usu set usu.tipoDocumento = :tipoDocumento where usu.id = :id")
    public void updateTipoDocumento(
            @Param(value = "id") Long id,
            @Param(value = "tipoDocumento") String tipoDocumento
    );

    @Modifying
    @Query("update Usuario usu set usu.documento = :documento where usu.id = :id")
    public void updateDocumento(
            @Param(value = "id") Long id,
            @Param(value = "documento") String documento
    );

}
