package br.com.adega.adegaApp.controllers.dto;

import br.com.adega.adegaApp.entities.Funcionario;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class FuncionarioDto {

    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;

    public FuncionarioDto (Funcionario funcionario){
        this.cpf = funcionario.getCpf();
        this.nome = funcionario.getNome();
        this.dataNascimento = funcionario.getDataNascimento();
        this.telefone = funcionario.getTelefone();
        this.email = funcionario.getEmail();
    }

    public static List<FuncionarioDto> converter(List<Funcionario> funcionarios){
        return funcionarios.stream().map(FuncionarioDto::new).collect(Collectors.toList());
    }
}
