package com.klef.soa.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.klef.soa.task.entity.Newspaper;
import com.klef.soa.task.repository.NewspaperRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NewspaperApplicationTests {

    @Autowired
    private NewspaperRepository repository;

    private Newspaper firstNewspaper;
    private Newspaper secondNewspaper;

    @BeforeEach
    void setUp() {
        firstNewspaper = repository.saveAndFlush(createNewspaper(
                "Exam Test Daily", "Daily", "Active", "Chennai"));
        secondNewspaper = repository.saveAndFlush(createNewspaper(
                "Exam Test Weekly", "Weekly", "Active", "Vijayawada"));
    }

    @Test
    void findAllReturnsExpectedNewspapers() {
        List<Newspaper> newspapers = repository.findAll();

        assertNotNull(newspapers);
        assertTrue(newspapers.stream()
                .anyMatch(newspaper -> newspaper.getNewspaperId().equals(firstNewspaper.getNewspaperId())));
        assertTrue(newspapers.stream()
                .anyMatch(newspaper -> newspaper.getNewspaperId().equals(secondNewspaper.getNewspaperId())));
    }

    @Test
    void findByIdReturnsMatchingNewspaperAndEmptyForUnknownId() {
        Newspaper found = repository.findById(firstNewspaper.getNewspaperId()).orElseThrow();

        assertEquals(firstNewspaper.getNewspaperId(), found.getNewspaperId());
        assertEquals("Exam Test Daily", found.getName());
        assertEquals("Daily", found.getType());
        assertEquals("Active", found.getStatus());
        assertEquals("Chennai", found.getLocation());
        assertTrue(repository.findById(Long.MAX_VALUE).isEmpty());
    }

    @Test
    void findByNameReturnsMatchingNewspaper() {
        List<Newspaper> matches = repository.findByName("Exam Test Daily");

        assertEquals(1, matches.size());
        assertEquals("Exam Test Daily", matches.get(0).getName());
        assertEquals(firstNewspaper.getNewspaperId(), matches.get(0).getNewspaperId());
    }

    @Test
    void countIncludesExactlyTheNewlyInsertedRecords() {
        long countBeforeInsert = repository.count();

        repository.saveAllAndFlush(List.of(
                createNewspaper("Exam Count One", "Daily", "Active", "Guntur"),
                createNewspaper("Exam Count Two", "Monthly", "Inactive", "Nellore")));

        assertEquals(countBeforeInsert + 2, repository.count());
    }

    @Test
    void findByIdReturnsNewspaperWithRequiredProperties() {
        Newspaper found = repository.findById(secondNewspaper.getNewspaperId()).orElseThrow();

        assertNotNull(found.getNewspaperId());
        assertNotNull(found.getName());
        assertNotNull(found.getType());
        assertNotNull(found.getStatus());
        assertNotNull(found.getLocation());
        assertTrue(found.getNewspaperId() > 0);
        assertFalse(found.getName().isBlank());
        assertEquals("Exam Test Weekly", found.getName());
        assertEquals("Weekly", found.getType());
        assertEquals("Active", found.getStatus());
        assertEquals("Vijayawada", found.getLocation());
    }

    private Newspaper createNewspaper(String name, String type, String status, String location) {
        Newspaper newspaper = new Newspaper();
        newspaper.setName(name);
        newspaper.setType(type);
        newspaper.setStatus(status);
        newspaper.setLocation(location);
        return newspaper;
    }
}
