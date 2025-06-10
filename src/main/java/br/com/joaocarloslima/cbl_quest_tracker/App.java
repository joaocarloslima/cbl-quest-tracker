package br.com.joaocarloslima.cbl_quest_tracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.joaocarloslima.cbl_quest_tracker.team.Team;
import br.com.joaocarloslima.cbl_quest_tracker.team.TeamRepository;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class App {

	@Autowired
	private TeamRepository teamRepository;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@PostConstruct
	public void init(){
		teamRepository.saveAll(List.of(
			Team.builder().name("Team01").color("teal").status("happy").accessCode("team01").imageUrl("taskid.png").build(),
			Team.builder().name("Team02").color("pink").status("happy").accessCode("team02").imageUrl("pisco.png").build(),
			Team.builder().name("Team03").color("amber").status("happy").accessCode("team03").imageUrl("pisco.png").build()
		));

		
	}	

}
