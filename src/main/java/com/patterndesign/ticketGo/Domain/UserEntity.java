package com.patterndesign.ticketGo.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ticket")
public class UserEntity {
	
	@Id
	private String Id;
	private String name;
	private String password;
	private Ticket ticket;
}
