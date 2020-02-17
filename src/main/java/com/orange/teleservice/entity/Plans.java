package com.orange.teleservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plans {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long planId;
	private String planName;
	private Double charges;	
}
