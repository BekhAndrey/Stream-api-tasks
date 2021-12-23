package com.bekh.streamapitasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        List<Student> students = createStudents();
        System.out.println(getAverageRatingForSubject(students, "OOP"));
        System.out.println(getAverageRatingForSubject(students, "PHYSICS"));
        System.out.println(getAverageRatingForSubject(students, "PMS"));
    }

    public static Double getAverageRatingForSubject(List<Student> students, String subject){
        return students
                .stream()
                .mapToInt(s->s.getRating().get(subject)).average().orElse(0);
    }

    public static List<Student> createStudents(){
        Student studentAndrey = new Student("Andrey");
        studentAndrey.rate("OOP",8);
        studentAndrey.rate("PMS",8);
        studentAndrey.rate("PHYSICS",5);
        studentAndrey.rate("MATH",7);

        Student studentAlex = new Student("Alex");
        studentAlex.rate("OOP",7);
        studentAlex.rate("PMS",9);
        studentAlex.rate("PHYSICS",8);
        studentAlex.rate("MATH",8);

        Student studentIhor = new Student("Ihor");
        studentIhor.rate("OOP",4);
        studentIhor.rate("PMS",4);
        studentIhor.rate("PHYSICS",4);
        studentIhor.rate("MATH",4);

        List<Student> students = new ArrayList<>();
        students.add(studentAndrey);
        students.add(studentAlex);
        students.add(studentIhor);
        return students;
    }
}
