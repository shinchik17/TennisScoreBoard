package com.alexshin.tennisscoreboard.model.entity;


import jakarta.persistence.*;
import lombok.*;

// TODO: есть инфа, что связка lombok @Data + JPA имеет траблы (из-за генерящегося в БД id вроде бы),
//  с equals и hashcode та же трабла, чекнуть потом
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players", schema = "public")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    public Player(String name) {
        this.name = name;
    }
}

