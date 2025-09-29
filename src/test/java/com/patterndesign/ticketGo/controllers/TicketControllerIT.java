package com.patterndesign.ticketGo.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.patterndesign.ticketGo.TestData;
import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.services.TicketService;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TicketControllerIT {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private TicketService ticketService;
	
	@Test
	public void testThatTicketIsCreated() throws Exception {
	    final Ticket ticket = TestData.testTicket();
	    final ObjectMapper objectMapper = new ObjectMapper();
	    final String ticketJson = objectMapper.writeValueAsString(ticket);

	    mockMvc.perform(MockMvcRequestBuilders.put("/ticket/" + ticket.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(ticketJson))
//	            .andDo(MockMvcResultHandlers.print()) // <--- See what's returned
	            .andExpect(MockMvcResultMatchers.status().isCreated())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ticket.getId()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(ticket.getName()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(ticket.getType()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(ticket.getPrice()));
	}
	
	
	@Test
	public void testThatTicketIsUpdated() throws Exception {
	    final Ticket ticket = TestData.testTicket();
	    ticketService.save(ticket);

	    ticket.setName("asdasd"); 
	    
	    final ObjectMapper objectMapper = new ObjectMapper();
	    final String ticketJson = objectMapper.writeValueAsString(ticket);

	    mockMvc.perform(MockMvcRequestBuilders.put("/ticket/" + ticket.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(ticketJson))
//	            .andDo(MockMvcResultHandlers.print()) // <--- See what's returned
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ticket.getId()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(ticket.getName()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.type").value(ticket.getType()))
	            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(ticket.getPrice()));
	}
		

	@Test
	public void testThatRetrieveTicketReturn404WhenNotFound() throws Exception{
		
		mockMvc.perform(MockMvcRequestBuilders.get( "/tickets/12312312312"))
		.andExpect(MockMvcResultMatchers.status().isNotFound());
		
		
	}
		
	
	@Test
	public void testThatRetrieveBookReturn200WhenTicketExist() throws Exception{
		
		final Ticket ticket = TestData.testTicket();
		ticketService.save(ticket);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tickets/" + ticket.getId()))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(ticket.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.name").value(ticket.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.type").value(ticket.getType()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(ticket.getPrice()));		
		
}

	@Test
	public void testThatListReturn200NoExist() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/tickets"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	@Test
	public void testThatListReturn200Exist() throws Exception{
		final Ticket ticket = TestData.testTicket();
		ticketService.save(ticket);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/tickets"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(ticket.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value(ticket.getName()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].type").value(ticket.getType()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.[0].price").value(ticket.getPrice()));		
		
		
	}
	
	@Test
	public void ticketDoesntExistWhenDeleted() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/tickets/123131313"))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	public void ticketIsDeleted() throws Exception{
		final Ticket ticket = TestData.testTicket();
		ticketService.save(ticket);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/tickets/" + ticket.getId()))
		.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}
