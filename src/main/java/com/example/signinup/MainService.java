package com.example.signinup;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MainService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ModelAndView main(Model model, Principal principal){
        ModelAndView mav = new ModelAndView();
        String email = principal.getName();
        mav.addObject("items",userRepository.findAll());
        mav.addObject("email", email);
        mav.setViewName("main");
        return mav;
    }

    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    public ResponseEntity<String> deleteUser(String email, Principal principal){
        System.out.println("delete user: "+email);
        if(Objects.equals(email, principal.getName())){
            System.out.println("you can't delete yourself... failed to delete user: "+principal.getName());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you can't delete yourself");
        }
        try {
            UserEntity entity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found"));
            entity.setUse(false); //mariaDB상에서 isUse 열이 0 에서 1 로 저장.
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
