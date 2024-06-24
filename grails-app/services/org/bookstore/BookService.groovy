package org.bookstore

import grails.gorm.transactions.Transactional


interface IBookService {

    Book get(Serializable id)

    List<Book> list(Map args)

    Long count()

    void delete(Serializable id)

    Book save(Book book)

}

@Transactional
class BookService implements IBookService {

    Book get(Serializable id) {
        return Book.get(id);
    }

    Book save(Book book) {
        book = book.save();
        return book;
    }

    List<Book> list(Map args) {
        Book[] books = [];

        books = Book.list();

        return books;
    }

    Long count() {
        Long countOfRecords;

        countOfRecords = Book.count();

        return countOfRecords;
    }

    void delete(Serializable id) {
        Book book = this.get(id);
        book.delete();
    }

}
