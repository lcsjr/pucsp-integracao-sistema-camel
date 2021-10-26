package br.com.study.repository;

import br.com.study.model.Dead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeadRepository extends JpaRepository<Dead, Long> {
}
