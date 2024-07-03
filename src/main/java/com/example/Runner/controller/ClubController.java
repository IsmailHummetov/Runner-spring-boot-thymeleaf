package com.example.Runner.controller;

import com.example.Runner.dto.ClubDto;
import com.example.Runner.models.User;
import com.example.Runner.security.SecurityUtil;
import com.example.Runner.service.inter.ClubService;
import com.example.Runner.service.inter.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {

    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService,UserService userService)
    {
        this.clubService = clubService;
        this.userService = userService;
    }

    @GetMapping(value = {"/","/clubs"})
    public String listClubs(Model model) {
        List<ClubDto> clubs = clubService.findAllClub();
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
    @GetMapping("/myClubs")
        public String myClubs(Model model){
        User user = new User();
        if (SecurityUtil.getSessionUser()!=null)
            user=userService.findUserByUsername(SecurityUtil.getSessionUser());
        List<ClubDto> myClubs =  clubService.myClubs(user);
        model.addAttribute("clubs",myClubs);
        return "myClubs-list";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value = "query") String query,Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        ClubDto clubDto = new ClubDto();
        model.addAttribute("club", clubDto);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubsId}")
    public String clubDetail(@PathVariable("clubsId") Integer clubsId,Model model){
        String username = SecurityUtil.getSessionUser();
        User user = new User();
        if (username!=null){
            user = userService.findUserByUsername(username);
        }
        ClubDto clubDto = clubService.findById(clubsId);
        model.addAttribute("user",user);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Integer clubId, Model model) {
        ClubDto clubDto = clubService.findById(clubId);
        model.addAttribute("club", clubDto);
        return "clubs-edit";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Integer clubId,Model model){
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @PostMapping(value = "/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Integer clubId, @Valid @ModelAttribute("club") ClubDto clubDto,
                             BindingResult result, Model model) {
        if(result.hasErrors())
        {
            model.addAttribute("club",clubDto);
            return "clubs-edit";
        }
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

}
