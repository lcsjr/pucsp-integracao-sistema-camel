package br.com.study.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadRepository extends JpaRepository<Dead, Long> {
}
