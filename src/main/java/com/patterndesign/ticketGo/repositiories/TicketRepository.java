package com.patterndesign.ticketGo.repositiories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patterndesign.ticketGo.Domain.TicketEntity;


@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, String>{
	
}
