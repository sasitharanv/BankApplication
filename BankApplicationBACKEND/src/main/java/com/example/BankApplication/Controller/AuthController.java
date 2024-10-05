package com.example.BankApplication.Controller;

        import com.example.BankApplication.Dto.*;

        import com.example.BankApplication.Jwt.JwtTokenProvider;
        import com.example.BankApplication.Service.AuthService;
        import lombok.AllArgsConstructor;
        import org.springframework.http.HttpStatus;
        import org.springframework.http.ResponseEntity;
        import org.springframework.security.access.prepost.PreAuthorize;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;
    private JwtTokenProvider jwtTokenProvider;
    //Build Register new User Api

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    //Build login api
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto) {
        JwtAuthResponse jwtAuthResponse = authService.login(loginDto);


        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/greeting")
    public String greeting() {

        return "Hello, this is a simple greeting from the server.";
    }

    @GetMapping("/user")
    public ResponseEntity<UserDetailsDto> getUserDetails() {

        UserDetailsDto userDetailsDto = authService.getCurrentUserDetails();
        return ResponseEntity.ok(userDetailsDto);

    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping ("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        try{
            authService.changePassword(passwordChangeDto);
            return ResponseEntity.ok().body("Password changed Succesfully");
        }catch(Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        // Extract the token from the Authorization header
        String jwtToken = token.substring(7); // Remove "Bearer " prefix

        // Logout: Add the token to the blacklist
        jwtTokenProvider.revokeToken(jwtToken);

        return new ResponseEntity<>("Logout successful", HttpStatus.OK);
    }

}
