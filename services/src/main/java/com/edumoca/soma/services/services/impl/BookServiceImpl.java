package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.Book;
import com.edumoca.soma.entities.GradeSectionInstitutionMap;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.dtos.BookBinaryDto;
import com.edumoca.soma.entities.dtos.BookDto;
import com.edumoca.soma.entities.models.BookResponse;
import com.edumoca.soma.entities.repositories.*;
import com.edumoca.soma.services.beans.LoadFile;
import com.edumoca.soma.services.fileUtils.FileOperationUtils;
import com.edumoca.soma.services.services.BookService;
import com.edumoca.soma.services.services.GridFSFileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
	private final BookRepository bookRepository;
	private final GradeSectionInstitutionMapRepository gradeSectionInstitutionMapRepository;
	private final SubjectRepository subjectRepository;
	private final ModelMapper modelMapper;
	private final GridFSFileService gridFSFileService;

	@Override
	public BookDto createBook(Book book) {
		return modelMapper.map(bookRepository.save(book), BookDto.class);
	}

	@Override
	public BookDto updateBook(Book book, Integer bookId) {
		Optional<BookResponse> book1 = Optional.ofNullable(bookRepository.getBookByBookId(bookId));
		if(book1.isPresent())
			book.setBookId(bookId);
		return modelMapper.map(bookRepository.save(book), BookDto.class);
	}


	@Override
	public List<BookDto> getAllBooksByUser(Integer userId) {
		return bookRepository.getAllBooksByUserId(userId).stream().map(b->{
			BookDto bookDto = new BookDto();
			try {
				BeanUtils.copyProperties(b,bookDto);
				LoadFile loadCoverPage = gridFSFileService.downloadFile(b.getCoverPage());
				bookDto.setCoverPage(loadCoverPage.getFile());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return bookDto;
		}).collect(Collectors.toList());
	}
	@Override
	public BookBinaryDto getBookData(String bookLocationId) throws IOException {
		BookBinaryDto bookBinaryDto = new BookBinaryDto();
		bookBinaryDto.setBookData(gridFSFileService.downloadFile(bookLocationId).getFile());
		return bookBinaryDto;
	}

	@Override
	public Map<String, Set<BookDto>> loadBooks(XSSFSheet booksSheet, String booksSheetName) {
		Map<String,Set<BookDto>> booksMap = new HashMap<>();
		Set<BookDto> booksSet = new HashSet<>();
		booksSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				try {
				Book book = new Book();
				book.setBookName(row.getCell(0).getStringCellValue());
				book.setBookFormat(row.getCell(1).getStringCellValue());
				book.setBookLocationId(generateUniqueIdForBook(row.getCell(2).getStringCellValue(),book));
				book.setCoverPage(generateUniqueIdForBookCoverPage(row.getCell(3).getStringCellValue(),book));
				book.setHasDigitalCopy(row.getCell(4).getBooleanCellValue());
                Optional<GradeSectionInstitutionMap> gradeSectionInstitutionMap = gradeSectionInstitutionMapRepository.findById(new Double(row.getCell(5).getNumericCellValue()).intValue());
				gradeSectionInstitutionMap.ifPresent(book::setGradeSectionInstitutionMaps);

				Optional<Subject> subject = subjectRepository.findById(new Double(row.getCell(6).getNumericCellValue()).intValue());
				subject.ifPresent(book::setSubject);
                bookRepository.save(book);
				BookDto bookDTO = new BookDto();
				bookDTO.setBookId(book.getBookId());
				bookDTO.setBookName(book.getBookName());
				bookDTO.setBookFormat(book.getBookFormat());
				bookDTO.setHasDigitalCopy(book.isHasDigitalCopy());
				booksSet.add(bookDTO);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
		booksMap.put(booksSheetName,booksSet);
		return booksMap;
	}
	private String generateUniqueIdForBook(String cellValue, Book book) throws IOException {
		MultipartFile uploadFilePath = FileOperationUtils.convertFileToMultipartFile(cellValue);
		DBObject metadata = new BasicDBObject();
		metadata.put("Book Name",book.getBookName());
		metadata.put("fileSize", uploadFilePath.getSize());
		book.setBookLocationId(gridFSFileService.uploadFile(uploadFilePath,metadata));
		return  book.getBookLocationId();
	}

	private String generateUniqueIdForBookCoverPage(String cellValue,Book book) throws IOException {
		MultipartFile uploadFilePath = FileOperationUtils.convertFileToMultipartFile(cellValue);
		DBObject metadata = new BasicDBObject();
		metadata.put("Book Name",book.getBookName());
		metadata.put("CoverPage Name",uploadFilePath.getName());
		metadata.put("fileSize", uploadFilePath.getSize());
		book.setCoverPage(gridFSFileService.uploadFile(uploadFilePath,metadata));
		return  book.getCoverPage();
	}


	
	
}
