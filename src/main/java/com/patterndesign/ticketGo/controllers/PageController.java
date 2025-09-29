package com.patterndesign.ticketGo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.patterndesign.ticketGo.Domain.Ticket;
import com.patterndesign.ticketGo.services.TicketService;

@Controller
public class PageController {

	@Autowired
	private TicketService ticketService;
	
    @GetMapping("/home")
    public String home() {
        return "home";
    }
    
    @GetMapping("/see-ticket")
    public String seeTicket(Model model) {
        List<Ticket> tickets = ticketService.listTicket();
        model.addAttribute("tickets", tickets);
        return "see-ticket";
    }
    
    @PostMapping("/tickets/delete/{id}")
    public String deleteTicket(@PathVariable String id) {
        ticketService.deleteTicketById(id);
        return "home";
    }
    
    @GetMapping("/buy-ticket")
    public String butTicket() {
        return "but-ticket";
    }
}
