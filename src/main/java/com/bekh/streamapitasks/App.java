package com.bekh.streamapitasks;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        List<Student> students = createStudents();
        System.out.println(getAverageRatingForSubject(students, "OOP"));
        System.out.println(getAverageRatingForSubject(students, "PHYSICS"));
        System.out.println(getAverageRatingForSubject(students, "PMS"));

        Task task1 = new Task("1", "Read Version Control with Git book", TaskType.READING, LocalDate.of(2015, Month.JULY, 1)).addTag("git").addTag("reading").addTag("books");
        Task task2 = new Task("2", "Read Java 8 Lambdas book", TaskType.READING, LocalDate.of(2015, Month.JULY, 5)).addTag("java8").addTag("reading").addTag("books");
        Task task3 = new Task("3", "Write a mobile application to store my tasks", TaskType.CODING, LocalDate.of(2015, Month.JULY, 3)).addTag("coding").addTag("mobile");
        Task task4 = new Task("4", "Write a blog on Java 8 Streams", TaskType.WRITING, LocalDate.of(2015, Month.JULY, 4)).addTag("blogging").addTag("writing").addTag("streams");
        Task task5 = new Task("5", "Read Domain Driven Design book", TaskType.READING, LocalDate.of(2015, Month.JULY, 2)).addTag("ddd").addTag("books").addTag("reading");
        List<Task> tasks = Arrays.asList(task1, task2, task3, task4, task5);
        System.out.println(getFirstFiveReadingTasks(tasks));
    }

    public static String getFirstFiveReadingTasks(List<Task> tasks) {
        return tasks
                .stream()
                .filter(t -> t.getType().equals(TaskType.READING))
                .sorted(Comparator.comparing(Task::getCreatedOn))
                .limit(5)
                .map(Task::getTitle).collect(Collectors.joining(","));
    }

    public static Double getAverageRatingForSubject(List<Student> students, String subject) {
        return students
                .stream()
                .mapToInt(s -> s.getRating().get(subject)).average().orElse(0);
    }

    public static List<Student> createStudents() {
        Student studentAndrey = new Student("Andrey");
        studentAndrey.rate("OOP", 8);
        studentAndrey.rate("PMS", 8);
        studentAndrey.rate("PHYSICS", 5);
        studentAndrey.rate("MATH", 7);

        Student studentAlex = new Student("Alex");
        studentAlex.rate("OOP", 7);
        studentAlex.rate("PMS", 9);
        studentAlex.rate("PHYSICS", 8);
        studentAlex.rate("MATH", 8);

        Student studentIhor = new Student("Ihor");
        studentIhor.rate("OOP", 4);
        studentIhor.rate("PMS", 4);
        studentIhor.rate("PHYSICS", 4);
        studentIhor.rate("MATH", 4);

        List<Student> students = new ArrayList<>();
        students.add(studentAndrey);
        students.add(studentAlex);
        students.add(studentIhor);
        return students;
    }
}
