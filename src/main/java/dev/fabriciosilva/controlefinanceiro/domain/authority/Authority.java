package dev.fabriciosilva.controlefinanceiro.domain.authority;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity(name = "Authority")
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
