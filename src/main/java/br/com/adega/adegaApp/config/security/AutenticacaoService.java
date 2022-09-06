package br.com.adega.adegaApp.config.security;

import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private FuncionarioService funcionarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail(username);
        if(funcionario.isPresent()){
            return funcionario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!");
    }
}
