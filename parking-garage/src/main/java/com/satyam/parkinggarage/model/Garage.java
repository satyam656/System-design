package com.satyam.parkinggarage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "garage")
public class Garage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "garagename")
    private String garageName;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "compactcapacity")
    private int compactCapacity;

    @Column(name = "compactrate")
    private float compactRate;

    @Column(name = "regularcapacity")
    private int regularCapacity;

    @Column(name = "regularrate")
    private float regularRate;

    @Column(name = "largecapacity")
    private int largeCapacity;

    @Column(name = "largerate")
    private float largeRate;

}
