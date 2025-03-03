package dev.fabriciosilva.controlefinanceiro.domain.passwordreset;

import dev.fabriciosilva.controlefinanceiro.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "passwordresettoken")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    @JoinColumn(nullable = false)
    private LocalDateTime expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(User user, String token, int expirationTime) {
        this.user = user;
        this.token = token;
        this.expiryDate = LocalDateTime.now().plusMinutes(expirationTime);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiryDate);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
