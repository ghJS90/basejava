package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.ListSection;
import com.urise.webapp.model.section.Organization;
import com.urise.webapp.model.section.OrganizationSection;
import com.urise.webapp.model.section.StringSection;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\testFileStorage");

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    private static final Resume R_1;
    private static final Resume R_2;
    private static final Resume R_3;
    private static final Resume R_4;

    static {
        R_1 = new Resume(UUID_1, "Name1");
        R_2 = new Resume(UUID_2, "Name2");
        R_3 = new Resume(UUID_3, "Name3");
        R_4 = new Resume(UUID_4, "Name4");

        R_1.addContact(ContactType.EMAIL, "mail1@ya.ru");
        R_1.addContact(ContactType.PHONE, "11111");
        R_1.addSection(SectionType.OBJECTIVE, new StringSection("Objective1"));
        R_1.addSection(SectionType.PERSONAL, new StringSection("Personal data"));
        R_1.addSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R_1.addSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Position(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R_1.addSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Position(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Position(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));
        R_2.addContact(ContactType.SKYPE, "skype2");
        R_2.addContact(ContactType.PHONE, "22222");
        R_1.addSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Position(2015, Month.JANUARY, "position1", "content1"))));
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(R_2);
        storage.save(R_1);
        storage.save(R_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateException() {
        storage.update(R_4);
    }

    @Test
    public void update() throws Exception {
        Resume testResume = new Resume(UUID_3, "F");
        storage.update(testResume);
        assertEquals(testResume, storage.get(UUID_3));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteException() {
        storage.delete(UUID_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> expected = Arrays.asList(R_1, R_2, R_3);
        assertEquals(expected, storage.getAllSorted());
    }

    @Test(expected = NotExistStorageException.class)
    public void getException() {
        storage.get("dummy");
    }

    @Test
    public void get() throws Exception {
        assertEquals(R_1, storage.get(UUID_1));
    }

    @Test(expected = ExistStorageException.class)
    public void saveException() {
        storage.save(R_3);
    }

    @Test
    public void save() throws Exception {
        storage.save(R_4);
        assertEquals(R_4, storage.get(UUID_4));
        assertEquals(4, storage.size());
    }
}