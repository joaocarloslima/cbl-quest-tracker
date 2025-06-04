package br.com.joaocarloslima.cbl_quest_tracker.team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TeamService {

    @Autowired
    private TeamRepository repository;

    public List<Team> getAll(){
        return repository.findAll();
    }

    public void save(Team team){
        repository.save(team);
    }

    public Team findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    public Team findByAccessCode(String username) {
        return repository.findByAccessCode(username);
    }
    
}
