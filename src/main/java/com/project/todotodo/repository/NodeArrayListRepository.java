package com.project.todotodo.repository;

import com.project.todotodo.domain.NodeArrayListDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeArrayListRepository extends JpaRepository<NodeArrayListDomain, Long> {
}
