package com.practice.SpringStateMachine.service;

import com.practice.SpringStateMachine.Enum.Events;
import com.practice.SpringStateMachine.Enum.OrderProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Service;

@Service
public class StateMachineService {
    private final StateMachine<OrderProcessing, Events> stateMachine;

    @Autowired
    public StateMachineService(StateMachine<OrderProcessing, Events> stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void startStateMachine() {
        stateMachine.start();
    }

    public void sendEvent(Events events) {
        stateMachine.sendEvent(events);
    }

    public State<OrderProcessing, Events> getState() {
        return stateMachine.getState();
    }
}
