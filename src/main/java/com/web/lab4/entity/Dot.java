package com.web.lab4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.lab4.dto.DotDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dot {

    @Id
    @SequenceGenerator(name = "dot_sequence", sequenceName = "dot_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dot_sequence")
    private int id;

    @NotNull
    private double x;

    @NotNull
    private double y;

    @NotNull
    private double r;

    @NotNull
    private char result;

    @Column(name = "execution_date")
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JsonBackReference
    @JsonIgnore
    private User user;

    public Dot(double x, double y, double r, char result, String date, User user) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.date = date;
        this.user = user;
    }

    public Dot (DotDto dto) {
        this.x = dto.getX();
        this.y = dto.getY();
        this.r = dto.getR();
    }

}
