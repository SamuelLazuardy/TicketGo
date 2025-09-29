package com.patterndesign.ticketGo;

import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.Domain.TicketEntity;

public final class TestData {
	
	private TestData() {
		
	}
	
	public static Ticket testTicket() {
		return Ticket.builder()
				.id("12345678")
				.name("Bruno Mars Concert")
				.type("Concert")
				.price(20).build();
	}
	
	public static TicketEntity testTicketEntity() {
		return TicketEntity.builder()
				.id("12345678")
				.name("Bruno Mars Concert")
				.type("Concert")
				.price(20).build();
	}
	
	
}
