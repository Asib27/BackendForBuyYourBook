package com.asib27.authentication.Locations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select check_location(?1, ?2,?3)", nativeQuery = true)
    Long getLocationId(String country, String street, String district);
}
