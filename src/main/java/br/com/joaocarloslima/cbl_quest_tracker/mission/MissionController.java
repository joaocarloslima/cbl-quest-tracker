package br.com.joaocarloslima.cbl_quest_tracker.mission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaocarloslima.cbl_quest_tracker.student.Student;
import br.com.joaocarloslima.cbl_quest_tracker.task.TaskService;
import br.com.joaocarloslima.cbl_quest_tracker.team.TeamService;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeamService teamService;

    @GetMapping("form")
    public String form(Mission mission, Model model){
        model.addAttribute("mission", mission);
        model.addAttribute("students", Student.values());
        return "mission-form";
    }

    @GetMapping("{missionId}/tasks")
    public String missionTasks(@PathVariable Long missionId, Model model){
        var mission = missionService.findById(missionId);
        model.addAttribute("tasks", taskService.findByMission(mission));
        model.addAttribute("mission", mission);
        model.addAttribute("students", Student.values());
        return "mission-tasks";
    }

    @PostMapping
    public String save(Mission mission, HttpSession session){
        var teamId = (Long) session.getAttribute("teamId");
        var team = teamService.findById(teamId);
        mission.setTeam(team);
        missionService.save(mission);
        return "redirect:/team";
    }

    @PostMapping("{missionId}/delete")
    public String delete(@PathVariable Long missionId) {
        missionService.deleteById(missionId);
        return "redirect:/team";
    }

}
