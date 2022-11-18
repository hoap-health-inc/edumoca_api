package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.dtos.BookDTO;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.dtos.ScheduleDTO;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
	BookDTO createBook(Book book);
	BookDTO updateBook(Book book,Integer bookId);
//	public List<BookDTO> getAllBooksByInstitution(Integer InstitutionId);
//	BookDTO getBookByInstitutionAndBook(Integer institutionId,Integer bookId);

	List<BookDTO> getAllBooksByUser(Integer userId);

	Map<String, Set<BookDTO>> loadBooks(XSSFSheet booksSheet, String booksSheetName);
}
