package br.com.adega.adegaApp.services;

import br.com.adega.adegaApp.entities.Perfil;
import br.com.adega.adegaApp.repositories.IPerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PerfilService {

    @Autowired
    private IPerfilRepository perfilRepository;

    public Perfil salvar(Perfil perfil){
        return perfilRepository.save(perfil);
    }

    public Optional<Perfil> buscarPorNomePerfil(String perfil){
        return perfilRepository.findByNome(perfil);
    }
}
