package com.jobseeker.repository;

import com.jobseeker.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByApiKey(UUID apiKey);
}

