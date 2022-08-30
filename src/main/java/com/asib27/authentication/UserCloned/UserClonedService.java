package com.asib27.authentication.UserCloned;

import com.asib27.authentication.payload.request.AboutYouRequest;
import com.asib27.authentication.payload.request.UserPersonalInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserClonedService {

    @Autowired
    UserClonedRepository userClonedRepository;


    public UserCloned addNewUser(UserCloned user){
        return userClonedRepository.save(user);
    }

    public List<UserCloned> getAllUsers() {
        return userClonedRepository.findAll();
    }

    public UserCloned getAnUser(Long userid) {
        boolean exists = userClonedRepository.existsById(userid);
        if(!exists){
            throw new IllegalStateException("no user found");
        }else{
            return userClonedRepository.findById(userid).get();
        }

    }

    public String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public UserCloned getCurrentUser(){
        String name = getUserName();
        Long id = userClonedRepository.findUserId(name);
        System.out.println("User id is : "+ id + name);
        return getAnUser(id);
    }

    public void addLink(String username, String link) {
        Long id = userClonedRepository.findUserId(username);
        UserCloned user = userClonedRepository.getReferenceById(id);
        user.setLink(link);
        userClonedRepository.save(user);
    }

    public String getLink(String username) {
        return userClonedRepository.getReferenceById(userClonedRepository.findUserId(username)).getLink();
    }

    public void addPersonalInfo(UserCloned user, UserPersonalInfoRequest userPersonalInfo) {
        if(userPersonalInfo.getFirst_name().length() > 0)
            user.setFirst_name(userPersonalInfo.getFirst_name());
        if(userPersonalInfo.getMiddle_name().length() > 0)
            user.setMiddle_name(userPersonalInfo.getMiddle_name());
        if(userPersonalInfo.getLast_name().length() > 0)
            user.setLast_name(userPersonalInfo.getLast_name());
        if(userPersonalInfo.getPhone_number().length() == 11)
            user.setPhone_number(userPersonalInfo.getPhone_number());
        if(userPersonalInfo.getBackup_phone_number().length() == 11)
            user.setBackup_phone_number(userPersonalInfo.getBackup_phone_number());

        userClonedRepository.save(user);


    }

    public void addAboutInfo(AboutYouRequest aboutYou, UserCloned user) {
        user.setDescription(aboutYou.getDescription());
        user.setFav_books(aboutYou.getFav_books());
        user.setFav_genre(aboutYou.getFav_genre());

        userClonedRepository.save(user);
    }
}
