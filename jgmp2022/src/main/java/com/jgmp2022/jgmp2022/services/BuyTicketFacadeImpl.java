package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import com.jgmp2022.jgmp2022.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyTicketFacadeImpl implements BuyTicketFacade {

    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private UserService userService;

    @Override
    public boolean buyTicket(User user, Event event, Ticket ticket) {
        return eventService.registerUser(user, event) && ticketService.sellTicket(ticket)
                && userService.addTicketToBasket(user, event);
    }
}
