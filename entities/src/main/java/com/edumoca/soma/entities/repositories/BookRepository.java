package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.models.BookResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer>{

    @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocationId,b.coverPage) from Book b where b.bookId= :bookId")
    BookResponse getBookByBookId(@Param("bookId") Integer bookId);

    @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocationId,b.coverPage) from Student std inner join std.gradeSectionInstitutionYearMap.gradeSectionInstitutionMap gsim inner join gsim.books b where std.userId= :userId")
    List<BookResponse> getAllBooksByUserId(@Param("userId") Integer userId);
}
