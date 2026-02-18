package com.letonorg.codingdojo.katas.birthday_greetings;

import java.time.LocalDate;
import java.util.List;

// Pour ce kata nous reprosduisons les services extérieurs dans le fichier de tests,
// en tant normal nous respecterion l'archi Héxagonal et nous mockerions les appell aux sender et db
// Ce kata perd beaucoup de son utilité dans ce format lot of kata in few files
public class BirthdayGreetingsService {
    private final BirthdayGreetingsServiceTest.InMemoryEmployeeDatabase employeeDatabase;
    private final BirthdayGreetingsServiceTest.FakeEmailSender emailSender;

    public BirthdayGreetingsService(
            BirthdayGreetingsServiceTest.InMemoryEmployeeDatabase employeeDatabase,
            BirthdayGreetingsServiceTest.FakeEmailSender emailSender
    ) {
        this.employeeDatabase = employeeDatabase;
        this.emailSender = emailSender;
    }

    public void sendGreetings(LocalDate today) {
        List<BirthdayGreetingsServiceTest.EmployeeRecord> employeeRecords = this.employeeDatabase.findAll();
         for (BirthdayGreetingsServiceTest.EmployeeRecord employeeRecord : employeeRecords) {
             if (isBirthday(today, employeeRecord.dateOfBirth))
                 this.emailSender.send(
                         employeeRecord.email,
                         "Happy Birthday!",
                         "Happy birthday, dear " + employeeRecord.firstName + "!"
                 );
         }
    }

    private boolean isBirthday(LocalDate today, LocalDate dateOfBirth) {
            return today.getMonth() == dateOfBirth.getMonth() && today.getDayOfMonth() == dateOfBirth.getDayOfMonth();
    }
}