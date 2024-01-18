package com.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Data
@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductResource {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String pname;
	
	@Column(unique = true)
	private String pcode;// 
	
	@Column(nullable = false)
	private LocalDate manufacturingDate;
	
//	@Enumerated(EnumType.STRING)
//    @Column(name = "category")
//	private Category cat;
}

