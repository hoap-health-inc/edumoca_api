package com.edumoca.soma.services.services;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.dtos.BookBinaryDto;
import com.edumoca.soma.entities.dtos.BookDto;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {
	BookDto createBook(Book book);
	BookDto updateBook(Book book, Integer bookId);
	List<BookDto> getAllBooksByUser(Integer userId);
	BookBinaryDto getBookData(String bookLocationId) throws IOException;

	Map<String, Set<BookDto>> loadBooks(XSSFSheet booksSheet, String booksSheetName);
}
