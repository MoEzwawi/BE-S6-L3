package MoEzwawi.BES6L2.services;

import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.exceptions.NotFoundException;
import MoEzwawi.BES6L2.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    public List<User> getUsers() {
        return this.usersRepository.findAll();
    }
    public User findById(UUID id){
        return this.usersRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    public User save(User body){
        System.out.println("--------------SAVE------------");
        System.out.println(body);
        body.setAvatarUrl("https://ui-avatars.com/api/?name="+body.getName()+"+"+body.getSurname());
        this.usersRepository.save(body);
        return body;
    }
    public User findByIdAndUpdate(UUID id, User body){
        User found = this.findById(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setAvatarUrl(body.getAvatarUrl());
        return found;
    }
    public User patchUser(UUID id, User partialBody){
        User found = this.findById(id);
        if(partialBody.getName()!=null) found.setName(partialBody.getName());
        if(partialBody.getSurname()!=null) found.setSurname(partialBody.getSurname());
        if(partialBody.getEmail()!=null) found.setEmail(partialBody.getEmail());
        if(partialBody.getAvatarUrl()!=null) found.setAvatarUrl(partialBody.getAvatarUrl());
        return found;
    }
    public void findByIdAndDelete(UUID id){
        User found = this.findById(id);
        this.usersRepository.delete(found);
    }
}
