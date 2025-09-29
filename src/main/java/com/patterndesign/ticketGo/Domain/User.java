package com.patterndesign.ticketGo.Domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	
	@Id
	private String Id;
	private String name;
	private String password;
	private Ticket ticket;
}
