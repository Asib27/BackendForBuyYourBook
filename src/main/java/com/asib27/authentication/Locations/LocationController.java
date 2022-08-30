package com.asib27.authentication.Locations;

import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    LocationService locationService;
    @Autowired
    UserClonedService userClonedService;


    @PostMapping("/add")
    public String addNewLocation(@RequestBody Location location) {
        Location location1 = locationService.addLocation(location);
        UserCloned user = userClonedService.getCurrentUser();
        user.setLocation(location1);
        userClonedService.addNewUser(user);
        return "New location added!!";
    }

    @GetMapping("/get")
    public List<Location> getLocations() {
        return locationService.getLocations();
    }

    @DeleteMapping("/delete/{location_id}")
    public String deleteLocation(@PathVariable Long location_id) {
        locationService.deleteLocation(location_id);
        return "Location deleted !!";
    }
}
