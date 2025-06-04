package br.com.joaocarloslima.cbl_quest_tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.joaocarloslima.cbl_quest_tracker.mission.Mission;
import br.com.joaocarloslima.cbl_quest_tracker.mission.MissionRepository;
import br.com.joaocarloslima.cbl_quest_tracker.task.Task;
import br.com.joaocarloslima.cbl_quest_tracker.task.TaskRepository;
import br.com.joaocarloslima.cbl_quest_tracker.team.Team;
import br.com.joaocarloslima.cbl_quest_tracker.team.TeamRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class App {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MissionRepository missionRepository;

	@Autowired
	private TaskRepository taskRepository;

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void init(){
		teamRepository.saveAll(List.of(
			Team.builder().name("Taskid").color("teal").status("happy").accessCode("taskid").imageUrl("taskid.png").build(),
			Team.builder().name("Pisco").color("pink").status("happy").accessCode("pisco").imageUrl("pisco.png").build(),
			Team.builder().name("Guardiões dos Rios").color("amber").status("happy").accessCode("rios").build()
		));

		
	}	

}
