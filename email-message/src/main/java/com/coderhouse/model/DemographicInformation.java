package com.coderhouse.model;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "demographic")
public class DemographicInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String birthDate;
    private String birthPlace;
    private String gender;
    private String nationality;
    private String maritalStatus;
    private String housingType;
}
