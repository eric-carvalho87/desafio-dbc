package br.com.desafio.infrastructure.repository;

import br.com.desafio.infrastructure.repository.entity.PollEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PollJpaRepository extends JpaRepository<PollEntity, UUID> {
}