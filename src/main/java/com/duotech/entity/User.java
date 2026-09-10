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
@Table(name = "users")
public class User {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "iduser")
        @JsonIgnore
        private Integer id;

        @Column(name = "password")
        @JsonIgnore
        private String password;

        @Column(name = "active")
        private boolean active;

        // se agregaron las entidades
        @Column(name = "fullname")
        private String name;

        @Column(name = "memberships")
        private String memberships;

        @Column(name = "startdate")
        private String startdate;

        @Column(name = "enddate")
        private String enddate;

        @Column(name = "birthdate")
        private String birthdate;

        @Column(name = "mainemail")
        private String email;

        @Column(name = "additionalemail")
        private String additionalemail;

        @Column(name = "mainmobilephone")
        private String phone;

        @Column(name = "additionalphone")
        private String additionalphone;

        @Column(name = "privacynoticeaccepptance")
        private boolean policy;

        @Column(name = "acceptancetermsconditions")
        private boolean acceptpolicy;


        @ManyToOne
        @JoinColumn(name = "idaccount")
        @JsonIgnore
        private Account account;

        @Column(name = "creationdate")
        private String creationDate;



    }

