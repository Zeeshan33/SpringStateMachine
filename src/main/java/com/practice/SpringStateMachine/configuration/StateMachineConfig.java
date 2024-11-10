package com.practice.SpringStateMachine.configuration;

import com.practice.SpringStateMachine.Enum.Events;
import com.practice.SpringStateMachine.Enum.OrderProcessing;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderProcessing, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderProcessing, Events> states) throws Exception {
        states
            .withStates()
            .initial(OrderProcessing.ORDER_IN_PROCESSING)
            .state(OrderProcessing.ORDER_PROCESSED)
            .state(OrderProcessing.ORDER_PACKED)
            .state(OrderProcessing.ORDER_SHIPPED)
            .end(OrderProcessing.ORDER_DELIVERED)
            .end(OrderProcessing.ORDER_RETURNED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderProcessing, Events> transitions) throws Exception {
        // Define transitions between states
        transitions.withExternal()
            .source(OrderProcessing.ORDER_IN_PROCESSING).target(OrderProcessing.ORDER_PROCESSED).event(Events.EVENT_PROCESSED)

            .and().withExternal()
            .source(OrderProcessing.ORDER_PROCESSED).target(OrderProcessing.ORDER_PACKED).event(Events.EVENT_PACKED)

            .and().withExternal()
            .source(OrderProcessing.ORDER_PACKED).target(OrderProcessing.ORDER_SHIPPED).event(Events.EVENT_SHIPPED)

            .and().withExternal()
            .source(OrderProcessing.ORDER_SHIPPED).target(OrderProcessing.ORDER_DELIVERED).event(Events.EVENT_DELIVERED)

            .and().withExternal()
            .source(OrderProcessing.ORDER_SHIPPED).target(OrderProcessing.ORDER_RETURNED).event(Events.EVENT_RETURNED);
    }
}
