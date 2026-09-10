package com.duotech.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idaccount")
    @JsonIgnore
    private Integer id;


    @Column(name = "account")
    private String account;


    @Column(name = "accountname")
    private String name;


    @Column(name = "creationdate")
    @JsonIgnore
    private String creationDate;

}
