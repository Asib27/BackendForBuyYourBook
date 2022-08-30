package com.asib27.authentication.UserCloned;

import com.asib27.authentication.Locations.Location;
import com.asib27.authentication.Locations.LocationService;
import com.asib27.authentication.payload.request.AboutYouRequest;
import com.asib27.authentication.payload.request.UserPersonalInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserClonedController {

    @Autowired
    UserClonedService userClonedService;

    @Autowired
    LocationService locationService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserCloned user){
        userClonedService.addNewUser(user);
        return "new user added";
    }
    
    @PostMapping("/add/personal")
    public String addPersonalInfo(@RequestBody UserPersonalInfoRequest userPersonalInfo)
    {
        UserCloned user = userClonedService.getCurrentUser();
        userClonedService.addPersonalInfo(user, userPersonalInfo);
        return "personal info added !!";
    }

    @GetMapping("/get/personal")
    public UserPersonalInfoRequest getPersonalInfo(){
        return userClonedService.getPersonalInfo(userClonedService.getCurrentUser().getId());
    }

    @GetMapping("/get/about")
    public AboutYouRequest getAbout(){
        return userClonedService.aboutRequest(userClonedService.getCurrentUser().getId());
    }
    @PostMapping("/add/about")
    public String addAboutInfo(@RequestBody AboutYouRequest aboutYou){
        UserCloned user = userClonedService.getCurrentUser();
        userClonedService.addAboutInfo(aboutYou, user);
        return "about user added !";
    }

    @GetMapping("/all")
    public List<UserCloned> getAllUsers(){
        return userClonedService.getAllUsers();
    }

    @GetMapping("/{userid}")
    public UserCloned getAnUser(@PathVariable Long userid){
        return userClonedService.getAnUser(userid);
    }

    @GetMapping("/currentUser")
    public UserCloned getCurrentUser(){
        return userClonedService.getCurrentUser();
    }


    @PostMapping("/add/Location")
    public UserCloned addLocation(@RequestBody Location location){
       UserCloned user  = userClonedService.getCurrentUser();
       Long location_id = locationService.addLocation(location);
       user.setLocation(locationService.getALocation(location_id));
       return userClonedService.addNewUser(user);
    }

    // add new people to the list of people who are followed by our current user
    @PostMapping("/follows/{id}")
    public UserCloned follower( @PathVariable Long id){
        UserCloned user = userClonedService.getCurrentUser();
        UserCloned user1 = userClonedService.getAnUser(id);
        user.whomFollows(user1);
        return userClonedService.addNewUser(user);
    }

    // list of people followed by our current user
    @GetMapping("/get/whomFollows")
    public Set<UserCloned> whomFollows(){
        UserCloned user = userClonedService.getCurrentUser();
        return user.getFollows();
    }

    // list of people who follows our current user
    @GetMapping("/get/followedBy")
    public Set<UserCloned> followedBy()
    {
        return userClonedService.getCurrentUser().getFollowedby();
    }

    @PostMapping("/edit")
    public String addImageLink(@RequestParam String username, @RequestParam String imageLink){
        userClonedService.addLink(username, imageLink);
        return "link added";
    }

    @GetMapping("/image")
    public String getLink(@RequestParam String username){
        return userClonedService.getLink(username);
    }

}
