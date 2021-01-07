package com.halukerd.springboottry.repository;

import com.halukerd.springboottry.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 and s.age >= ?2")
    List<Student> getStudentByFirst_nameAndAgeGreater(String first_name, Integer age);

    @Query(value = "SELECT * FROM student WHERE first_name = :first_name AND age >=:age",
            nativeQuery = true
    )
    List<Student> getStudentByFirst_nameAndAgeGreaterNative(
            @Param("first_name") String first_name,
            @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}