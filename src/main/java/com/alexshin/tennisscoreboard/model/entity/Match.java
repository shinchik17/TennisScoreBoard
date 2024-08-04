package com.alexshin.tennisscoreboard.model.entity;


import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matches", schema = "public")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2")
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner")
    private Player winner;


}
