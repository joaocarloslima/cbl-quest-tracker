package br.com.joaocarloslima.cbl_quest_tracker.dashboard;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.joaocarloslima.cbl_quest_tracker.mission.Mission;
import br.com.joaocarloslima.cbl_quest_tracker.mission.MissionService;
import br.com.joaocarloslima.cbl_quest_tracker.team.Team;
import br.com.joaocarloslima.cbl_quest_tracker.team.TeamController.MissionCardData;
import br.com.joaocarloslima.cbl_quest_tracker.team.TeamService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private TeamService service;

    @Autowired
    private MissionService missionService;

    @Value("${app.start.date}")
    private String startDate;

    @Value("${app.end.date}")
    private String endDate;

    @GetMapping
    public String index(Model model) {
        List<Team> teams = service.getAll();
        Map<Long, List<MissionCardData>> missionsByTeam = new HashMap<>();

        Map<Long, Long> totalTasksPercentByTeam = new HashMap<>();
        Map<Long, Long> timeSpendPercentByTeam = new HashMap<>();

        for (Team team : teams) {
            List<Mission> missions = missionService.listByTeamId(team.getId());
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
            long totalTasks = 0;
            long doneTasks = 0;

            for (var mission : missions) {
                for (var task : mission.getTasks()) {
                    totalTasks++;
                    if (task.getDone()) {
                        doneTasks++;
                    }
                }
            }

            long totalTasksPercent = totalTasks > 0 ? doneTasks * 100 / totalTasks : 0;

            var start = LocalDate.parse(startDate);
            var end = LocalDate.parse(endDate);
            var currentDate = LocalDate.now();
            int timeSpend = (int) (currentDate.toEpochDay() - start.toEpochDay());
            int totalTime = (int) (end.toEpochDay() - start.toEpochDay());
            long timeSpendPercent = (long) ((double) timeSpend / totalTime * 100);

            totalTasksPercentByTeam.put(team.getId(), totalTasksPercent);
            timeSpendPercentByTeam.put(team.getId(), timeSpendPercent);

            missionsByTeam.put(team.getId(), missionCards);
        }

        model.addAttribute("missionsByTeam", missionsByTeam);
        model.addAttribute("teams", teams);
        model.addAttribute("timeSpendPercentByTeam", timeSpendPercentByTeam);
        model.addAttribute("totalTasksPercentByTeam", totalTasksPercentByTeam);
        return "dashboard";
    }
}
