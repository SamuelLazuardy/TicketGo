package com.patterndesign.ticketGo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.Domain.TicketEntity;
import com.patterndesign.ticketGo.repositiories.TicketRepository;
import com.patterndesign.ticketGo.services.TicketService;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

	private final TicketRepository ticketRepository;
	
	@Autowired
	public TicketServiceImpl(final TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}
	
	@Override
	public Ticket save(final Ticket ticket) {
		final TicketEntity ticketEntity = ticketToTicketEntity(ticket);
		final TicketEntity saveTicketEntity = ticketRepository.save(ticketEntity);
		
		return ticketEntityToTicket(saveTicketEntity);
	}
	
	private TicketEntity ticketToTicketEntity(Ticket ticket) {
	    return TicketEntity.builder()
	    		.id(ticket.getId())
	    		.name(ticket.getName())
	    		.type(ticket.getType())
	    		.price(ticket.getPrice())
	    		.build();
	}
	
	private Ticket ticketEntityToTicket(TicketEntity ticketEntity) {
	    return Ticket.builder()
	    		.id(ticketEntity.getId())
	    		.name(ticketEntity.getName())
	    		.type(ticketEntity.getType())
	    		.price(ticketEntity.getPrice())
	    		.build();
	}

	@Override
	public Optional<Ticket> findById(String id) {
		
		final Optional<TicketEntity> foundTicket = ticketRepository.findById(id);
		return foundTicket.map(ticket -> ticketEntityToTicket(ticket));
		
	}

	@Override
	public List<Ticket> listTicket() {
		
		final List<TicketEntity> foundTicket = ticketRepository.findAll();
		return foundTicket.stream().map(ticket -> ticketEntityToTicket(ticket)).collect(Collectors.toList());

	}

	@Override
	public void deleteTicketById(String id) {
		
		try {
			ticketRepository.deleteById(id);
		}catch (final EmptyResultDataAccessException ex) {
			log.debug("Attemted to delete non exist ticket",ex);
		}
		
		
	}

	@Override
	public boolean isTicketExist(Ticket ticket) {
		
		return ticketRepository.existsById(ticket.getId());
	}	
}
