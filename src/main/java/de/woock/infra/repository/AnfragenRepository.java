package de.woock.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.woock.infra.entity.AnfrageEntity;

public interface AnfragenRepository extends JpaRepository<AnfrageEntity, Long> {
}
