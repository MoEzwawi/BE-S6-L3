package MoEzwawi.BES6L2.services;

import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsersService {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return this.users;
    }
    public User findById(int id){
        User found = null;
        for (User user: users){
            if(user.getId()==id){
                found = user;
            }
        }
        if(found==null) throw new NotFoundException();
        return found;
    }
    public User save(User body){
        System.out.println("--------------SAVE------------");
        System.out.println(body);
        Random rndm = new Random();
        body.setId(rndm.nextInt(100,999));
        body.setAvatarUrl("https://ui-avatars.com/api/?name="+body.getName()+"+"+body.getSurname());
        this.users.add(body);
        return body;
    }
    public User findByIdAndUpdate(int id, User body){
        User found = this.findById(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setDateOfBirth(body.getDateOfBirth());
        return found;
    }
    public User patchUser(int id, User partialBody){
        User found = this.findById(id);
        if(partialBody.getName()!=null) found.setName(partialBody.getName());
        if(partialBody.getSurname()!=null) found.setSurname(partialBody.getSurname());
        if(partialBody.getEmail()!=null) found.setEmail(partialBody.getEmail());
        if(partialBody.getDateOfBirth()!=null) found.setDateOfBirth(partialBody.getDateOfBirth());
        if(partialBody.getAvatarUrl()!=null) found.setAvatarUrl(partialBody.getAvatarUrl());
        return found;
    }
    public void findByIdAndDelete(int id){
        this.users.removeIf(current -> current.getId() == id);
    }
}
