package br.com.joaocarloslima.cbl_quest_tracker.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.joaocarloslima.cbl_quest_tracker.mission.Mission;

public interface TaskRepository extends JpaRepository<Task, Long>{

    List<Task> findByMission(Mission mission);
    
}
