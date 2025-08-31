package com.fitnessapp.fitnesstracker.controller;

import com.fitnessapp.fitnesstracker.dto.CardioRequestDTO;
import com.fitnessapp.fitnesstracker.dto.CardioResponseDTO;
import com.fitnessapp.fitnesstracker.model.Cardio;
import com.fitnessapp.fitnesstracker.model.User;
import com.fitnessapp.fitnesstracker.repository.CardioRepository;
import com.fitnessapp.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cardio")
public class CardioController {

    @Autowired
    private CardioRepository cardioRepository;

    @Autowired
    private UserRepository userRepository;

    // Log cardio session
    @PostMapping
    public CardioResponseDTO logCardio(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CardioRequestDTO dto) {
        User user=userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));
        Cardio cardio=new Cardio();
        cardio.setActivityType(dto.getActivityType());
        cardio.setDistanceKm(dto.getDistanceKm());
        cardio.setDurationMinutes(dto.getDurationMinutes());

        // backend calculates
        cardio.setPace(dto.getDurationMinutes() > 0 ?
                (double) dto.getDurationMinutes() / dto.getDistanceKm() : 0);
        cardio.setCaloriesBurned((int) (dto.getDurationMinutes() * 8)); // simple formula

        cardio.setUser(user);
        Cardio saved=cardioRepository.save(cardio);
        CardioResponseDTO res=new CardioResponseDTO();
        res.setId(saved.getId());
        res.setActivityType(saved.getActivityType());
        res.setDistanceKm(saved.getDistanceKm());
        res.setDurationMinutes(saved.getDurationMinutes());
        res.setPace(saved.getPace());
        res.setCaloriesBurned(saved.getCaloriesBurned());
        res.setDate(saved.getDate());
        return res;
    }

    // Get all cardio sessions for user
    @GetMapping("/me")
    public List<CardioResponseDTO> getCardioByUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user=userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<Cardio> sessions=cardioRepository.findByUser(user);
        return sessions.stream()
                .map(cardio -> {
                    CardioResponseDTO dto = new CardioResponseDTO();
                    dto.setId(cardio.getId());
                    dto.setActivityType(cardio.getActivityType());
                    dto.setDistanceKm(cardio.getDistanceKm());
                    dto.setDurationMinutes(cardio.getDurationMinutes());
                    dto.setPace(cardio.getPace());
                    dto.setCaloriesBurned(cardio.getCaloriesBurned());
                    dto.setDate(cardio.getDate());
                    return dto;
                }).toList();
    }
}
