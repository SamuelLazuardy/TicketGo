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
public class Ticket {
	
	@Id
	private String id;
	private String name;
	private String type;
	private int price;
}

