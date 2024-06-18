package com.springBootJWT.SpringJwt_Project.repository;

import com.springBootJWT.SpringJwt_Project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE role =? AND name !=?",nativeQuery = true)
    public List<User> showUsersLists(String role,String name);

    @Query(value = "select count(*) FROM user WHERE pending_request=true AND role ='USER'",nativeQuery = true)
    Integer showUserCountPendingRequestTrue();

    @Query(value = "select count(*) FROM user WHERE pending_request=false AND role='USER'",nativeQuery = true)
    Integer showUserCountPendingRequestFalse();

    @Query(value = "SELECT COUNT(*) FROM user WHERE role='USER'",nativeQuery = true)
    Integer showUserCount();

}
