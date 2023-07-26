package com.nexdev.jaimedesafio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Cliente")
public class Consumer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "data_cadastro")
    private Date createdAt;

    @LastModifiedDate
    @UpdateTimestamp
    @Column(name = "data_alteracao")
    private Date updatedAt;

    private String phone;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Individual individual;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private Legal legal;

    @ManyToOne
    private User user;

}
