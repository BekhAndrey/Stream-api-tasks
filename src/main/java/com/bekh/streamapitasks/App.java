package com.bekh.streamapitasks;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.function.Function.identity;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


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
        List<Task> tasks = asList(task1, task2, task3, task4, task5);
        System.out.println(getFirstFiveReadingTasks(tasks));

        List<TaskDto> taskDtos = convertToTaskDtoList(tasks);

        System.out.println(findMostCommonChar("p1p1p1p"));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1L, "Lokesh",
                new Skill("English", 10),
                new Skill("Kannada", 20),
                new Skill("Hindi", 10)));
        persons.add(new Person(2L, "Mahesh",
                new Skill("English", 10),
                new Skill("Kannada", 30),
                new Skill("Hindi", 50)));
        persons.add(new Person(3L, "Ganesh",
                new Skill("English", 10),
                new Skill("Kannada", 20),
                new Skill("Hindi", 40)));
        persons.add(new Person(4L, "Ramesh",
                new Skill("English", 10),
                new Skill("Kannada", 20),
                new Skill("Hindi", 40)));
        persons.add(new Person(5L, "Suresh",
                new Skill("English", 10),
                new Skill("Kannada", 40),
                new Skill("Hindi", 40)));
        persons.add(new Person(6L, "Gnanesh",
                new Skill("English", 100),
                new Skill("Kannada", 20),
                new Skill("Hindi", 40)));
        System.out.println(findBestMatchingPerson(persons, new Skill("English", 50), new Skill("Kannada", 50), new Skill(" Urdu", 50), new Skill("Hindi", 50)));
    }


    public static String findBestMatchingPerson(List<Person> persons, Skill... skills) {
        StringBuilder result = new StringBuilder("[");
        stream(skills).forEach(skill -> {
            List<Person> personsSortedBySkill = persons.stream()
                    .filter(person -> person.getSkills().stream()
                            .anyMatch(s -> s.getTitle().equals(skill.getTitle())))
                    .peek(person -> person.getSkills()
                            .sort(comparing(s -> s.getTitle().equals(skill.getTitle()))))
                    .collect(Collectors.toList());
            if (personsSortedBySkill.size() != 0) {
                personsSortedBySkill
                        .sort(comparingInt(o -> o.getSkills().get(o.getSkills().size() - 1).getProficiency()));
                result.append(skill.getTitle())
                        .append(" : ")
                        .append(personsSortedBySkill.get(personsSortedBySkill.size() - 1).getName())
                        .append(", ");
            } else {
                result.append(skill.getTitle()).append(" : ").append("null, ");
            }
        });
        return result.substring(0, result.length() - 2) + "]";
    }

    public static String findMostCommonChar(String word) {
        return word.chars()
                .mapToObj(c -> (char) c)
                .collect(groupingBy(identity(), counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .map(entry -> "[" + entry.getKey().toString() + "," + entry.getValue().toString() + "]")
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<TaskDto> convertToTaskDtoList(List<Task> tasks) {
        return tasks
                .stream().map(App::convertToTaskDto).collect(toList());
    }

    private static TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto(task.getId(), task.getTitle(), task.getType(), task.getCreatedOn());
        taskDto.setTags(task.getTags());
        return taskDto;
    }

    public static String getFirstFiveReadingTasks(List<Task> tasks) {
        return tasks
                .stream()
                .filter(t -> t.getType().equals(TaskType.READING))
                .sorted(comparing(Task::getCreatedOn))
                .limit(5)
                .map(Task::getTitle).collect(joining(","));
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
