package org.myaudit.example

import grails.gorm.services.Service

@Service(AuditTrail)
interface AuditTrailService {

    AuditTrail get(Serializable id)

    List<AuditTrail> list(Map args)

    Long count()

    void delete(Serializable id)

    AuditTrail save(AuditTrail auditTrail)

}