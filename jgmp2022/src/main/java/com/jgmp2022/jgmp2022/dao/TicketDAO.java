package com.jgmp2022.jgmp2022.dao;

import com.jgmp2022.jgmp2022.model.event.Event;
import com.jgmp2022.jgmp2022.model.ticket.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDAO {

    @Getter
    List<Ticket> tickets;

    {
        tickets = new ArrayList<>();
        tickets.add(new Ticket("AA1212"));
        tickets.add(new Ticket("AC3415"));
        tickets.add(new Ticket("DD1224"));
        tickets.add(new Ticket("AA1111"));
    }

    public Ticket getTicket(String ticket) {
        return tickets.stream().filter(e -> e.getTicketNumber().equalsIgnoreCase(ticket)).findAny().orElse(null);
    }

    public boolean addTicket(String ticket) {
        return tickets.add(new Ticket(ticket));
    }

    public void deleteTicket(String ticket) {
        tickets.stream().filter(e -> e.getTicketNumber().equalsIgnoreCase(ticket)).forEach(e -> tickets.remove(e));
    }

    public void updateEvent(String event) {
        tickets.stream().filter(e -> e.getTicketNumber().equalsIgnoreCase(event)).findFirst().ifPresent(e -> e
                .setTicketNumber(event));
    }

}
