package br.com.adega.adegaApp.controllers.form;

import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.entities.Perfil;
import br.com.adega.adegaApp.exceptions.PerfilNaoEncontradoException;
import br.com.adega.adegaApp.services.PerfilService;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter @Setter
public class FuncionarioForm {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String senha;
    private String perfil;

    public Funcionario converter(PerfilService perfilService) throws PerfilNaoEncontradoException {
        Optional<Perfil> perfil = perfilService.buscarPorNomePerfil(this.perfil);
        if(perfil.isPresent()){
            List<Perfil> perfis = Arrays.asList(perfil.get());
            return new Funcionario(this.cpf, this.nome, this.dataNascimento, this.telefone, this.email, this.senha, perfis);
        }
        throw new PerfilNaoEncontradoException("Perfil não ecnontrado!");
    }
}
