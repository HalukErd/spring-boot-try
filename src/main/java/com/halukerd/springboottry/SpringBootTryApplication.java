package com.halukerd.springboottry;

import com.github.javafaker.Faker;
import com.halukerd.springboottry.model.Book;
import com.halukerd.springboottry.model.Student;
import com.halukerd.springboottry.model.StudentIdCard;
import com.halukerd.springboottry.repository.StudentIdCardRepository;
import com.halukerd.springboottry.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

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
//            Student maria = new Student(
//                    "maria",
//                    "jones",
//                    "m.jones@gmail.com",
//                    25);
//            studentRepository.save(maria);
//
//            Student haluk = new Student(
//                    "haluk",
//                    "erd",
//                    "erd.haluk@gmail.com",
//                    28
//            );


//            studentRepository.saveAll(List.of(maria, haluk));
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
            generateRandomStudents(studentRepository, 5);
            Faker faker = new Faker();

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));
            studentRepository.save(student);

            student.addBook(new Book(
                    "clean code", LocalDateTime.now().minusDays(4)
            ));
            student.addBook(new Book(
                    "spring data jpa", LocalDateTime.now()
            ));
            student.addBook(new Book(
                    "think and grow rich", LocalDateTime.now().minusYears(1)
            ));

            StudentIdCard studentIdCard = new StudentIdCard("123456789", student);
            studentIdCardRepository.save(studentIdCard);
            student.setStudentIdCard(studentIdCard);

            studentRepository.save(student);


            studentRepository.findAll().forEach(
                    studentLambdaParam -> System.out.println(
                            studentLambdaParam.getFirstName() + " " + studentLambdaParam.getAge()));

        };
    }

    private void generateRandomStudents(StudentRepository studentRepository, int generateQuantity) {
        Faker faker = new Faker();
        for (int i = 0; i < generateQuantity; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = String.format("%s.%s@gmail.com", firstName, lastName);
            Student student = new Student(
                    firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55));
            studentRepository.save(student);
        }
    }
}