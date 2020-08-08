package com.stoom.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@lombok.Setter
@lombok.Getter
@Entity
@Table(name = "Endereco")
@NamedQuery(name = "Endereco.findAll", query = "SELECT c FROM Endereco c")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @NotNull(message = "streetName cannot be null")
    public String streetName;
    @NotNull(message = "number cannot be null")
    public Integer number;
    @NotNull(message = "complement cannot be null")
    public Integer complement;
    @NotNull(message = "neighbourhood cannot be null")
    public String neighbourhood;
    @NotNull(message = "city cannot be null")
    public String city;
    @NotNull(message = "state cannot be null")
    public String state;
    @NotNull(message = "country cannot be null")
    public String country;
    @NotNull(message = "zipcode cannot be null")
    public int zipcode;

    public String latitude;
    public String longitude;

}
