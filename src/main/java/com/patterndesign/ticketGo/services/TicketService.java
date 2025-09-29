package com.patterndesign.ticketGo.services;

import java.util.List;
import java.util.Optional;

import com.patterndesign.ticketGo.Domain.Ticket;


public interface TicketService {	
	
	boolean isTicketExist(Ticket ticket);
	
	Ticket save(Ticket ticket);
	
	Optional<Ticket> findById(String id);
	
	List<Ticket>listTicket();
	
	void deleteTicketById(String id);
	
}
