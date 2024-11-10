package com.practice.SpringStateMachine.controller;

import com.practice.SpringStateMachine.Enum.Events;
import com.practice.SpringStateMachine.Enum.OrderProcessing;
import com.practice.SpringStateMachine.service.StateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.state.State;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statemachine")
public class StateMachineController {

    private final StateMachineService stateMachineService;

    @Autowired
    public StateMachineController(StateMachineService stateMachineService) {
        this.stateMachineService = stateMachineService;
    }

    @GetMapping("/start")
    public ResponseEntity<String> startStateMachine() {
        stateMachineService.startStateMachine();
        return ResponseEntity.ok("State Machine Started");
    }

    @GetMapping("/event/{event}")
    public ResponseEntity<State<OrderProcessing, Events>> sendEvent(@PathVariable("event") Events events ) {
        stateMachineService.sendEvent(events);

        return ResponseEntity.ok(stateMachineService.getState());
    }
}
