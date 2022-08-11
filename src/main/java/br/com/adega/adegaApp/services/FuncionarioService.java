package br.com.adega.adegaApp.services;

import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.repositories.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    public Funcionario salvar(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorNome(String nome){
        return funcionarioRepository.getByNome(nome);
    }

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }
}
