package co.istad.thoung.customer_service.application.repository;

import co.istad.thoung.customer_service.data.entity.CustomerSegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerSegmentRepository extends JpaRepository<CustomerSegmentEntity, UUID> {

}
