package com.edumoca.soma.services.services.impl;

import java.io.*;
import java.time.ZoneId;
import java.util.*;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.*;
import com.edumoca.soma.entities.models.*;
import com.edumoca.soma.entities.repositories.*;
import com.edumoca.soma.services.beans.LoadFile;
import com.edumoca.soma.services.fileUtils.FileOperationUtils;
import com.edumoca.soma.services.services.GridFSFileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.services.services.UsersService;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;
    private final HomeRoomTeacherRepository homeRoomTeacherRepository;
    private final SubjectHeadRepository subjectHeadRepository;
    private final PrincipalRepository principalRepository;
    private final GradeSectionInstitutionYearMappingRepository gradeSectionInstitutionYearMappingRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final SchoolAdminRepository schoolAdminRepository;
    private final SuperAdminRepository superAdminRepository;

    private final GridFSFileService gridFSFileService;

    @Override
    public Student registerStudent(Student student) {
        if(student.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(student.getPassword());
            student.setPassword(encrypted);
        }
        return studentRepository.save(student);
    }
    
    @Override
    public Teacher registerTeacher(Teacher teacher) {
        if(teacher.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(teacher.getPassword());
            teacher.setPassword(encrypted);
        }
        return teacherRepository.save(teacher);
    }

    @Override
    public Parent registerParent(Parent parent) {
        if(parent.getStudents()==null || parent.getStudents().size()==0){
            throw new RuntimeException("Please send Student details of the Parent.");
        }
        if(parent.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(parent.getPassword());
            parent.setPassword(encrypted);
        }
        return parentRepository.save(parent);
    }

    @Override
	public HomeRoomTeacher registerHomeRoomTeacher(HomeRoomTeacher homeRoomTeacher) {
        if(homeRoomTeacher.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(homeRoomTeacher.getPassword());
            homeRoomTeacher.setPassword(encrypted);
        }
        return homeRoomTeacherRepository.save(homeRoomTeacher);
	}

	@Override
	public SubjectHead registerSubjectHead(SubjectHead subjectHead) {
        if(subjectHead.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(subjectHead.getPassword());
            subjectHead.setPassword(encrypted);
        }
        return subjectHeadRepository.save(subjectHead);
	}

	@Override
	public Principal registerPrincipal(Principal principal) {
        if(principal.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(principal.getPassword());
            principal.setPassword(encrypted);
        }
        return principalRepository.save(principal);
	}

	@Override
    public Parent getParentById(Integer id) {
        Optional<Parent> parent = parentRepository.findById(id);
        if(parent.isPresent())
            return parent.get();
        else
            throw new DataNotFoundException("Parent with id not found.");
    }

	@Override
	public Student getStudentById(Integer studentId) {
		Optional<Student> student = studentRepository.findById(studentId);
		if(student.isPresent()) 
			return student.get();
		else
		  throw new DataNotFoundException("Student with id not found");
	}

    @Override
    public StudentDTO showStudentDetails(String userName) throws IOException {
        StudentResponse studentResponse = studentRepository.getStudentByLoginId(userName);
        LoadFile loadFile = gridFSFileService.downloadFile(studentResponse.getProfilePic());
        StudentDTO studentDTO = new StudentDTO();
        BeanUtils.copyProperties(studentResponse,studentDTO);
        studentDTO.setFullName(studentResponse.getLastName().concat(" "+studentResponse.getFirstName().concat(" "+studentResponse.getMiddleName())));
        studentDTO.setProfilePic(loadFile.getFile());
        return studentDTO;
    }

    @Override
    public TeacherDTO showTeacherDetails(String userName) throws IOException {
        return convertTeacherResponseToDTO(teacherRepository.getTeacherByLoginId(userName));
    }

    @Override
    public TeacherDTO showSubjectHeadDetails(String userName) throws IOException {
        return convertTeacherResponseToDTO(subjectHeadRepository.getSubjectHeadByLoginId(userName));
    }

    @Override
    public TeacherDTO showHomeRoomDetails(String userName) throws IOException {
        return convertTeacherResponseToDTO(homeRoomTeacherRepository.getHomeRoomByLoginId(userName));
    }

    @Override
    public TeacherDTO showSchoolAdminByLoginId(String userName) throws IOException {
        SchoolAdminResponse schoolAdminResponse = schoolAdminRepository.getSchoolAdminByLoginId(userName);
        LoadFile loadFile = gridFSFileService.downloadFile(schoolAdminResponse.getProfilePic());
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(schoolAdminResponse,teacherDTO);
        teacherDTO.setFullName(schoolAdminResponse.getLastName().concat(" "+schoolAdminResponse.getFirstName().concat(" "+schoolAdminResponse.getMiddleName())));
        teacherDTO.setProfilePic(loadFile.getFile());
        return teacherDTO;
    }

    @Override
    public TeacherDTO showSuperAdminByLoginId(String userName) throws IOException {
        SuperAdminResponse superAdminResponse = superAdminRepository.getSuperAdminByLoginId(userName);
        LoadFile loadFile = gridFSFileService.downloadFile(superAdminResponse.getProfilePic());
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(superAdminResponse,teacherDTO);
        teacherDTO.setFullName(superAdminResponse.getLastName().concat(" "+superAdminResponse.getFirstName().concat(" "+superAdminResponse.getMiddleName())));
        teacherDTO.setProfilePic(loadFile.getFile());
        return teacherDTO;
    }

    @Override
    public ParentDTO showParentByLoginId(String userName) throws IOException{
        ParentResponse parentResponse = parentRepository.getParentByLoginId(userName);
        LoadFile loadFile = gridFSFileService.downloadFile(parentResponse.getProfilePic());
        ParentDTO parentDTO = new ParentDTO();
        BeanUtils.copyProperties(parentResponse,parentDTO);
        parentDTO.setProfilePic(loadFile.getFile());
        return  parentDTO;
    }

    @Override
    public TeacherDTO showPrincipalByLoginId(String userName) throws IOException{
        return convertTeacherResponseToDTO(principalRepository.getPrincipalByLoginId(userName));
    }

    private TeacherDTO convertTeacherResponseToDTO(TeacherResponse teacherResponse) throws IOException {
        LoadFile loadFile = gridFSFileService.downloadFile(teacherResponse.getProfilePic());
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(teacherResponse,teacherDTO);
        teacherDTO.setFullName(teacherResponse.getLastName().concat(" "+teacherResponse.getFirstName().concat(" "+teacherResponse.getMiddleName())));
        teacherDTO.setProfilePic(loadFile.getFile());
        return teacherDTO;
    }

    @Override
    public Map<String,Set<StudentDTO>> loadStudents(XSSFSheet studentsSheet, String studentsSheetName) {
        Map<String,Set<StudentDTO>> studentsMap = new HashMap<>();
        Set<StudentDTO> studentsSet = new HashSet<>();
        Set<Role> roles = new HashSet<>();
            studentsSheet.rowIterator().forEachRemaining(row -> {
                if (row.getRowNum() > 0) {
                    Student student = new Student();
                    student.setLoginId(row.getCell(0).getStringCellValue());
                    student.setPassword(new BCryptPasswordEncoder().encode(row.getCell(1).getStringCellValue()));
                    student.setFirstName(row.getCell(2).getStringCellValue());

                    if (Optional.ofNullable(row.getCell(3)).isPresent()) {
                        student.setLastName(row.getCell(3).getStringCellValue());
                    } else {
                        student.setLastName("");
                    }

                    student.setMiddleName("");

                //    student.setFullName(student.getLastName().concat(student.getFirstName().concat(student.getMiddleName())));

                    student.setDateOfBirth(row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    student.setGender(Gender.valueOf(row.getCell(5).getStringCellValue()));
                    //generate gridfs id based on fileuploaded
                    try {
                        student.setProfilePic(generateUniqueIdForUser(row.getCell(6).getStringCellValue(), student));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    student.setEmailAddress(row.getCell(7).getStringCellValue());
                    student.setCitizenShip(row.getCell(8).getStringCellValue());
                    student.setPresentAddress(row.getCell(9).getStringCellValue());
                    student.setPermanantAddress(row.getCell(10).getStringCellValue());
                    Optional<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                    gradeSectionInstitutionYearMapping.ifPresent(student::setGradeSectionInstitutionYearMapping);
                    student.setLrn(String.valueOf(row.getCell(12).getNumericCellValue()));
                    student.setPsaRegNumber(String.valueOf(row.getCell(13).getNumericCellValue()));
                    student.setReligion(row.getCell(14).getStringCellValue());
                    student.setPlaceOfBirth(row.getCell(15).getStringCellValue());
                    student.setSchoolLastAttended(row.getCell(16).getStringCellValue());
                    student.setMotherTongue(row.getCell(17).getStringCellValue());
                    Optional<Role> role = roleRepository.findById(new Double(row.getCell(18).getNumericCellValue()).intValue());
                    role.ifPresent(roles::add);
                    student.setRoles(roles);
                    studentRepository.save(student);
                    StudentDTO studentDTO = new StudentDTO();
                    studentDTO.setUserId(student.getUserId());
                    studentDTO.setLoginId(student.getLoginId());
                   // studentDTO.setFullName(student.getFullName());
                    studentDTO.setGender(student.getGender());
                    studentDTO.setDateOfBirth(student.getDateOfBirth());
                    studentDTO.setLrn(student.getLrn());
                    studentDTO.setPsaRegNumber(student.getPsaRegNumber());
                    studentsSet.add(studentDTO);
                }
            });
            studentsMap.put(studentsSheetName, studentsSet);
        return studentsMap;
    }
    @Override
    public Map<String, Set<TeacherDTO>> loadTeachers(XSSFSheet teachersSheet, String teachersSheetName,Class<?> teacherType) {
        Map<String,Set<TeacherDTO>> teachersMap = new HashMap<>();
        Set<TeacherDTO> teachersSet = new HashSet<>();
        Set<Role> roles = new HashSet<>();

        teachersSheet.rowIterator().forEachRemaining(row->{
            boolean noDeptFlag = false;
            if(row.getRowNum()>0){
                try {
                    Teacher teacher = (Teacher) teacherType.newInstance();
                    teacher.setLoginId(row.getCell(0).getStringCellValue());
                    teacher.setPassword(new BCryptPasswordEncoder().encode(row.getCell(1).getStringCellValue()));
                    teacher.setFirstName(row.getCell(2).getStringCellValue());

                    if(Optional.ofNullable(row.getCell(3)).isPresent()) {
                        teacher.setLastName(row.getCell(3).getStringCellValue());
                    }else{
                        teacher.setLastName("");
                    }

                    teacher.setMiddleName("");
                    //teacher.setFullName(teacher.getLastName().concat(teacher.getFirstName().concat(teacher.getMiddleName())));

                    teacher.setGender(Gender.valueOf(row.getCell(4).getStringCellValue()));
                    teacher.setEmailAddress(row.getCell(5).getStringCellValue());
                    teacher.setPresentAddress(row.getCell(6).getStringCellValue());
                    teacher.setPermanantAddress(row.getCell(7).getStringCellValue());

                    //generate gridfs id based on fileuploaded
                    teacher.setProfilePic(generateUniqueIdForUser(row.getCell(8).getStringCellValue(),teacher));

                    //teacher.setProfilePic(row.getCell(8).getStringCellValue());
                    Optional<Role> role = roleRepository.findById(new Double(row.getCell(9).getNumericCellValue()).intValue());
                    role.ifPresent(roles::add);
                    teacher.setRoles(roles);
                    if(Teacher.class.getCanonicalName().equals(teacherType.getCanonicalName()) || SubjectHead.class.getCanonicalName().equals(teacherType.getCanonicalName()) || Principal.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        Optional<Department> department = departmentRepository.findById(new Double(row.getCell(10).getNumericCellValue()).intValue());
                        department.ifPresent(teacher::setDepartment);
                        teacher.setMotherTongue(row.getCell(11).getStringCellValue());
                    }else if(HomeRoomTeacher.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        Optional<GradeSectionInstitutionYearMapping> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                        gradeSectionInstitutionYearMapping.ifPresent(gradeSectionInstitutionYearMapping1 -> ((HomeRoomTeacher) teacher).setGradeSectionInstitutionYearMapping(gradeSectionInstitutionYearMapping1));
                        Optional<Department> department = departmentRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                        department.ifPresent(teacher::setDepartment);
                        teacher.setMotherTongue(row.getCell(12).getStringCellValue());
                    }else if(SchoolAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName()) || SuperAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        teacher.setMotherTongue(row.getCell(10).getStringCellValue());
                        noDeptFlag = true;
                    }
                    teacherRepository.save(teacher);
                    TeacherDTO teacherDTO = new TeacherDTO();
                    teacherDTO.setUserId(teacher.getUserId());
                    teacherDTO.setLoginId(teacher.getLoginId());
                   // teacherDTO.setFullName(teacher.getFullName());
                    teacherDTO.setGender(teacher.getGender());
                    System.out.println(SchoolAdmin.class.getCanonicalName());
                    System.out.println(teacherType.getCanonicalName());
                    System.out.println(SchoolAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName()));
                    System.out.println(!SchoolAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName()));
                    System.out.println(!(SchoolAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName())));
                    if(!noDeptFlag)
                        teacherDTO.setDepartmentName(teacher.getDepartment().getDeptName());
                    teacherDTO.setEmailAddress(teacher.getEmailAddress());
                    teachersSet.add(teacherDTO);
                } catch (InstantiationException | IllegalAccessException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        teachersMap.put(teachersSheetName,teachersSet);
        return teachersMap;
    }

    @Override
    public Map<String, Set<ParentDTO>> loadParents(XSSFSheet parentsSheet,String parentsSheetName){
        Map<String,Set<ParentDTO>> parentsMap = new HashMap<>();
        Set<ParentDTO> parentsSet = new HashSet<>();
        Set<Role> roles = new HashSet<>();
        parentsSheet.rowIterator().forEachRemaining(row-> {
            if (row.getRowNum() > 0) {
                Parent father = populateFatherDateIntoFatherObject(row);
                Parent mother = populateMotherDataIntoMotherObject(row);
                Set<Student> studentSetIds = new HashSet<>();
                if(row.getCell(21).getCellType().equals(CellType.STRING)){
                    String studentIds = row.getCell(21).getStringCellValue();
                    if(studentIds.contains(",")){
                        String[] studentIdArr = studentIds.split(",");
                        for (String s : studentIdArr) {
                            Optional<Student> student = studentRepository.findById(new Double(s).intValue());
                            student.ifPresent(studentSetIds::add);
                        }
                    }
                }else{
                    Optional<Student> student = studentRepository.findById(new Double(row.getCell(21).getNumericCellValue()).intValue());
                    student.ifPresent(studentSetIds::add);
                }
                Optional<Role> role = roleRepository.findById(new Double(row.getCell(22).getNumericCellValue()).intValue());
                role.ifPresent(roles::add);
                father.setRoles(roles);
                mother.setRoles(roles);
               father.setStudents(studentSetIds);
               mother.setStudents(studentSetIds);
               parentRepository.save(father);
               parentRepository.save(mother);
                wrapParentDataIntoDTO(father,parentsSet);
                wrapParentDataIntoDTO(mother,parentsSet);
            }
        });
        parentsMap.put(parentsSheetName,parentsSet);
        return parentsMap;
    }

    private Set<ParentDTO> wrapParentDataIntoDTO(Parent parent,Set<ParentDTO> parentDTOSet){
        ParentDTO parentDTO = new ParentDTO();
        parentDTO.setUserId(parent.getUserId());
        parentDTO.setLoginId(parent.getLoginId());
       // parentDTO.setFullName(parent.getFullName());
        parentDTO.setOccupation(parent.getOccupation());
        parentDTO.setEmailAddress(parent.getEmailAddress());
        parentDTOSet.add(parentDTO);
        return parentDTOSet;
    }
    private Parent populateFatherDateIntoFatherObject(Row row){
        Parent father = new Parent();
        try{
            father.setLoginId(row.getCell(0).getStringCellValue());
            father.setPassword(new BCryptPasswordEncoder().encode(row.getCell(1).getStringCellValue()));
            father.setFirstName(row.getCell(2).getStringCellValue());
            if(Optional.ofNullable(row.getCell(3)).isPresent()) {
                father.setMiddleName(row.getCell(3).getStringCellValue());
            }else{
                father.setMiddleName("");
            }
            father.setLastName(row.getCell(4).getStringCellValue());
            father.setGender(Gender.valueOf(row.getCell(5).getStringCellValue()));
          //  father.setFullName(father.getLastName().concat(father.getFirstName().concat(father.getMiddleName())));
            //generate gridfs id based on fileuploaded
            father.setProfilePic(generateUniqueIdForUser(row.getCell(6).getStringCellValue(),father));
            father.setEmailAddress(row.getCell(7).getStringCellValue());
            father.setMotherTongue(row.getCell(8).getStringCellValue());
            father.setOccupation(row.getCell(9).getStringCellValue());
            father.setParentType(ParentType.valueOf(row.getCell(10).getStringCellValue()));
        }catch(IOException e){
            e.printStackTrace();
        }
        return father;
    }

    private Parent populateMotherDataIntoMotherObject(Row row){
        Parent mother = new Parent();
        try{
            mother.setLoginId(row.getCell(11).getStringCellValue());
            mother.setPassword(new BCryptPasswordEncoder().encode(row.getCell(12).getStringCellValue()));
            mother.setFirstName(row.getCell(13).getStringCellValue());
            if(Optional.ofNullable(row.getCell(14)).isPresent()) {
                mother.setMiddleName(row.getCell(14).getStringCellValue());
            }else{
                mother.setMiddleName("");
            }
            mother.setLastName(row.getCell(15).getStringCellValue());
            mother.setGender(Gender.valueOf(row.getCell(16).getStringCellValue()));
            //generate gridfs id based on fileuploaded
            mother.setProfilePic(generateUniqueIdForUser(row.getCell(17).getStringCellValue(),mother));
            mother.setEmailAddress(row.getCell(18).getStringCellValue());
            mother.setMotherTongue(row.getCell(19).getStringCellValue());
            mother.setOccupation(row.getCell(20).getStringCellValue());
        }catch(IOException e){
            e.printStackTrace();
        }
        return mother;
    }

    private String generateUniqueIdForUser(String cellValue,AppUser appUser) throws IOException {
        MultipartFile uploadFilePath = FileOperationUtils.convertFileToMultipartFile(cellValue);
        DBObject metadata = new BasicDBObject();
        metadata.put("User Name",appUser.getFullName());
        metadata.put("fileSize", uploadFilePath.getSize());
        appUser.setProfilePic(gridFSFileService.uploadFile(uploadFilePath,metadata));
        return  appUser.getProfilePic();
    }
}
