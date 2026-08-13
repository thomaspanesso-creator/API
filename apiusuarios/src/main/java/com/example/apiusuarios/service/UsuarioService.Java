package com.example.apiusuarios.service;

import com.example.apiusuarios.model.Usuario;
import com.example.apiusuarios.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() { 
        return repo.findAll(); 
    }

    public Usuario obtener(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario crear(Usuario u) {
        repo.findByEmail(u.getEmail()).ifPresent(x -> {
            throw new RuntimeException("Email ya registrado");
        });
        return repo.save(u);
    }

    public Usuario actualizar(Long id, Usuario datos) {
        Usuario u = obtener(id);
        u.setNombre(datos.getNombre());
        u.setEmail(datos.getEmail());
        u.setEdad(datos.getEdad());
        return repo.save(u);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
