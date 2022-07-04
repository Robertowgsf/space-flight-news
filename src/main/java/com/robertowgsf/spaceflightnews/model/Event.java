package com.robertowgsf.spaceflightnews.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Id
    private String id;
    private String provider;
}
