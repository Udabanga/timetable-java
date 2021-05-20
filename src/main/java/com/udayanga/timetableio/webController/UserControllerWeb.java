package com.udayanga.timetableio.webController;

import com.udayanga.timetableio.model.ERole;
import com.udayanga.timetableio.model.Role;
import com.udayanga.timetableio.model.User;
import com.udayanga.timetableio.repository.RoleRepository;
import com.udayanga.timetableio.repository.UserRepository;
import com.udayanga.timetableio.payload.request.SignupRequest;
import com.udayanga.timetableio.payload.response.MessageResponse;
import com.udayanga.timetableio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
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
    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        // create model attribute to bind form data
//        User user = new User();
        SignupRequest user = new SignupRequest();
        model.addAttribute("user", user);

        model.addAttribute("allRoles", roleRepository.findAll());
        return "lecturerListAdd";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") SignupRequest signUpRequest) {
//        // save user to database
//        userService.saveUser(user);

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
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
                    case "ROLE_LECTURER":
                        Role modRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
//        userRepository.save(user);
        userService.saveUser(user);

//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

        return "redirect:/admin/lecturerList";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") SignupRequest signUpRequest) {
//        // save user to database
//        userService.saveUser(user);

//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
////            return ResponseEntity
////                    .badRequest()
////                    .body(new MessageResponse("Error: Email is already in use!"));
//        }

        // Create new user's account
        User user = new User(
                signUpRequest.getId(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
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
                    case "ROLE_LECTURER":
                        Role modRole = roleRepository.findByName(ERole.ROLE_LECTURER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

        return "redirect:/admin/lecturerList";
    }

    @GetMapping("/showFormForUpdateUser/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") int id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        Set<String> roles = new HashSet<>();


        for (Role role : user.getRoles())
        {
            System.out.println(role.getName().name());
//            if(role.getName().name() == "ROLE_LECTURER"){
////                roles
//                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//                roles.add(adminRole);
//            }

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

//        SignupRequest user1 = new SignupRequest(user.getEmail(), user.getName(), roles);
        SignupRequest user1 = new SignupRequest(id, user.getName(), user.getEmail(), roles);
        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user1);
        model.addAttribute("allRoles", roleRepository.findAll());
        return "lecturerListUpdate";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (value = "id") int id) {

        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/admin/lecturerList";
    }


//    @GetMapping("/page/{pageNo}")
//    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
//                                Model model) {
//        int pageSize = 5;
//
//        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
//        List<User> listUsers = page.getContent();
//
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//
//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//
//        model.addAttribute("listUsers", listUsers);
//        return "lecturerList";
//    }
}
