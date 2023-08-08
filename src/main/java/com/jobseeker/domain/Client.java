package com.jobseeker.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * Client entity.
 */
@Entity
@Data
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence_generator")
    @SequenceGenerator(name = "client_sequence_generator", sequenceName = "client_sequence_generator", allocationSize = 1, initialValue = 100)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Length(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "api_key", nullable = false)
    private UUID apiKey;
}

