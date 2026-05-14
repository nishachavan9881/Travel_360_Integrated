package org.travel360.controller;

import org.travel360.dto.LoginRequestDTO;
import org.travel360.dto.LoginResponseDTO;
import org.travel360.service.IamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final IamService iamService;

    public AuthController(IamService iamService) {
        this.iamService = iamService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return iamService.login(loginRequestDTO);
    }
}
