package com.patterndesign.ticketGo.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.patterndesign.ticketGo.TestData.testTicket;
import static com.patterndesign.ticketGo.TestData.testTicketEntity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.Domain.TicketEntity;
import com.patterndesign.ticketGo.repositiories.TicketRepository;


@ExtendWith(MockitoExtension.class)
public class TicketserviceTest {

	@Mock
	private TicketRepository ticketRepository;
	
	
	@InjectMocks
	private TicketServiceImpl underTest;
	
	@Test
	public void testThatTicketIsSaved() {
		final Ticket ticket = testTicket();
		
		final TicketEntity ticketEntity = testTicketEntity();
		
		when(ticketRepository.save(eq(ticketEntity))).thenReturn(ticketEntity);
		
		final Ticket result = underTest.save(ticket);
		assertEquals(ticket, result);
	}
	
	
	@Test
	public void testThatFindByIdReturnsEmptyWhenNoTicket() {
		final String id = "123123678";
		
		when(ticketRepository.findById(eq(id))).thenReturn(Optional.empty());
		final Optional<Ticket> result = underTest.findById(id);
		assertEquals(Optional.empty(), result);
	}
	
	@Test
	public void testThatFindByIdReturnsTicketWhenExist() {
		final Ticket ticket = testTicket();
		final TicketEntity ticketEntity = testTicketEntity();
			
		when(ticketRepository.findById(eq(ticket.getId()))).thenReturn(Optional.of(ticketEntity));
		
		final Optional<Ticket> result = underTest.findById(ticket.getId());
		assertEquals(Optional.of(ticket), result);	
	}
	
	@Test
	public void testListNoTicketExist() {
		when(ticketRepository.findAll()).thenReturn(new ArrayList<TicketEntity>());
		final List<Ticket> result = underTest.listTicket();
		assertEquals(0, result.size());
	}
	
	@Test
	public void testListTicketExist() {
		
		final TicketEntity ticketEntity = testTicketEntity();
		when(ticketRepository.findAll()).thenReturn(List.of(ticketEntity));
		final List<Ticket> result = underTest.listTicket();
		assertEquals(1, result.size());
	}
	
	@Test
	public void testTicketNotExistFalse() {
		when(ticketRepository.existsById(any())).thenReturn(false);
		final boolean result = underTest.isTicketExist(null);
		assertEquals(false, result);
	}
	
	@Test
	public void testTicketExistTrue() {
		when(ticketRepository.existsById(any())).thenReturn(true);
		final boolean result = underTest.isTicketExist(testTicket());
		assertEquals(true, result);
	}	
	
	@Test
	public void testDeleteTicket() {
		final String id = "11231241";
		underTest.deleteTicketById(id);
		verify(ticketRepository,times(1)).deleteById(id);
	}
}
