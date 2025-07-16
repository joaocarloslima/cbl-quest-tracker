package br.com.joaocarloslima.cbl_quest_tracker.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaocarloslima.cbl_quest_tracker.mission.Mission;

@Controller
@RequestMapping("task")
public class TaskController {

    record TaskRequest(Long missionId, String text){}

    @Autowired
    private TaskService service;

    @PostMapping
    public String addTask(TaskRequest request){
        var missionId = request.missionId();
        if (request.missionId() == null || request.text() == null || request.text().isBlank()) {
            return ("redirect:/mission/" + missionId + "/tasks");
        }
        var task = Task.builder()
                .text(request.text())
                .mission(Mission.builder().id(missionId).build())
                .build();
        service.save(task);
        return ("redirect:/mission/" + missionId + "/tasks");
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/mission/" + id + "/tasks";
    }

}
