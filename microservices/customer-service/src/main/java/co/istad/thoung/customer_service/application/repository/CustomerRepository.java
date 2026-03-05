package co.istad.thoung.customer_service.application.repository;

import co.istad.thoung.customer_service.data.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsById(UUID customerId);

    List<CustomerEntity> customerId(UUID customerId);
}
