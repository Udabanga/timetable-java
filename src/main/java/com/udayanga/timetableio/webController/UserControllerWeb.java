package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.ERole;
import com.udayanga.timetableio.model.Role;
import com.udayanga.timetableio.model.User;
import com.udayanga.timetableio.repository.RoleRepository;
import com.udayanga.timetableio.repository.UserRepository;
import com.udayanga.timetableio.payload.request.SignupRequest;
import com.udayanga.timetableio.payload.response.MessageResponse;
import com.udayanga.timetableio.security.AuthenticatedUser;
import com.udayanga.timetableio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class UserControllerWeb {
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    @GetMapping("/admin/lecturer/add")
    public String adminUserAdd(Model model) {
        // create model attribute to bind form data
//        User user = new User();
        SignupRequest user = new SignupRequest();
        model.addAttribute("user", user);

        model.addAttribute("allRoles", roleRepository.findAll());
        return "lecturerListAdd";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") SignupRequest signUpRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return "lecturerListAdd";
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return "redirect:/admin/lecturer/add?emailError";
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userService.saveUser(user);



        return "redirect:/admin/lecturer";
    }

    @PostMapping("/updateUser")
    public String updateUser(@Valid @ModelAttribute("user") SignupRequest signUpRequest, BindingResult bindingResult, Model model, Authentication authResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allRoles", roleRepository.findAll());
            return "lecturerListUpdate";
        }

        Optional<User> userData = userRepository.findById(signUpRequest.getId());
        User user;
        if(signUpRequest.getPassword().isEmpty()) {
            User _user = userData.get();
            user = new User(
                    signUpRequest.getId(),
                    signUpRequest.getName(),
                    signUpRequest.getEmail(),
                    _user.getPassword());

        }
        else{
            user = new User(
                    signUpRequest.getId(),
                    signUpRequest.getName(),
                    signUpRequest.getEmail(),
                    encoder.encode(signUpRequest.getPassword()));
        }

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

        String role =  authResult.getAuthorities().toString();
        User userLecturer = ((User) ((AuthenticatedUser) authResult.getPrincipal()).getUser());

        if(role.contains("ROLE_ADMIN")){
            return "redirect:/admin/lecturer";
        }
        else if(role.contains("ROLE_LECTURER")) {
            model.addAttribute("user", userLecturer);
            return "redirect:/lecturer";
        }
        else{
            return "redirect:/login";
        }


    }

    @GetMapping("/admin/lecturer/update/{id}")
    public String adminUserUpdate(@PathVariable( value = "id") int id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        Set<String> roles = new HashSet<>();


        for (Role role : user.getRoles())
        {
            System.out.println(role.getName().name());
            switch (role.getName().name()) {
                case "ROLE_ADMIN":
                    roles.add("ROLE_ADMIN");

                    break;
                case "ROLE_LECTURER":
                    roles.add("ROLE_LECTURER");
                    break;
                default:
                    roles.add("ROLE_USER");
            }
        }

        SignupRequest user1 = new SignupRequest(id, user.getName(), user.getEmail(), roles);
        model.addAttribute("user", user1);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "lecturerListUpdate";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value = "id") int id) {

        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/admin/lecturer";
    }
}
