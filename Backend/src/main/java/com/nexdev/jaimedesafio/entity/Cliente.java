package com.nexdev.jaimedesafio.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    private Date dataCadastro;

    @UpdateTimestamp
    private Date dataAlteracao;

    private String telefone;

    @OneToOne
    @JoinColumn
    private Fisica fisica;

    @OneToOne
    @JoinColumn
    private Juridica juridica;

    @ManyToOne
    private Usuario usuario;

    public String getId() {
        return id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public String getTelefone() {
        return telefone;
    }

    public Fisica getFisica() {
        return fisica;
    }

    public Juridica getJuridica() {
        return juridica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setFisica(Fisica fisica) {
        this.fisica = fisica;
    }

    public void setJuridica(Juridica juridica) {
        this.juridica = juridica;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
