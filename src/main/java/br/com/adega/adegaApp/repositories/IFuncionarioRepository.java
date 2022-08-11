package br.com.adega.adegaApp.repositories;

import br.com.adega.adegaApp.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> getByNome(String nome);
}
