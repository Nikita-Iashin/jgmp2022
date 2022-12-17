package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.model.Event;
import com.jgmp2022.jgmp2022.model.Ticket;
import com.jgmp2022.jgmp2022.model.User;

public interface BuyTicketFacade {
    boolean buyTicket(User user, Event event, Ticket ticket);
}
