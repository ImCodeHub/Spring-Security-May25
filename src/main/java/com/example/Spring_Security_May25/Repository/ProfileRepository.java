package com.example.Spring_Security_May25.Repository;

import com.example.Spring_Security_May25.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUserId(Long userId);
}
