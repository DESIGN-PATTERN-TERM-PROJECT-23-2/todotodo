package com.project.todotodo.repository;

import com.project.todotodo.domain.NodeDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeDomainRepository extends JpaRepository<NodeDomain, Long> {
}
