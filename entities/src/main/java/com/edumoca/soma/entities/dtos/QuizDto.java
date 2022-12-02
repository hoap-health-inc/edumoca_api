package com.edumoca.soma.entities.dtos;

import com.edumoca.soma.entities.Schedule;
import com.edumoca.soma.entities.Student;
import com.edumoca.soma.entities.Subject;
import com.edumoca.soma.entities.documents.Instruction;
import com.edumoca.soma.entities.documents.Question;
import lombok.Data;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

@Data
public class QuizDto {
    private String quizId;
    private String title;
    List<Instruction> instructions;
//    private Duration duration;
    private Schedule schedule;
    private List<Question> questions;
//    private List<Student> students;
//    private LocalTime startTime;
//    private LocalTime endTime;
    private Subject subject;
    private int maxPoints;
//    private int negativePoints;
//    private boolean reusableQuiz;
//    private int createdTeacherId;
}
