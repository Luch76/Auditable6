package org.bookstore

import grails.plugins.orm.auditable.Auditable

class Book implements Auditable {
    Date dateCreated;
    Date lastUpdated;

    String title;

    static constraints = {
    }
}
