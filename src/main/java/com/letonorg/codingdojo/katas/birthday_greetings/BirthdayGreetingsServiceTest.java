package com.letonorg.codingdojo.katas.birthday_greetings;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BirthdayGreetingsServiceTest {

    @Test
    void should_send_nothing_when_no_one_has_birthday_today() {
        // given
        InMemoryEmployeeDatabase db = new InMemoryEmployeeDatabase(List.of(
                new EmployeeRecord("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new EmployeeRecord("Mary", "Ann", LocalDate.of(1975, 3, 11), "mary.ann@foobar.com")
        ));
        FakeEmailSender emailSender = new FakeEmailSender();

        BirthdayGreetingsService service = new BirthdayGreetingsService(db, emailSender);

        // when
        service.sendGreetings(LocalDate.of(2026, 2, 18));

        // then
        assertEquals(0, emailSender.sentEmails.size());
    }

    @Test
    void should_send_greetings_email_when_one_employee_has_birthday_today() {
        // given
        InMemoryEmployeeDatabase db = new InMemoryEmployeeDatabase(List.of(
                new EmployeeRecord("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com"),
                new EmployeeRecord("Mary", "Ann", LocalDate.of(1975, 2, 18), "mary.ann@foobar.com")
        ));
        FakeEmailSender emailSender = new FakeEmailSender();

        BirthdayGreetingsService service = new BirthdayGreetingsService(db, emailSender);

        // when
        service.sendGreetings(LocalDate.of(2026, 2, 18));

        // then
        assertEquals(1, emailSender.sentEmails.size());

        SentEmail email = emailSender.sentEmails.get(0);
        assertEquals("mary.ann@foobar.com", email.to);
        assertEquals("Happy Birthday!", email.subject);
        assertEquals("Happy birthday, dear Mary!", email.body);
    }

    @Test
    void should_send_one_email_per_employee_when_multiple_birthdays_today() {
        // given
        InMemoryEmployeeDatabase db = new InMemoryEmployeeDatabase(List.of(
                new EmployeeRecord("Mary", "Ann", LocalDate.of(1975, 2, 18), "mary.ann@foobar.com"),
                new EmployeeRecord("Bob", "Smith", LocalDate.of(1990, 2, 18), "bob.smith@foobar.com"),
                new EmployeeRecord("John", "Doe", LocalDate.of(1982, 10, 8), "john.doe@foobar.com")
        ));
        FakeEmailSender emailSender = new FakeEmailSender();

        BirthdayGreetingsService service = new BirthdayGreetingsService(db, emailSender);

        // when
        service.sendGreetings(LocalDate.of(2026, 2, 18));

        // then
        assertEquals(2, emailSender.sentEmails.size());
        assertTrue(emailSender.sentEmails.stream().anyMatch(e ->
                e.to.equals("mary.ann@foobar.com")
                        && e.subject.equals("Happy Birthday!")
                        && e.body.equals("Happy birthday, dear Mary!")
        ));

        assertTrue(emailSender.sentEmails.stream().anyMatch(e ->
                e.to.equals("bob.smith@foobar.com")
                        && e.subject.equals("Happy Birthday!")
                        && e.body.equals("Happy birthday, dear Bob!")
        ));
    }

    @Test
    void should_match_birthday_by_month_and_day_only_ignoring_birth_year() {
        // given
        InMemoryEmployeeDatabase db = new InMemoryEmployeeDatabase(List.of(
                new EmployeeRecord("Alice", "Young", LocalDate.of(2001, 2, 18), "alice.young@foobar.com"),
                new EmployeeRecord("Alice", "Old", LocalDate.of(1960, 2, 18), "alice.old@foobar.com")
        ));
        FakeEmailSender emailSender = new FakeEmailSender();

        BirthdayGreetingsService service = new BirthdayGreetingsService(db, emailSender);

        // when
        service.sendGreetings(LocalDate.of(2026, 2, 18));

        // then
        assertEquals(2, emailSender.sentEmails.size());
    }

    // ---------------------------------------------------------------------
    // Test doubles (DB-like + SMTP-like) — these are NOT the kata solution.
    // They exist only to keep tests hermetic and push design seams.
    // ---------------------------------------------------------------------

    static final class InMemoryEmployeeDatabase {
        private final List<EmployeeRecord> records;

        InMemoryEmployeeDatabase(List<EmployeeRecord> records) {
            this.records = records;
        }

        public List<EmployeeRecord> findAll() {
            return records;
        }
    }

    static final class FakeEmailSender {
        private final List<SentEmail> sentEmails = new ArrayList<>();

        public void send(String to, String subject, String body) {
            sentEmails.add(new SentEmail(to, subject, body));
        }
    }

    static final class SentEmail {
        final String to;
        final String subject;
        final String body;

        SentEmail(String to, String subject, String body) {
            this.to = to;
            this.subject = subject;
            this.body = body;
        }

        @Override
        public String toString() {
            return "to: " + to + ", subject: " + subject + ", body: " + body;
        }
    }

    static final class EmployeeRecord {
        final String firstName;
        final String lastName;
        final LocalDate dateOfBirth;
        final String email;

        EmployeeRecord(String firstName, String lastName, LocalDate dateOfBirth, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.dateOfBirth = dateOfBirth;
            this.email = email;
        }
    }
}
