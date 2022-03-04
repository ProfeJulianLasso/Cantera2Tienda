package com.sofka.service;

import com.sofka.dao.UsuarioDao;
import com.sofka.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired // para inyectar UsuarioDao
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> list() {
        List<Usuario> usuarios = null;
        try {
            usuarios = (List<Usuario>) usuarioDao.findAll();
        } catch (Exception exc) {
            throw exc;
        }
        return usuarios;
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioDao.save(usuario);
    }

    @Transactional
    public void updateNombre(Long id, Usuario usuario) {
        usuarioDao.updateNombre(id, usuario.getNombre());
    }

    @Transactional
    public void updateTipoDocumento(Long id, Usuario usuario) {
        usuarioDao.updateTipoDocumento(id, usuario.getTipoDocumento());
    }

    @Transactional
    public void updateDocumento(Long id, Usuario usuario) {
        usuarioDao.updateDocumento(id, usuario.getDocumento());
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findUser(Usuario usuario) {
        return usuarioDao.findById(usuario.getId());
    }
}
