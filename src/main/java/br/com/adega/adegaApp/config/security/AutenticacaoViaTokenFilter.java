package br.com.adega.adegaApp.config.security;

import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.services.FuncionarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private FuncionarioService funcionarioService;

    public AutenticacaoViaTokenFilter(TokenService tokenService, FuncionarioService funcionarioService) {
        this.tokenService = tokenService;
        this.funcionarioService = funcionarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recuperarToken(request);
        boolean valido = tokenService.isTokenValido(token);
        if(valido){
            autenticarFuncionario(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticarFuncionario(String token) {
        Long idFuncionario = tokenService.getIdFuncionario(token);
        Funcionario funcionario = funcionarioService.buscarPorId(idFuncionario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(funcionario, null, funcionario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7, token.length());
    }
}
