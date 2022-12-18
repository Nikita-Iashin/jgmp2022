package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.dao.Storage;
import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import com.jgmp2022.jgmp2022.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BuyTicketFacadeImplTest {

    @Autowired
    private BuyTicketFacade buyTicketFacade;

    @Test
    void testHappyPathForTicketFacade() {

        Event event = new Event("testEvent");
        User user = new User("testUser");
        Ticket ticket = new Ticket("AC2121TT");

        assertTrue(buyTicketFacade.buyTicket(user, event, ticket), String.format(
                "There was a problem to buy the ticket for user - %s, event - %s, ticket # - %s.", user, event, ticket
        ));
    }

}