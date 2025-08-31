package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.dto.WorkoutDTO;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.model.Workout;
import com.fitnessapp.fitnesstracker.model.WorkoutResponseDTO;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import com.fitnessapp.fitnesstracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private UserRepository userRepository;
    @PostMapping
    public Workout createWorkout(@RequestBody WorkoutDTO workoutDTO, @AuthenticationPrincipal UserDetails userDetails){
        User user= userRepository.findByEmail(userDetails.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        Workout workout=new Workout();
        workout.setExerciseName(workoutDTO.getExerciseName());
        workout.setSets(workoutDTO.getSets());
        workout.setReps(workoutDTO.getReps());
        workout.setWeight(workoutDTO.getWeight());
        workout.setDurationMinutes(workoutDTO.getDurationMinutes());
        workout.setCaloriesBurned(workoutDTO.getCaloriesBurned());
        workout.setUser(user);
        return workoutRepository.save(workout);
    }

    @GetMapping("/me")
    public List<WorkoutResponseDTO> getWorkoutsForUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Workout> workouts = workoutRepository.findByUser(user);
        return workouts.stream().map(this::mapToResponseDTO).toList();
    }

    private WorkoutResponseDTO mapToResponseDTO(Workout workout) {
        WorkoutResponseDTO dto = new WorkoutResponseDTO();
        dto.setId(workout.getId());
        dto.setExerciseName(workout.getExerciseName());
        dto.setSets(workout.getSets());
        dto.setReps(workout.getReps());
        dto.setWeight(workout.getWeight());
        dto.setDurationMinutes(workout.getDurationMinutes());
        dto.setCaloriesBurned(workout.getCaloriesBurned());
        dto.setDate(workout.getDate());
        return dto;
    }
}
