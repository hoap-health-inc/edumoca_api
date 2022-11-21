package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.dtos.BookBinaryDto;
import com.edumoca.soma.entities.dtos.BookDto;
import com.edumoca.soma.services.services.BookService;
import com.edumoca.soma.services.services.GridFSFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/book")
@AllArgsConstructor
public class BookController {

	private final BookService bookService;
	private final GridFSFileService gridFSFileService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<BookDto> createBook(@RequestPart("book") String bookJson, @RequestPart("file") MultipartFile uploadFile) throws IOException {
		return new ResponseEntity<>(bookService.createBook(uploadBookGFS(bookJson,uploadFile)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{bookId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<BookDto> updateBook(@RequestPart("book") String bookJson, @RequestPart("file") MultipartFile uploadFile, @PathVariable("bookId") Integer bookId) throws IOException {
		return new ResponseEntity<>(bookService.updateBook(uploadBookGFS(bookJson,uploadFile),bookId),HttpStatus.OK);
	}

	@GetMapping(value = "/{userId}/allBooks",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDto>> getAllBooksByUser(@PathVariable("userId") Integer userId){
		return new ResponseEntity<>(bookService.getAllBooksByUser(userId),HttpStatus.OK);
	}

	@GetMapping(value = "/{bookLocationId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BookBinaryDto> getBookBinaryData(@PathVariable("bookLocationId") String bookLocationId) throws IOException {
		return new ResponseEntity<>(bookService.getBookData(bookLocationId),HttpStatus.OK);
	}
	
	private Book uploadBookGFS(String bookJson,MultipartFile uploadFile) throws IOException {
		Book book = new ObjectMapper().readValue(bookJson,Book.class);
		DBObject metadata = new BasicDBObject();
		metadata.put("bookName",book.getBookName());
		metadata.put("fileSize", uploadFile.getSize());
		metadata.put("subject",book.getSubject().getSubjectName());
		book.setBookLocationId(gridFSFileService.uploadFile(uploadFile,metadata));
		return book;

	}
}
