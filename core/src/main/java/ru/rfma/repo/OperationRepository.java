package ru.rfma.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rfma.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Integer> {
}
