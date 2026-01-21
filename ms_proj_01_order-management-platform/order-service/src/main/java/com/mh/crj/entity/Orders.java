package com.mh.crj.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer userId;
	
	@Column(nullable = false)
	private Integer productId;

	@Min(0)
	@Column(nullable = false)
    private Integer quantity;

	@Column(nullable = false,updatable = false)
    private Double totalAmount;


	@Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
	

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
