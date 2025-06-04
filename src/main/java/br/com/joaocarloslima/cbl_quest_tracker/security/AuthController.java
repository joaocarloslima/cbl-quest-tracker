package br.com.joaocarloslima.cbl_quest_tracker.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;

import br.com.joaocarloslima.cbl_quest_tracker.team.TeamService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public String loginForm(){
        return "index.html";
    }

    @GetMapping("/post-login")
    public String postLogin(HttpSession session, @AuthenticationPrincipal User user) {
        System.out.println("logando " + user);
        var team = teamService.findByAccessCode(user.getUsername());
        session.setAttribute("teamId", team.getId());
        return "redirect:/team";
    }

    @GetMapping("/logout-success")
    public String logout(SessionStatus status, HttpSession session) {
        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }
    
}
