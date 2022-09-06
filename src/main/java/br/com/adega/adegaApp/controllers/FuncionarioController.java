package br.com.adega.adegaApp.controllers;

import br.com.adega.adegaApp.controllers.dto.FuncionarioDto;
import br.com.adega.adegaApp.controllers.form.FuncionarioForm;
import br.com.adega.adegaApp.entities.Funcionario;
import br.com.adega.adegaApp.exceptions.PerfilNaoEncontradoException;
import br.com.adega.adegaApp.services.FuncionarioService;
import br.com.adega.adegaApp.services.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PerfilService perfilService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FuncionarioDto> salvar(@RequestBody FuncionarioForm funcionarioForm, UriComponentsBuilder uriBuilder){
        try {
            Funcionario funcionario = funcionarioForm.converter(perfilService);
            funcionarioService.salvar(funcionario);
            URI uri = uriBuilder.path("/api/funcionarios/{id}").buildAndExpand(funcionario.getIdFuncionario()).toUri();
            return ResponseEntity
                    .created(uri)
                    .body(new FuncionarioDto(funcionario));
        } catch (PerfilNaoEncontradoException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FuncionarioDto> listarFuncionarios(){
        return FuncionarioDto.converter(funcionarioService.listarFuncionarios());
    }

    @GetMapping(path = "/{nome}")
    public ResponseEntity<FuncionarioDto> buscarPorNome(@PathVariable String nome){
        Optional<Funcionario> funcionario = funcionarioService.buscarPorNome(nome);
        if(funcionario.isPresent()){
            FuncionarioDto funcionarioDto = new FuncionarioDto(funcionario.get());
            return ResponseEntity.ok(funcionarioDto);
        }
        return ResponseEntity.notFound().build();
    }

}
