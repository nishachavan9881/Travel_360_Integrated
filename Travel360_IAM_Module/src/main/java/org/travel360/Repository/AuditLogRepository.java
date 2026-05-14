package org.travel360.Repository;

import org.travel360.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.travel360.audit.EntityType;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByEntityType(EntityType entityType);
    List<AuditLog> findByUserUserId(Long userId);

}
