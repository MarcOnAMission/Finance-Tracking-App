package com.MarcOnAMission.finance_tracker.Repositories;

import com.MarcOnAMission.finance_tracker.Model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,Long> {
        Optional<ApplicationUser> findByUsername(String username);
}
