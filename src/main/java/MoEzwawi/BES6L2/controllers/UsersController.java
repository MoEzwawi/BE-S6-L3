package MoEzwawi.BES6L2.controllers;

import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0")int pageNumber,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(defaultValue = "id") String orderBy){
        return usersService.getUsers(pageNumber, size, orderBy);
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable UUID id){
        return usersService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status code 201
    public User save(@RequestBody User body){
        return usersService.save(body);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable UUID id,@RequestBody User body){
        return usersService.findByIdAndUpdate(id, body);
    }
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable UUID id, @RequestBody User partialBody){
        return this.usersService.patchUser(id,partialBody);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // status code 204
    public void findByIdAndDelete(@PathVariable UUID id){
        usersService.findByIdAndDelete(id);
    }
}
