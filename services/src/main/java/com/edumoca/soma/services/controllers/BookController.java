package com.edumoca.soma.services.controllers;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.dtos.BookDTO;
import com.edumoca.soma.services.beans.LoadFile;
import com.edumoca.soma.services.services.BookService;
import com.edumoca.soma.services.services.GridFSFileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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
	private final ModelMapper modelMapper;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<BookDTO> createBook(@RequestPart("book") String bookJson,@RequestPart("file") MultipartFile uploadFile) throws IOException {
		return new ResponseEntity<>(bookService.createBook(uploadBookGFS(bookJson,uploadFile)), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{bookId}",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<BookDTO> updateBook(@RequestPart("book") String bookJson,@RequestPart("file") MultipartFile uploadFile, @PathVariable("bookId") Integer bookId) throws IOException {
		return new ResponseEntity<>(bookService.updateBook(uploadBookGFS(bookJson,uploadFile),bookId),HttpStatus.OK);
	}

//	@GetMapping(value = "/{institutionId}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<List<BookDTO>> getAllBooksByInstitution(@PathVariable("institutionId") Integer institutionId){
//		return new ResponseEntity<>(bookService.getAllBooksByInstitution(institutionId),HttpStatus.OK);
//	}
//
//	@GetMapping(value = "/{institutionId}/{bookId}",produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<BookDTO> getBookByInstitutionAndBook(@PathVariable("institutionId") Integer institutionId, @PathVariable("bookId") Integer bookId) {
//		return new ResponseEntity<>(bookService.getBookByInstitutionAndBook(institutionId,bookId),HttpStatus.OK);
//	}

	@GetMapping(value = "/{userId}/allBooks",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BookDTO>> getAllBooksByUser(@PathVariable("userId") Integer userId){
		return new ResponseEntity<>(bookService.getAllBooksByUser(userId),HttpStatus.OK);
	}
	
//	@GetMapping(value = "/{institutionId}/{bookLocId}/download")
//	public ResponseEntity<ByteArrayResource> downloadBook(@PathVariable("institutionId") Integer institutionId,@PathVariable("bookLocId") String id) throws IOException {
//		LoadFile loadFile = gridFSFileService.downloadFile(id);
//		return ResponseEntity.ok().contentType(MediaType.parseMediaType(loadFile.getFileType()))
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+loadFile.getFilename()+"\"")
//				.body(new ByteArrayResource(loadFile.getFile()));
//	}

	private Book uploadBookGFS(String bookJson,MultipartFile uploadFile) throws IOException {
		Book book = new ObjectMapper().readValue(bookJson,Book.class);
		DBObject metadata = new BasicDBObject();
		metadata.put("bookName",book.getBookName());
		metadata.put("fileSize", uploadFile.getSize());
		metadata.put("subject",book.getSubject().getSubjectName());
		book.setBookLocation(gridFSFileService.uploadFile(uploadFile,metadata));
		return book;
	}
	
}
