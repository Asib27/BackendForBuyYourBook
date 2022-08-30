package com.asib27.authentication.UserCloned;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserClonedRepository extends JpaRepository<UserCloned, Long> {

    @Query(value = "select userid from user_cloned where username = ?1", nativeQuery = true)
    Long findUserId(String name);

    @Query(value = "select first_name, middle_name, last_name, phone_number, backup_phone_number from user_cloned where userid = ?1",
    nativeQuery = true)
    List<Object[]> getPersonalInfo(Long id);

    @Query(value = "select about_user, favourite_books,favourite_genre from user_cloned where userid = ?1", nativeQuery = true)
    List<Object[]> getAboutInfo(Long id);
}
