package de.woock.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.woock.infra.entity.Anfrage_;

public interface AnfragenRepository extends JpaRepository<Anfrage_, Long> {
}
