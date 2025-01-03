package dev.fabriciosilva.controlefinanceiro.domain.user;

import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserCreationDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserLoginDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserResponseDto;
import dev.fabriciosilva.controlefinanceiro.domain.user.dto.UserTokenDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreationDto userCreationDTO,
                                                      UriComponentsBuilder uriBuilder) {

        UserResponseDto userResponseDto = (UserResponseDto) userService.create(userCreationDTO);
        var uri = uriBuilder.path("/user/{id}")
                .buildAndExpand(userResponseDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userResponseDto);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserTokenDto> login(@RequestBody @Valid UserLoginDto userLoginDTO) {
        return ResponseEntity.ok(userService.login(userLoginDTO));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

}
