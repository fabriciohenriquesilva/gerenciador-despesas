package dev.fabriciosilva.controlefinanceiro.domain.user;

import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.PasswordResetTokenService;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.ResetPasswordRequest;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.PasswordResetTokenRequest;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.PasswordResetTokenResponse;
import dev.fabriciosilva.controlefinanceiro.domain.passwordreset.dto.ResetPasswordResponse;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreateRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserLoginRequest;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDetail;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserTokenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserRest {

    private final UserService userService;

    public UserRest(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<UserResponseDetail> createUser(@RequestPart("form") @Valid UserCreateRequest userCreateRequest,
                                                         @RequestPart(value = "foto", required = false) MultipartFile foto,
                                                         UriComponentsBuilder uriBuilder) throws IOException {

        UserResponseDetail userResponseDetail = (UserResponseDetail) userService.create(userCreateRequest, foto);
        var uri = uriBuilder.path("/user/{id}")
                .buildAndExpand(userResponseDetail.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userResponseDetail);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return ResponseEntity.ok(userService.login(userLoginRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDetail> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDetail>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> getUserProfilePic(@PathVariable Integer id) {
        byte[] profilePic = userService.getProfilePic(id);

        // TODO ver depois como tratar outros tipos de arquivos
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Assumindo que seja JPEG
                .body(profilePic);
    }

    @PostMapping(value = "/forgotPassword")
    public ResponseEntity<PasswordResetTokenResponse> forgotPassword(@RequestBody @Valid PasswordResetTokenRequest form) {
        userService.generatePasswordResetToken(form);
        PasswordResetTokenResponse response = new PasswordResetTokenResponse("Se este e-mail estiver cadastrado, um link de recuperação foi enviado.");
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/resetPassword")
    public ResponseEntity<ResetPasswordResponse> resetPassword(@RequestBody @Valid ResetPasswordRequest form) {
        userService.resetPassword(form);
        ResetPasswordResponse response = new ResetPasswordResponse("Senha alterada com sucesso!");
        return ResponseEntity.ok(response);
    }

}
