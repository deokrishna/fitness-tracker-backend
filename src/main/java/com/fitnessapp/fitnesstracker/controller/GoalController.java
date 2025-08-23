package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.model.Goal;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.GoalRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class GoalController {
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}")
    public Goal createGoal(@PathVariable Long userId, @RequestBody Goal goal){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        goal.setUser(user);
        return  goalRepository.save(goal);
    }

    @GetMapping("/{userId}")
    public List<Goal> getGoalsByUser(@PathVariable Long userId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        return goalRepository.findByUser(user);
    }
    @PutMapping("/{goalId}/achieve")
    public Goal markGoalAchieved(@PathVariable Long goalId){
        Goal goal=goalRepository.findById(goalId).orElseThrow(()->new RuntimeException("goal not found"));
        goal.setAchieved(true);
        return goalRepository.save(goal);
    }
}
