package com.patterndesign.ticketGo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.services.TicketService;

@RestController
public class TicketController {
	
	private final TicketService ticketService;
	
	@Autowired
	public TicketController(final TicketService ticketService) {
		this.ticketService = ticketService;
	}
	
	@PutMapping(path = "/ticket/{id}")
	public ResponseEntity<Ticket> createUpdataTicket(@PathVariable final String id, @RequestBody final Ticket ticket){
		
		ticket.setId(id);
		final boolean isTicketExist = ticketService.isTicketExist(ticket);
		final Ticket savedTicket = ticketService.save(ticket);
		
		if(isTicketExist) {
			return new ResponseEntity<Ticket>(savedTicket,HttpStatus.OK);
		}else {
			return new ResponseEntity<Ticket>(savedTicket,HttpStatus.CREATED );
		}
	}
	
	@GetMapping(path = "/api/tickets/{id}")
	public ResponseEntity<Ticket> retrieveBook(@PathVariable final String id){
		
		final Optional<Ticket> foundTicket = ticketService.findById(id);
	
		return foundTicket.map(ticket -> new ResponseEntity<Ticket>(ticket, HttpStatus.OK))
		.orElse(new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND));
		
	}
	
	@GetMapping(path = "/api/tickets")
	public ResponseEntity<List<Ticket>> listBooks(){
		
		return new ResponseEntity<List<Ticket>>(ticketService.listTicket(),HttpStatus.OK);
		
	}	
	
	@DeleteMapping(path = "/tickets/{id}")
	public ResponseEntity deleteTicket(@PathVariable final String id) {
		ticketService.deleteTicketById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
}
