package com.agendatarefa.agendador.infrastructure.security;


import com.agendatarefa.agendador.dto.UsuarioDTO;
import com.agendatarefa.agendador.infrastructure.security.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    // Implementação própria do método para carregar detalhes do usuário pelo e-mail

    public UserDetails loadUserByEmailAndToken(String email, String token) {
        UsuarioDTO usuario = client.buscarUsuarioPorEmail(email, token);
        return User
                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuario.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
