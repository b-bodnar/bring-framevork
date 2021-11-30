package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotation.Autowired;
import com.bobocode.hoverla.bring.annotation.Component;
import com.bobocode.hoverla.bring.annotation.Qualifier;

@Component
public class BobocodeComponent {
    @Autowired
    @Qualifier("Super participants")
    private ParticipantService participantService;
}
