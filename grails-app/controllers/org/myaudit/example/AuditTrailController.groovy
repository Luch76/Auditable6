package org.myaudit.example

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AuditTrailController {

    AuditTrailService auditTrailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond auditTrailService.list(params), model:[auditTrailCount: auditTrailService.count()]
    }

    def show(Long id) {
        respond auditTrailService.get(id)
    }

    def create() {
        respond new AuditTrail(params)
    }

    def save(AuditTrail auditTrail) {
        if (auditTrail == null) {
            notFound()
            return
        }

        try {
            auditTrailService.save(auditTrail)
        } catch (ValidationException e) {
            respond auditTrail.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auditTrail.label', default: 'AuditTrail'), auditTrail.id])
                redirect auditTrail
            }
            '*' { respond auditTrail, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond auditTrailService.get(id)
    }

    def update(AuditTrail auditTrail) {
        if (auditTrail == null) {
            notFound()
            return
        }

        try {
            auditTrailService.save(auditTrail)
        } catch (ValidationException e) {
            respond auditTrail.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'auditTrail.label', default: 'AuditTrail'), auditTrail.id])
                redirect auditTrail
            }
            '*'{ respond auditTrail, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        auditTrailService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'auditTrail.label', default: 'AuditTrail'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auditTrail.label', default: 'AuditTrail'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
