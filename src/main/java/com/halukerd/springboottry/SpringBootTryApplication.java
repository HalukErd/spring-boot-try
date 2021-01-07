package com.halukerd.springboottry;

import com.github.javafaker.Faker;
import com.halukerd.springboottry.model.*;
import com.halukerd.springboottry.repository.StudentIdCardRepository;
import com.halukerd.springboottry.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class SpringBootTryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTryApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {
//


//            System.out.println(studentRepository.count());
//
//            studentRepository
//                    .findById(2L)
//                    .ifPresentOrElse(
//                            System.out::println, () -> System.out.println("student with id 2 not found")
//                    );
//
//            System.out.println("Select all students");
//            studentRepository.findAll().forEach(System.out::println);
//            studentRepository.getStudentByFirst_nameAndAgeGreater(
//                    "haluk", 20)
//                    .forEach(System.out::println);
//            generateRandomStudents(studentRepository, 5);

//            student.addBook(new Book(
//                    "clean code", LocalDateTime.now().minusDays(4)
//            ));
//            student.addBook(new Book(
//                    "spring data jpa", LocalDateTime.now()
//            ));
//            student.addBook(new Book(
//                    "think and grow rich", LocalDateTime.now().minusYears(1)
//            ));

//            Student savedStudent = studentRepository.save(student);
//            StudentIdCard studentIdCard =
//                    new StudentIdCard(
//                            String.valueOf(faker.number().numberBetween(100000, 1000000)), savedStudent);
//            studentIdCardRepository.save(studentIdCard);
//            student.setStudentIdCard(studentIdCard);


//            Student student = generateRandomStudent();
//            StudentIdCard newStudentIdCard = new StudentIdCard("1011221", student);
//            studentRepository.save(student);
//            studentIdCardRepository.save(newStudentIdCard);


//            Student newStudent = studentRepository.findById(45L).orElseThrow();
//            StudentIdCard newStudentIdCard = new StudentIdCard("451221", newStudent);
//            studentIdCardRepository.save(newStudentIdCard);
//            newStudent.setStudentIdCard(newStudentIdCard);
//            System.out.println(newStudent.getStudentIdCard());
//            studentRepository.delete(newStudent);

//            studentRepository.findAll().forEach(
//                    studentLambdaParam -> System.out.println(
//                            studentLambdaParam.getFirstName() + " " + studentLambdaParam.getAge()));

//            --------------tryin student and course without enrolment-------------
            Student student = generateRandomStudent();
            addNewBooksToStudent(student);

            StudentIdCard studentIdCard = generateRandomIdCard(student);
            student.setStudentIdCard(studentIdCard);

            student.addEnrolment(
                    new Enrolment(
                            new EnrolmentId(1L, 1L),
                            student,
                            new Course("Computer Science", "IT"),
                            LocalDateTime.now().minusDays(1)
                    )
            );
            student.addEnrolment(
                    new Enrolment(
                            new EnrolmentId(1L, 2L),
                            student,
                            new Course("Spring Data Jpa", "IT"),
                            LocalDateTime.now().minusDays(1)
                    )
            );

            studentRepository.save(student);
        };
    }

    private void generateRandomStudents(StudentRepository studentRepository, int generateQuantity) {
        Faker faker = new Faker();
        for (int i = 0; i < generateQuantity; i++) {
            Student student = generateRandomStudent();
            studentRepository.save(student);
        }
    }

    private Student generateRandomStudent() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = String.format("%s.%s@gmail.com", firstName, lastName);
        return new Student(
                firstName,
                lastName,
                email,
                faker.number().numberBetween(17, 55));
    }

    private void addNewBooksToStudent(Student student) {
        student.addBook(new Book(
                "clean code", LocalDateTime.now().minusDays(4)
        ));
        student.addBook(new Book(
                "spring data jpa", LocalDateTime.now()
        ));
        student.addBook(new Book(
                "think and grow rich", LocalDateTime.now().minusYears(1)
        ));
    }

    private StudentIdCard generateRandomIdCard(Student student) {
        Faker faker = new Faker();
        String cardNumber = String.valueOf(faker.number().numberBetween(10000, 100000));
        return new StudentIdCard(cardNumber, student);
    }
}