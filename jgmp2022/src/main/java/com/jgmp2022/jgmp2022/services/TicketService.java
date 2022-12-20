package com.jgmp2022.jgmp2022.services;

import com.jgmp2022.jgmp2022.dao.Storage;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TicketService {

    @Autowired
    Storage storage;

    public boolean sellTicket(Ticket ticket) {
        log.info("Selling ticket to the event {}", ticket.getTicketNumber());
        ticket.setSold(true);
        return true;
    }

    public List<Ticket> getTickets() {
        return storage.getTickets();
    }
}
