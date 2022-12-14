package br.com.adega.adegaApp.services;

import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.repositories.IFuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private IFuncionarioRepository funcionarioRepository;

    @Autowired
    private PerfilService perfilService;

    public Funcionario salvar(Funcionario funcionario){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> buscarPorNome(String nome){
        return funcionarioRepository.getByNome(nome);
    }

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorEmail(String email){
        return funcionarioRepository.findByEmail(email);
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }
}
