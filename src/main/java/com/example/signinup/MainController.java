package com.example.signinup;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MainService mainService;


    @GetMapping("/")
    public ModelAndView main(Model model, Principal principal) {
        return mainService.main(model,principal);
    }

    @DeleteMapping("/api/users/{email}")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> deleteUser(@PathVariable String email, Principal principal) {
        System.out.println("delete user: "+email);
        if(Objects.equals(email, principal.getName())){
            System.out.println("you can't delete yourself... failed to delete user: "+principal.getName());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you can't delete yourself");
        }

        try {
            UserEntity entity = userRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            entity.setUse(false);
            System.out.println("Userentity status : "+entity);
            userRepository.save(entity);

            System.out.println("try delete user: "+email);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("failed to delete user: "+email);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user");
        }
    }
}
