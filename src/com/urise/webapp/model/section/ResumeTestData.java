package com.urise.webapp.model.section;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        String uuid = "uuid1";
        String fullname = "Григорий Кислин";
        String phone = "+7(921) 855-0482";
        String skype = "skype:grigory.kislin";
        String email = "gkislin@yandex.ru";
        StringSection personal = new StringSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        StringSection objective = new StringSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        ListSection achievement = new ListSection(new ArrayList<>(Collections.singletonList("\nОрганизация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет\n" +
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.\n" +
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.\n" +
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.\n" +
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.\n" +
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).\n" +
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.")));
        ListSection qualifications = new ListSection(new ArrayList<>(Collections.singletonList("\nJEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2\n" +
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
                "Родной русский, английский \"upper intermediate\"")));
        Organization organizationSection1 = new Organization(LocalDate.of(2013, 10,1), LocalDate.of(2022, 5,1), "Java Online Projects", "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organizationSection2 = new Organization(LocalDate.of(2014, 10,1), LocalDate.of(2016, 1,1), "Wrike", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization organizationSection3 = new Organization(LocalDate.of(2013, 3,1), LocalDate.of(2013, 5,1), "Coursera", "", "\n" + "'Functional Programming Principles in Scala' by Martin Odersky");

        OrganizationSection experience = new OrganizationSection(new ArrayList<>(Arrays.asList(organizationSection1, organizationSection2)));
        OrganizationSection education = new OrganizationSection(new ArrayList<>(Arrays.asList(organizationSection3)));

        Resume testResume = new Resume(uuid, fullname);
        testResume.getContacts().put(ContactType.PHONE, phone);
        testResume.getContacts().put(ContactType.SKYPE, skype);
        testResume.getContacts().put(ContactType.EMAIL, email);

        testResume.getSections().put(SectionType.PERSONAL, personal);
        testResume.getSections().put(SectionType.OBJECTIVE, objective);
        testResume.getSections().put(SectionType.ACHIEVEMENT, achievement);
        testResume.getSections().put(SectionType.QUALIFICATIONS, qualifications);
        testResume.getSections().put(SectionType.EXPERIENCE, experience);
        testResume.getSections().put(SectionType.EDUCATION, education);

        System.out.println(testResume.getUuid());
        System.out.println(testResume.getFullName());
        System.out.println(testResume.getContacts().values());
        System.out.println(testResume.getSections().values());
    }
}
