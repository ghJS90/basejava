package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Position;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.ListSection;
import com.urise.webapp.model.section.Organization;
import com.urise.webapp.model.section.OrganizationSection;
import com.urise.webapp.model.section.StringSection;
import com.urise.webapp.storage.FileStorage;
import com.urise.webapp.storage.Storage;

import java.io.File;
import java.time.LocalDate;

public class ResumeTestData {
    private final static Storage ARRAY_STORAGE = new FileStorage(new File("C:/testFileStorage"));

    public static void main(String[] args) {

        String uuid = "uuid1";
        String fullname = "Григорий Кислин";
        ListSection achievement = new ListSection();
        ListSection qualifications = new ListSection();
        OrganizationSection experience = new OrganizationSection();
        OrganizationSection education = new OrganizationSection();
        achievement.addStrings("\nОрганизация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет\n" +
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.\n" +
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n" +
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n" +
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        qualifications.addStrings("\nJEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n" +
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n" +
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB\n" +
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy\n" +
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts\n" +
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).\n" +
                "Python: Django.\n" +
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js\n" +
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka\n" +
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\n" +
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix\n" +
                "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer\n" +
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования\n" +
                "Родной русский, английский \"upper intermediate\"");
        StringSection personal = new StringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        StringSection objective = new StringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        Organization org1 = new Organization("Java Online Projects", "http://javaops.ru/");
        org1.addPosition(new Position(LocalDate.of(2013, 10, 1), null, "Автор Проекта", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        Organization org2 = new Organization("Wrike", "https://www.wrike.com/");
        org2.addPosition(new Position(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));

        Organization org3 = new Organization("Coursera", "https://www.coursera.org/course/progfun");
        org3.addPosition(new Position(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), null, "'Functional Programming Principles in Scala' by Martin Odersky"));
        Organization org4 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");
        org4.addPosition(new Position(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), null, "Аспирантура (программист С, С++)"));
        org4.addPosition(new Position(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), null, "Инженер (программист Fortran, C)"));

        experience.addOrganization(org1, org2);
        education.addOrganization(org3, org4);

        Resume testResume = new Resume(uuid, fullname);
        testResume.getContacts().put(ContactType.PHONE, "+7(921) 855-0482");
        testResume.getContacts().put(ContactType.SKYPE, "skype:grigory.kislin");
        testResume.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");
        testResume.getContacts().put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        testResume.getContacts().put(ContactType.GITHUB, "https://github.com/gkislin");
        testResume.getContacts().put(ContactType.HOME_PAGE, "http://gkislin.ru/");

        testResume.getSections().put(SectionType.PERSONAL, personal);
        testResume.getSections().put(SectionType.OBJECTIVE, objective);
        testResume.getSections().put(SectionType.ACHIEVEMENT, achievement);
        testResume.getSections().put(SectionType.QUALIFICATIONS, qualifications);
        testResume.getSections().put(SectionType.EXPERIENCE, experience);
        testResume.getSections().put(SectionType.EDUCATION, education);

//        getResumeInfo(testResume);

        Resume resumeForUpdate = new Resume(uuid, "Новый Сотрудник");

        ARRAY_STORAGE.clear();
        ARRAY_STORAGE.size();
        ARRAY_STORAGE.save(testResume);
        ARRAY_STORAGE.size();
        getResumeInfo(ARRAY_STORAGE.get(uuid));
        ARRAY_STORAGE.update(resumeForUpdate);
        getResumeInfo(ARRAY_STORAGE.get(uuid));
//        ARRAY_STORAGE.delete(uuid);
//        ARRAY_STORAGE.clear();
//        ARRAY_STORAGE.delete("test");

    }

    public static void getResumeInfo(Resume resume) {
        System.out.println(resume.getUuid());
        System.out.println(resume.getFullName());
        System.out.println(resume.getContacts().values());
        System.out.println(resume.getSections().values());
    }
}
