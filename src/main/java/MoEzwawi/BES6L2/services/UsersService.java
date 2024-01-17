package MoEzwawi.BES6L2.services;

import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.exceptions.BadRequestException;
import MoEzwawi.BES6L2.exceptions.NotFoundException;
import MoEzwawi.BES6L2.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    private boolean isTheEmailAlreadyInUse(String email){
        return this.usersRepository.existsByEmail(email);
    }
    public Page<User> getUsers(int pageNumber, int size, String orderBy) {
        Pageable pageable = PageRequest.of(pageNumber,size, Sort.by(orderBy));
        return this.usersRepository.findAll(pageable);
    }
    public User findById(UUID id){
        return this.usersRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public User save(User body){
        if(this.isTheEmailAlreadyInUse(body.getEmail())){
            throw new BadRequestException("The e-mail address "+body.getEmail()+" is already in use");
        }
        System.out.println("--------------SAVE------------");
        body.setAvatarUrl("https://ui-avatars.com/api/?name="+body.getName()+"+"+body.getSurname());
        this.usersRepository.save(body);
        System.out.println(body);
        return body;
    }
    public User findByIdAndUpdate(UUID id, User body){
        if(this.isTheEmailAlreadyInUse(body.getEmail())){
            throw new BadRequestException("The e-mail address "+body.getEmail()+" is already in use");
        }
        User found = this.findById(id);
        found.setName(body.getName());
        found.setSurname(body.getSurname());
        found.setEmail(body.getEmail());
        found.setAvatarUrl(body.getAvatarUrl());
        return found;
    }
    public User patchUser(UUID id, User partialBody){
        if(this.isTheEmailAlreadyInUse(partialBody.getEmail())){
            throw new BadRequestException("The e-mail address "+partialBody.getEmail()+" is already in use");
        }
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
