package com.edumoca.soma.entities.repositories;

import com.edumoca.soma.entities.models.BookResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.edumoca.soma.entities.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends PagingAndSortingRepository<Book, Integer>{

//    @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocation,b.bookCoverPageLocation) from Book b where b.institution.institutionId= :institutionId")
//    public List<BookResponse> getAllBooksByInstitutionId(@Param("institutionId") Integer institutionId);
//
//    @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocation,b.bookCoverPageLocation) from Book b where b.institution.institutionId= :institutionId and b.bookId= :bookId")
//    public BookResponse getBookByInstitutionIdAndBookId(@Param("institutionId") Integer institutionId, @Param("bookId") Integer bookId);

    @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocation,b.bookCoverPageLocation) from Book b where b.bookId= :bookId")
    public BookResponse getBookByBookId(@Param("bookId") Integer bookId);

   @Query("select new com.edumoca.soma.entities.models.BookResponse(b.bookId,b.bookName,b.subject.subjectName,b.hasDigitalCopy,b.bookFormat,b.bookLocation,b.bookCoverPageLocation) from Book b inner join b.gradeSectionInstitutionYearMapping gsiym inner join gsiym.students std where std.userId =:userId")
    public List<BookResponse> getAllBooksByUserId(@Param("userId") Integer userId);
}
