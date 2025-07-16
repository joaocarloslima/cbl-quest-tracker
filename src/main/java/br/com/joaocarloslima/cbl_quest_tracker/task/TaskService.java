package br.com.joaocarloslima.cbl_quest_tracker.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.joaocarloslima.cbl_quest_tracker.mission.Mission;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public List<Task> listAll(){
        return repository.findAll();
    }

    public Task save(Task task) {
        return repository.save(task);
    }

    public void toggleDone(Long id) {
        var task = repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        task.setDone(!task.getDone());

        repository.save(task);
    }

    public List<Task> findByMission(Mission mission) {
        return repository.findByMission(mission);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Task findById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    
    
}
