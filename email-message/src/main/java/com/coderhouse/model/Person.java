package com.coderhouse.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String fatherName;
    private String motherName;
    private String fullName;
    @ManyToOne(cascade = CascadeType.ALL)
    private DemographicInformation demographicInformation;
    @ManyToOne(cascade = CascadeType.ALL)
    private AddressInformation address;

}
