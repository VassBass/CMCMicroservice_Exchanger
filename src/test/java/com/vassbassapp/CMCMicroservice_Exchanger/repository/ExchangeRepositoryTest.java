package com.vassbassapp.CMCMicroservice_Exchanger.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRepositoryTest {
    private static final String TEST_CONTENT = "This is test content!";

    private ExchangeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ExchangeRepository();
    }

    @AfterEach
    void tearDown() {
        repository = null;
    }

    @Test
    void addContent() throws InterruptedException {
        int id = repository.addContent(TEST_CONTENT);

        assertTrue(id >= 1_000 && id < 10_000);
        assertEquals(TEST_CONTENT, repository.getContent(id));

        assertTrue(repository.getContent(888).isEmpty());
    }

    @Test
    void updateContent() throws InterruptedException {
        String expectedContent = "This is expected content!";
        int id = repository.addContent(TEST_CONTENT);

        assertTrue(id >= 1_000 && id < 10_000);
        assertEquals(TEST_CONTENT, repository.getContent(id));

        assertFalse(repository.updateContent(888, expectedContent));
        assertTrue(repository.getContent(888).isEmpty());

        assertTrue(repository.updateContent(id, expectedContent));
        assertEquals(expectedContent, repository.getContent(id));
    }

    @Test
    void removeContent() throws InterruptedException {
        int id = repository.addContent(TEST_CONTENT);

        assertTrue(id >= 1_000 && id < 10_000);
        assertEquals(TEST_CONTENT, repository.getContent(id));

        assertTrue(repository.getContent(888).isEmpty());
        assertFalse(repository.removeContent(888));
        assertTrue(repository.getContent(888).isEmpty());

        assertTrue(repository.removeContent(id));
        assertTrue(repository.getContent(id).isEmpty());
    }

    @Test
    void getContent() throws InterruptedException {
        int id = repository.addContent(TEST_CONTENT);

        assertTrue(id >= 1_000 && id < 10_000);
        assertEquals(TEST_CONTENT, repository.getContent(id));

        assertTrue(repository.getContent(888).isEmpty());
    }
}