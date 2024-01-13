package com.example.javatraining.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.javatraining.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // NativeQuery Test; (INJECTION IS POSSIBLE);
    @Query(value = "SELECT * from api.Student WHERE name = :name", nativeQuery = true)
    List<Student> findByNameWithNativeQuery(@Param("name") String name);

    // JPQL Test
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByNameWithJPQL(@Param("name") String name);
}
