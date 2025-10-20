package ec.com.denunciasecuador.feature.security.service;

import ec.com.denunciasecuador.feature.usuario.model.Credencial;
import ec.com.denunciasecuador.feature.usuario.service.CredencialServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CredencialServiceImpl credencialService;

    public CustomUserDetailsService(CredencialServiceImpl credencialService) {
        this.credencialService = credencialService;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        Credencial credencial = credencialService.buscarCredencialPorUsername(
            username
        );
        return User.builder()
            .username(credencial.getUsername())
            .password(credencial.getPassword())
            .roles("USUARIO")
            .build();
    }
}
