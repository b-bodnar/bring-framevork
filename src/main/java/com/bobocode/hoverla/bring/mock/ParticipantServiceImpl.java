package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotation.Component;

import java.util.List;

@Component
public class ParticipantServiceImpl implements ParticipantService {
    @Override
    public List<String> getParticipants() {
        return List.of("Bo", "Bo", "Co", "De");
    }
}
