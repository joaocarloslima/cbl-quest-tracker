package br.com.joaocarloslima.cbl_quest_tracker.mission;

import java.util.List;

import br.com.joaocarloslima.cbl_quest_tracker.student.Student;
import br.com.joaocarloslima.cbl_quest_tracker.task.Task;
import br.com.joaocarloslima.cbl_quest_tracker.team.Team;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @Builder.Default
    private String status = "smile";

    private String color;

    @Enumerated(EnumType.STRING)
    private Student owner;

    @ManyToOne
    private Team team;

    @OneToMany(mappedBy = "mission")
    private List<Task> tasks;
    
}
