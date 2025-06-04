package br.com.joaocarloslima.cbl_quest_tracker.mission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joaocarloslima.cbl_quest_tracker.student.Student;
import br.com.joaocarloslima.cbl_quest_tracker.team.Team;

@Service
public class MissionService {

    @Autowired
    private MissionRepository repository;

    public void save(Mission mission){
        repository.save(mission);
    }

    public List<Mission> listAll(){
        return repository.findAll();
    }

    public List<Mission> listByTeam(Team team) {
        System.out.println("Listing missions for team: " + team.getName());
        return repository.findByTeam(team);
    }

    public Mission findById(Long missionId) {
        return repository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found with id: " + missionId));
    }

    public List<Mission> listByTeamId(Long id) {
        return repository.findByTeamId(id);
    }

    public void changeTaskStatus(Long id, String status) {
        Mission mission = findById(id);
        if (mission == null) {
            throw new IllegalArgumentException("Mission not found with id: " + id);
        }
        mission.setStatus(status);
        save(mission);
    }

    public void deleteById(Long missionId) {
        repository.deleteById(missionId);
    }

    public void changeOwner(Long missionId, Student student) {
        Mission mission = findById(missionId);
        if (mission == null) {
            throw new IllegalArgumentException("Mission not found with id: " + missionId);
        }
        mission.setOwner(student);
        save(mission);
    }

}
