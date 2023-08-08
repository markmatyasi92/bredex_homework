package com.jobseeker.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Position entity.
 */
@Entity
@Data
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_sequence_generator")
    @SequenceGenerator(name = "position_sequence_generator", sequenceName = "position_sequence_generator", allocationSize = 1, initialValue = 100)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Length(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotNull
    @Length(max = 50)
    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @NotNull
    @Column(name = "url", nullable = false)
    private String url;
}

