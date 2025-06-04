package br.com.joaocarloslima.cbl_quest_tracker.team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaocarloslima.cbl_quest_tracker.mission.MissionService;
import br.com.joaocarloslima.cbl_quest_tracker.student.Student;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("team")
public class TeamController {

    @Autowired
    private TeamService service;

    @Autowired
    MissionService missionService;

    @Value("${app.start.date}")
    private String startDate;

    @Value("${app.end.date}")
    private String endDate;

    public record MissionCardData(Long id, String description, String color, String status, Student owner, long percent) {
    }

    @GetMapping("form")
    public String form(Team team, Model model) {
        model.addAttribute("team", team);
        return "team-form";
    }

    @GetMapping
    public String teamBoard(HttpSession session, Model model) {
        var teamId = (Long) session.getAttribute("teamId");
        var team = service.findById(teamId);
        var missions = missionService.listByTeam(team);

        var start = LocalDate.parse(startDate);
        var end = LocalDate.parse(endDate);
        var currentDate = LocalDate.now();
        int timeSpend = (int) (currentDate.toEpochDay() - start.toEpochDay());
        int totalTime = (int) (end.toEpochDay() - start.toEpochDay());
        int timeSpendPercent = (int) ((double) timeSpend / totalTime * 100);
        model.addAttribute("timeSpendPercent", timeSpendPercent);

        var missionCards = new ArrayList<MissionCardData>();
        for (var mission : missions) {
            long percent = 0;
            int totalTasksInMission = mission.getTasks().size();
            if (totalTasksInMission > 0) {
                percent = mission.getTasks().stream()
                        .filter(task -> task.getDone())
                        .count() * 100 / totalTasksInMission;
            }
            var cardData = new MissionCardData(
                    mission.getId(),
                    mission.getDescription(),
                    mission.getColor(),
                    mission.getStatus(),
                    mission.getOwner(),
                    percent);
            missionCards.add(cardData);
        }
        model.addAttribute("missions", missionCards);

        var totalTasks = missions.stream()
                .flatMap(mission -> mission.getTasks().stream())
                .count();
        var totalDoneTasks = missions.stream()
                .flatMap(mission -> mission.getTasks().stream())
                .filter(task -> task.getDone())
                .count();
        long totalTasksPercent = 0;
        if (totalTasks > 0) {
            totalTasksPercent = totalDoneTasks * 100 / totalTasks;
        }
        model.addAttribute("totalTasksPercent", totalTasksPercent);

        model.addAttribute("team", team);
        return "team";
    }

    @PostMapping
    public String create(Team team) {
        service.save(team);
        return "redirect:/dashboard";
    }

}
