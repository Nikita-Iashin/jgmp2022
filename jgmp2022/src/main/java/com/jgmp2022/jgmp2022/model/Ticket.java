package com.jgmp2022.jgmp2022.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private String ticketNumber;
    private boolean sold;

    public Ticket(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
