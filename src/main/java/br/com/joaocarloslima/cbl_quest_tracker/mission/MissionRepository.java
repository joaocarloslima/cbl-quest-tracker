package br.com.joaocarloslima.cbl_quest_tracker.mission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaocarloslima.cbl_quest_tracker.team.Team;

public interface MissionRepository extends JpaRepository<Mission, Long>{

    List<Mission> findByTeam(Team team);

    List<Mission> findByTeamId(Long id);
    
}
