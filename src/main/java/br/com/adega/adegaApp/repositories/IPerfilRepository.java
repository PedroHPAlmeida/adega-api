package br.com.adega.adegaApp.repositories;

import br.com.adega.adegaApp.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPerfilRepository extends JpaRepository<Perfil, Long> {
    Optional<Perfil> findByNome(String perfil);
}
