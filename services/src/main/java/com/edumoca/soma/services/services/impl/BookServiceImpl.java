package com.edumoca.soma.services.services.impl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.StudentDTO;
import com.edumoca.soma.entities.models.BookResponse;
import com.edumoca.soma.entities.dtos.BookDTO;
import com.edumoca.soma.entities.models.StudentResponse;
import com.edumoca.soma.entities.repositories.*;
import com.edumoca.soma.services.beans.LoadFile;
import com.edumoca.soma.services.fileUtils.FileOperationUtils;
import com.edumoca.soma.services.services.GridFSFileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.services.services.BookService;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService{
	private final BookRepository bookRepository;
	private final GradeRepository gradeRepository;
	private final SectionRepository sectionRepository;
	private final GradeSectionInstitutionYearMappingRepository gradeSectionInstitutionYearMappingRepository;
	private final SubjectRepository subjectRepository;
	private final InstitutionRepository institutionRepository;
	private final ModelMapper modelMapper;
	private final GridFSFileService gridFSFileService;

	@Override
	public BookDTO createBook(Book book) {
		return modelMapper.map(bookRepository.save(book),BookDTO.class);
	}

	@Override
	public BookDTO updateBook(Book book,Integer bookId) {
		Optional<BookResponse> book1 = Optional.ofNullable(bookRepository.getBookByBookId(bookId));
		if(book1.isPresent())
			book.setBookId(bookId);
		return modelMapper.map(bookRepository.save(book),BookDTO.class);
	}

//	public List<BookDTO> getAllBooksByInstitution(Integer InstitutionId){
//        return bookRepository.getAllBooksByInstitutionId(InstitutionId).stream().map(b->{
//			return modelMapper.map(b,BookDTO.class);
//		}).collect(Collectors.toList());
//	}
//	@Override
//	public BookDTO getBookByInstitutionAndBook(Integer institutionId,Integer bookId) {
//		Optional<BookResponse> bookResponse = Optional.ofNullable(bookRepository.getBookByInstitutionIdAndBookId(institutionId, bookId));
//		if(bookResponse.isPresent()) {
//			  return modelMapper.map(bookResponse.get(),BookDTO.class);
//		}else
//			throw new DataNotFoundException("Book with id not found.");
//	}

	@Override
	public List<BookDTO> getAllBooksByUser(Integer userId) {
		return bookRepository.getAllBooksByUserId(userId).stream().map(b->{
			BookDTO bookDTO = new BookDTO();
			try {
				LoadFile loadBook = gridFSFileService.downloadFile(b.getBookLocation());
				BeanUtils.copyProperties(b,bookDTO);
				bookDTO.setBook(loadBook.getFile());
				LoadFile loadCoverPage = gridFSFileService.downloadFile(b.getBookCoverPageLocation());
				bookDTO.setCoverPage(loadCoverPage.getFile());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			return bookDTO;
		}).collect(Collectors.toList());
	}

	@Override
	public Map<String, Set<BookDTO>> loadBooks(XSSFSheet booksSheet, String booksSheetName) {
		Map<String,Set<BookDTO>> booksMap = new HashMap<>();
		Set<BookDTO> booksSet = new HashSet<>();
		booksSheet.rowIterator().forEachRemaining(row->{
			if(row.getRowNum()>0){
				try {
				Book book = new Book();
				book.setBookName(row.getCell(0).getStringCellValue());
				book.setBookFormat(row.getCell(1).getStringCellValue());
				book.setBookLocation(generateUniqueIdForBook(row.getCell(2).getStringCellValue(),book));
				book.setBookCoverPageLocation(generateUniqueIdForBookCoverPage(row.getCell(3).getStringCellValue(),book));
				book.setHasDigitalCopy(row.getCell(4).getBooleanCellValue());
//				Optional<Grade> grade = gradeRepository.findById(new Double(row.getCell(5).getNumericCellValue()).intValue());
//				grade.ifPresent(book::setGrade);
//				Set<Section> sectionSetIds = new HashSet<>();
//				if(row.getCell(6).getCellType().equals(CellType.STRING)){
//					String sectionIds = row.getCell(6).getStringCellValue();
//					if(sectionIds.contains(",")){
//                       String[] sectionsArr = sectionIds.split(",");
//					   for(String sec : sectionsArr){
//						   Optional<Section> section = sectionRepository.findById(new Double(sec).intValue());
//						   section.ifPresent(sectionSetIds::add);
//					   }
//					}
//				}else{
//					Optional<Section> section = sectionRepository.findById(new Double(row.getCell(8).getNumericCellValue()).intValue());
//					section.ifPresent(sectionSetIds::add);
//				}
//				book.setSection(sectionSetIds);
                List<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMappingsIdList = new ArrayList<>();
                Optional<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(5).getNumericCellValue()).intValue());
				gradeSectionInstitutionYearMapping.ifPresent(gradeSectionInstitutionYearMappingsIdList::add);
				book.setGradeSectionInstitutionYearMapping(gradeSectionInstitutionYearMappingsIdList);

				Optional<Subject> subject = subjectRepository.findById(new Double(row.getCell(6).getNumericCellValue()).intValue());
				subject.ifPresent(book::setSubject);
//				Optional<Institution> institution = institutionRepository.findById(new Double(row.getCell(8).getNumericCellValue()).intValue());
//				institution.ifPresent(book::setInstitution);
                bookRepository.save(book);
				BookDTO bookDTO = new BookDTO();
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
		book.setBookLocation(gridFSFileService.uploadFile(uploadFilePath,metadata));
		return  book.getBookLocation();
	}

	private String generateUniqueIdForBookCoverPage(String cellValue,Book book) throws IOException {
		MultipartFile uploadFilePath = FileOperationUtils.convertFileToMultipartFile(cellValue);
		DBObject metadata = new BasicDBObject();
		metadata.put("Book Name",book.getBookName());
		metadata.put("CoverPage Name",uploadFilePath.getName());
		metadata.put("fileSize", uploadFilePath.getSize());
		book.setBookCoverPageLocation(gridFSFileService.uploadFile(uploadFilePath,metadata));
		return  book.getBookCoverPageLocation();
	}


	
	
}
