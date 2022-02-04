package com.petsalone.core;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.persistence.metamodel.StaticMetamodel;

@Entity
public class My_Pet_Class extends AbstractEntity {

    // the name
    private String name;

    // missing since
    private LocalDateTime missingSince;

    // type
    // 1 = Cat, 2 = Dog, 3 = Hamster, 4 = Bird, 5 = Rabbit, 6 = Fish, 7 = Lizard, 8 = Horse, 9 = Gerbil, 10 = Tortoise
    private int petType;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPetType() {
        return petType;
    }

    public void setPetType(int petType) {
        this.petType = petType;
    }

    public LocalDateTime getMissingSince() {
        return missingSince;
    }

    public void setMissingSince(LocalDateTime missingSince) {
        this.missingSince = missingSince;
    }
}