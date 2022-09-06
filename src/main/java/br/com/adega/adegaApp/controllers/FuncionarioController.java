package br.com.adega.adegaApp.controllers;

import br.com.adega.adegaApp.controllers.form.FuncionarioForm;
import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.execeptions.PerfilNaoEncontradoException;
import br.com.adega.adegaApp.services.FuncionarioService;
import br.com.adega.adegaApp.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvar(@RequestBody FuncionarioForm funcionarioForm){
        try {
            Funcionario funcionario = funcionarioForm.converter(perfilService);
            return funcionarioService.salvar(funcionario);
        } catch (PerfilNaoEncontradoException ex){
            return new Funcionario();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping(path = "/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public Funcionario buscarPorNome(@PathVariable String nome){
        return funcionarioService.buscarPorNome(nome)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionário não encontrado"));
    }

}
