package com.project.todotodo.repository;

import com.project.todotodo.domain.NodeListDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeListRepository extends JpaRepository<NodeListDomain, Long> {
}
