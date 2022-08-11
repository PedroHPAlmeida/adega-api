package br.com.adega.adegaApp.entities;

import br.com.adega.adegaApp.entities.enums.NivelAcesso;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long idFuncionario;
    private String cpf;
    private String nome;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso")
    private NivelAcesso nivelAcesso;

}