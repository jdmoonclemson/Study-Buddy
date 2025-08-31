package com.clemson.studybuddy.web;


import com.clemson.studybuddy.dto.MatchResult;
import com.clemson.studybuddy.service.MatchService;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/match")
public class MatchController {
    private final MatchService matchService;
    public MatchController(MatchService service){ this.matchService = service; }


    @GetMapping
    public List<MatchResult> suggest(@RequestParam Long studentId, @RequestParam String course){
        return matchService.suggestMatches(studentId, course);
    }
}