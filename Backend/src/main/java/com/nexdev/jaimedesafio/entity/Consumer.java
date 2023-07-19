package com.nexdev.jaimedesafio.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

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

    public void setUser(User user) {
        this.user = user;
    }

    public Legal getLegal() {
        return legal;
    }

    public void setLegal(Legal legal) {
        this.legal = legal;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

}
