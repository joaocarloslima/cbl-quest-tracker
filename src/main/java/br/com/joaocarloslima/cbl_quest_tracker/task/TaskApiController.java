package br.com.joaocarloslima.cbl_quest_tracker.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.joaocarloslima.cbl_quest_tracker.mission.MissionService;
import br.com.joaocarloslima.cbl_quest_tracker.student.Student;

@RestController
@RequestMapping
public class TaskApiController {

    record StatusRequest(String status) {}
    record OwnerRequest(Student owner) {}

    @Autowired
    private TaskService service;

    @Autowired
    private MissionService missionService;

    @PatchMapping("/task/{id}")
    public void toggleDone(@PathVariable Long id){
        service.toggleDone(id);
    }

    @PatchMapping("/mission/{id}")
    public void changeStatus(@PathVariable Long id, @RequestBody StatusRequest status){
        missionService.changeTaskStatus(id, status.status());
    }

    @PatchMapping("/mission/{missionId}/owner")
    public String changeOwner(@PathVariable Long missionId, @RequestBody OwnerRequest ownerRequest) {
        missionService.changeOwner(missionId, ownerRequest.owner());
        return "redirect:/mission/" + missionId + "/tasks";
    }
    
}
