package com.mh.crj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false,length = 50)
	private String firstName;
	
	@Column(nullable = false,length = 50)
	private String lastName;
	
	@Column(nullable = false, unique = true,length = 100)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false,length = 15)
	private String mobile;
	
	@Column(nullable = false,updatable = false)
	private LocalDateTime createdDate;
	
	@Column(nullable = false)
	private LocalDateTime updatedDate;
	
    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
