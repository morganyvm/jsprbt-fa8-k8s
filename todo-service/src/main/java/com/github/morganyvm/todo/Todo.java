package com.github.morganyvm.todo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Todo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    private String value;

    private boolean done;
}