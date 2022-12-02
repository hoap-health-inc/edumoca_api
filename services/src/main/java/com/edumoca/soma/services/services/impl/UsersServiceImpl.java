package com.edumoca.soma.services.services.impl;

import com.edumoca.soma.entities.*;
import com.edumoca.soma.entities.dtos.AdminDto;
import com.edumoca.soma.entities.dtos.ParentDto;
import com.edumoca.soma.entities.dtos.StudentDto;
import com.edumoca.soma.entities.dtos.TeacherDto;
import com.edumoca.soma.entities.exception.DataNotFoundException;
import com.edumoca.soma.entities.models.AdminResponse;
import com.edumoca.soma.entities.models.ParentResponse;
import com.edumoca.soma.entities.models.StudentResponse;
import com.edumoca.soma.entities.models.TeacherResponse;
import com.edumoca.soma.entities.repositories.*;
import com.edumoca.soma.services.fileUtils.FileOperationUtils;
import com.edumoca.soma.services.services.ProfilePhotoService;
import com.edumoca.soma.services.services.UsersService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;
    private final HomeRoomTeacherRepository homeRoomTeacherRepository;
    private final SubjectHeadRepository subjectHeadRepository;
    private final PrincipalRepository principalRepository;
    private final GradeSectionInstitutionYearMapRepository gradeSectionInstitutionYearMappingRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final SchoolAdminRepository schoolAdminRepository;
    private final SuperAdminRepository superAdminRepository;
    private final ProfilePhotoService profilePhotoService;

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
    public SchoolAdmin registerSchoolAdmin(SchoolAdmin schoolAdmin){
        if(schoolAdmin.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(schoolAdmin.getPassword());
            schoolAdmin.setPassword(encrypted);
        }
        return schoolAdminRepository.save(schoolAdmin);
    }

    @Override
    public SuperAdmin registerSuperAdmin(SuperAdmin superAdmin){
        if(superAdmin.getUserId()==0) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted = encoder.encode(superAdmin.getPassword());
            superAdmin.setPassword(encrypted);
        }
        return superAdminRepository.save(superAdmin);
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
    public StudentDto showStudentDetails(String userName){
        StudentResponse studentResponse = studentRepository.getStudentByLoginId(userName);
        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(studentResponse,studentDto);
        studentDto.setFullName(studentResponse.getLastName().concat(" "+studentResponse.getFirstName().concat(" "+studentResponse.getMiddleName())));
        studentDto.setProfilePic(profilePhotoService.getProfilePhotoData(studentResponse.getProfilePic()).getPhoto().getData());
        return studentDto;
    }

    @Override
    public ParentDto showParentByLoginId(String userName){
        ParentResponse parentResponse = parentRepository.getParentByLoginId(userName);
        ParentDto parentDto = new ParentDto();
        BeanUtils.copyProperties(parentResponse,parentDto);
        parentDto.setProfilePic(profilePhotoService.getProfilePhotoData(parentResponse.getProfilePic()).getPhoto().getData());
        return  parentDto;
    }

    @Override
    public TeacherDto showTeacherDetails(String userName){
        return convertTeacherResponseToDto(teacherRepository.getTeacherByLoginId(userName));
    }

    @Override
    public TeacherDto showSubjectHeadDetails(String userName){
        return convertTeacherResponseToDto(subjectHeadRepository.getSubjectHeadByLoginId(userName));
    }

    @Override
    public TeacherDto showHomeRoomDetails(String userName){
        return convertTeacherResponseToDto(homeRoomTeacherRepository.getHomeRoomByLoginId(userName));
    }

    @Override
    public AdminDto showSchoolAdminByLoginId(String userName){
        return convertAdminResponseToDto(schoolAdminRepository.getSchoolAdminByLoginId(userName));
    }

    @Override
    public AdminDto showSuperAdminByLoginId(String userName){
        return convertAdminResponseToDto(superAdminRepository.getSuperAdminByLoginId(userName));
    }
    @Override
    public TeacherDto showPrincipalByLoginId(String userName){
        return convertTeacherResponseToDto(principalRepository.getPrincipalByLoginId(userName));
    }
    private TeacherDto convertTeacherResponseToDto(TeacherResponse teacherResponse){
        TeacherDto teacherDto = new TeacherDto();
        BeanUtils.copyProperties(teacherResponse,teacherDto);
        teacherDto.setFullName(teacherResponse.getLastName().concat(" "+teacherResponse.getFirstName().concat(" "+teacherResponse.getMiddleName())));
        teacherDto.setProfilePic(profilePhotoService.getProfilePhotoData(teacherResponse.getProfilePic()).getPhoto().getData());
        return teacherDto;
    }

    private AdminDto convertAdminResponseToDto(AdminResponse adminResponse){
        AdminDto adminDto = new AdminDto();
        BeanUtils.copyProperties(adminResponse,adminDto);
        adminDto.setFullName(adminResponse.getLastName().concat(" "+adminResponse.getFirstName().concat(" "+adminResponse.getMiddleName())));
        adminDto.setProfilePic(profilePhotoService.getProfilePhotoData(adminResponse.getProfilePic()).getPhoto().getData());
        return adminDto;
    }

    @Override
    public Map<String,Set<StudentDto>> loadStudents(XSSFSheet studentsSheet, String studentsSheetName) {
        Map<String,Set<StudentDto>> studentsMap = new HashMap<>();
        Set<StudentDto> studentsSet = new HashSet<>();
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
                    student.setDateOfBirth(row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    student.setGender(Gender.valueOf(row.getCell(5).getStringCellValue()));
                    try {
                        student.setProfilePic(profilePhotoService.uploadPhoto(row.getCell(2).getStringCellValue(),FileOperationUtils.convertFileToMultipartFile(row.getCell(6).getStringCellValue())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    student.setEmailAddress(row.getCell(7).getStringCellValue());
                    student.setCitizenShip(row.getCell(8).getStringCellValue());
                    student.setPresentAddress(row.getCell(9).getStringCellValue());
                    student.setPermanantAddress(row.getCell(10).getStringCellValue());
                    Optional<GradeSectionInstitutionYearMap> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                    gradeSectionInstitutionYearMapping.ifPresent(student::setGradeSectionInstitutionYearMap);
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
                    StudentDto studentDTO = new StudentDto();
                    studentDTO.setUserId(student.getUserId());
                    studentDTO.setLoginId(student.getLoginId());
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
    public Map<String, Set<TeacherDto>> loadTeachers(XSSFSheet teachersSheet, String teachersSheetName, Class<?> teacherType) {
        Map<String,Set<TeacherDto>> teachersMap = new HashMap<>();
        Set<TeacherDto> teachersSet = new HashSet<>();
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
                    teacher.setGender(Gender.valueOf(row.getCell(4).getStringCellValue()));
                    teacher.setEmailAddress(row.getCell(5).getStringCellValue());
                    teacher.setPresentAddress(row.getCell(6).getStringCellValue());
                    teacher.setPermanantAddress(row.getCell(7).getStringCellValue());
                    try {
                        teacher.setProfilePic(profilePhotoService.uploadPhoto(row.getCell(2).getStringCellValue(),FileOperationUtils.convertFileToMultipartFile(row.getCell(8).getStringCellValue())));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Optional<Role> role = roleRepository.findById(new Double(row.getCell(9).getNumericCellValue()).intValue());
                    role.ifPresent(roles::add);
                    teacher.setRoles(roles);
                    if(Teacher.class.getCanonicalName().equals(teacherType.getCanonicalName()) || SubjectHead.class.getCanonicalName().equals(teacherType.getCanonicalName()) || Principal.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        Optional<Department> department = departmentRepository.findById(new Double(row.getCell(10).getNumericCellValue()).intValue());
                        department.ifPresent(teacher::setDepartment);
                        teacher.setMotherTongue(row.getCell(11).getStringCellValue());
                    }else if(HomeRoomTeacher.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        Optional<GradeSectionInstitutionYearMap> gradeSectionInstitutionYearMapping = gradeSectionInstitutionYearMappingRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                        gradeSectionInstitutionYearMapping.ifPresent(gradeSectionInstitutionYearMapping1 -> ((HomeRoomTeacher) teacher).setGradeSectionInstitutionYearMap(gradeSectionInstitutionYearMapping1));
                        Optional<Department> department = departmentRepository.findById(new Double(row.getCell(11).getNumericCellValue()).intValue());
                        department.ifPresent(teacher::setDepartment);
                        teacher.setMotherTongue(row.getCell(12).getStringCellValue());
                    }else if(SchoolAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName()) || SuperAdmin.class.getCanonicalName().equals(teacherType.getCanonicalName())){
                        teacher.setMotherTongue(row.getCell(10).getStringCellValue());
                        noDeptFlag = true;
                    }
                    teacherRepository.save(teacher);
                    TeacherDto teacherDTO = new TeacherDto();
                    teacherDTO.setUserId(teacher.getUserId());
                    teacherDTO.setLoginId(teacher.getLoginId());
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
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        teachersMap.put(teachersSheetName,teachersSet);
        return teachersMap;
    }

    @Override
    public Map<String, Set<ParentDto>> loadParents(XSSFSheet parentsSheet, String parentsSheetName){
        Map<String,Set<ParentDto>> parentsMap = new HashMap<>();
        Set<ParentDto> parentsSet = new HashSet<>();
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

    private Set<ParentDto> wrapParentDataIntoDTO(Parent parent, Set<ParentDto> parentDTOSet){
        ParentDto parentDTO = new ParentDto();
        parentDTO.setUserId(parent.getUserId());
        parentDTO.setLoginId(parent.getLoginId());
        parentDTO.setOccupation(parent.getOccupation());
        parentDTO.setEmailAddress(parent.getEmailAddress());
        parentDTOSet.add(parentDTO);
        return parentDTOSet;
    }
    private Parent populateFatherDateIntoFatherObject(Row row){
        Parent father = new Parent();
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
            try {
                father.setProfilePic(profilePhotoService.uploadPhoto(row.getCell(2).getStringCellValue(),FileOperationUtils.convertFileToMultipartFile(row.getCell(6).getStringCellValue())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            father.setEmailAddress(row.getCell(7).getStringCellValue());
            father.setMotherTongue(row.getCell(8).getStringCellValue());
            father.setOccupation(row.getCell(9).getStringCellValue());
            father.setParentType(ParentType.valueOf(row.getCell(10).getStringCellValue()));
        return father;
    }

    private Parent populateMotherDataIntoMotherObject(Row row){
        Parent mother = new Parent();
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
            try {
                mother.setProfilePic(profilePhotoService.uploadPhoto(row.getCell(13).getStringCellValue(),FileOperationUtils.convertFileToMultipartFile(row.getCell(17).getStringCellValue())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mother.setEmailAddress(row.getCell(18).getStringCellValue());
            mother.setMotherTongue(row.getCell(19).getStringCellValue());
            mother.setOccupation(row.getCell(20).getStringCellValue());
        return mother;
    }

}
