package br.com.adega.adegaApp.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "perfis")
public class Perfil implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long idPerfil;
    private String nomePerfil;

    @Override
    public String getAuthority() {
        return this.nomePerfil;
    }
}