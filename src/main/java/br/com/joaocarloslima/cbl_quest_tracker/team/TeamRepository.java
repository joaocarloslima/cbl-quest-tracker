package br.com.joaocarloslima.cbl_quest_tracker.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    Team findByAccessCode(String username);

    
} 
