package com.jgmp2022.jgmp2022.model.ticket;

import lombok.Data;

@Data
public class Ticket {
    private String ticketNumber;
    private boolean sold;

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
