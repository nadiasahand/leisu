package com.mycompany.api.controllers;

import com.mycompany.database.models.LeisuUser;
import com.mycompany.database.models.criteria.UserCriteria;
import com.mycompany.database.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*@RestController
@RequestMapping("user")
public class UserController extends AbstractController<LeisuUser, UserCriteria, UserRepository, UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @PostMapping
    public LeisuUser create(@RequestBody UserCreateRequest request) {
        return super.service.save(request);
    }
}*/
