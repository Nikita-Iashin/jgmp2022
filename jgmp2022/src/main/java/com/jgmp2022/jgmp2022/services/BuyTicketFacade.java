package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import com.jgmp2022.jgmp2022.model.User;

public interface BuyTicketFacade {
    boolean buyTicket(User user, Event event, Ticket ticket);
}
