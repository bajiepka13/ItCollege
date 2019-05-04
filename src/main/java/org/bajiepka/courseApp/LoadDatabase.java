package org.bajiepka.courseApp;

import lombok.extern.slf4j.Slf4j;
import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.domain.CourseProgress;
import org.bajiepka.courseApp.domain.ExchangeFile;
import org.bajiepka.courseApp.domain.Student;
import org.bajiepka.courseApp.repositories.CourseProgressRepository;
import org.bajiepka.courseApp.repositories.CourseRepository;
import org.bajiepka.courseApp.repositories.ExchangeFileRepository;
import org.bajiepka.courseApp.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Slf4j
@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner populateDatabase(
            StudentRepository studentRepository,
            CourseRepository courseRepository,
            CourseProgressRepository progressRepository,
            ExchangeFileRepository exchangeFileRepository) {

        return args -> {

            log.info("Загружаем учащихся");
            Student studentIvanov = new Student(
                    "Иванов Иван Иванович",
                    "298302, Республика Крым, г. Керчь, ул. Вознесенская, 15 / ф",
                    "+7(978)111-22-33",
                    1000121);

            Student studentJackson = new Student(
                    "Бобби Джэксон Младший",
                    "298302, Республика Крым, г. Керчь, ул. Провансальная, 22 / 11",
                    "+7(978)111-22-33",
                    1000122);

            Student studentCarlson = new Student(
                    "Сэмми Смит Карлсон",
                    "298302, Республика Крым, г. Керчь, ул. Ленинская, 8а",
                    "+7(978)222-33-44",
                    1000123);

            Student studentHarris = new Student(
                    "Гарри Дэйл Харрисон",
                    "298302, Республика Крым, г. Керчь, ул. Оборонная, 127a / 1",
                    "+7(978)333-44-55",
                    1000124);

            Student studentGomez = new Student(
                    "Линдси Джэфферсон Гомез",
                    "298302, Республика Крым, г. Керчь, ул. Суздальская, 200 / 112",
                    "+7(978)555-66-77",
                    1000125);

            Student studentParker = new Student(
                    "Рэйчел Джэксон Паркер",
                    "298302, Республика Крым, г. Керчь, Флотский переулок, 1",
                    "+7(978)666-77-88",
                    1000126);

            log.info("Загружаем базовые курсы для студентов...");
            Course chemistryCourse = new Course("Химия для учеников старших классов и студентов", 202554110, 22000f);
            Course physicsCourse = new Course("Углубленный курс физики", 100292910, 25000f);
            Course programmingCourse = new Course("Общий кура подготовки программиста на языке Java Core.", 300235650, 30000f);

            CourseProgress ivanovJavaProgress = new CourseProgress();
            ivanovJavaProgress.setName("Курс программирования Java");
            ivanovJavaProgress.setCourse(programmingCourse);
            studentIvanov.addCourseProgress(ivanovJavaProgress);

            programmingCourse.addParticipator(studentIvanov);

            ivanovJavaProgress.addMark(Arrays.asList(5, 4, 4, 5, 4, 5));

            CourseProgress ivanovChemProgress = new CourseProgress();
            ivanovChemProgress.setName("Подготовительные курсы по химии");
            ivanovChemProgress.setCourse(chemistryCourse);
            studentIvanov.addCourseProgress(ivanovChemProgress);

            chemistryCourse.addParticipator(studentIvanov);

            ivanovChemProgress.addMark(Arrays.asList(2, 3, 2, 4, 5, 3));

            studentRepository.save(studentIvanov);

//            CourseProgress jacksonJavaProgress = new CourseProgress();
//            jacksonJavaProgress.setName("Курс программирования Java");
//            jacksonJavaProgress.setCourse(programmingCourse);
//            studentJackson.addCourseProgress(jacksonJavaProgress);
//
//            programmingCourse.addParticipator(studentJackson);
//
//            studentRepository.save(studentJackson);
//
//            jacksonJavaProgress.addMark(Arrays.asList(5, 5, 5, 5, 4, 5));

            log.info("Загружаем тестовый экспортный фаайл" + exchangeFileRepository.save(
                    new ExchangeFile("D:\\export_json_31-03-2019_20_12_58.json", Long.valueOf(1846))));

            studentRepository.save(studentCarlson);
            studentRepository.save(studentHarris);
            studentRepository.save(studentGomez);
            studentRepository.save(studentParker);

        };
    }

}
