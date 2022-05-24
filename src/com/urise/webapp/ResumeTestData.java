package com.urise.webapp;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.ListSection;
import com.urise.webapp.model.section.Organization;
import com.urise.webapp.model.section.OrganizationSection;
import com.urise.webapp.model.section.StringSection;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        String uuid = "uuid1";
        String fullname = "Григорий Кислин";
        ListSection achievement = new ListSection();
        ListSection qualifications = new ListSection();
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


        Organization organization1 = new Organization(LocalDate.of(2013, 10,1), null, "Java Online Projects", "Автор проекта", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        Organization organization2 = new Organization(LocalDate.of(2014, 10,1), LocalDate.of(2016, 1,1), "Wrike", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Organization organization3 = new Organization(LocalDate.of(2012, 4,1), LocalDate.of(2014, 10,1), "RIT Center", "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        Organization organization4 = new Organization(LocalDate.of(2010, 12,1), LocalDate.of(2012, 4,1), "Luxoft (Deutsche Bank)", "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        Organization organization5 = new Organization(LocalDate.of(2008, 6,1), LocalDate.of(2010, 12,1), "Yota", "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        Organization organization6 = new Organization(LocalDate.of(2007, 3,1), LocalDate.of(2008, 6,1), "Enkata", "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        Organization organization7 = new Organization(LocalDate.of(2005, 1,1), LocalDate.of(2007, 2,1), "Siemens AG", "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        Organization organization8 = new Organization(LocalDate.of(1997, 9,1), LocalDate.of(2005, 1,1), "Alcatel", "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");

        Organization organization9 = new Organization(LocalDate.of(2013, 3,1), LocalDate.of(2013, 5,1), "Coursera", null, "\n" + "'Functional Programming Principles in Scala' by Martin Odersky");
        Organization organization10 = new Organization(LocalDate.of(2011, 3,1), LocalDate.of(2011, 4,1), "Siemens AG", null, "\n" + "3 месяца обучения мобильным IN сетям (Берлин)");
        Organization organization11 = new Organization(LocalDate.of(1997, 9,1), LocalDate.of(1998, 3,1), "Alcatel", null, "\n" + "6 месяцев обучения цифровым телефонным сетям (Москва)");
        Organization organization12 = new Organization(LocalDate.of(1993, 9,1), LocalDate.of(1996, 7,1), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", null, "Аспирантура (программист С, С++)");
        Organization organization13 = new Organization(LocalDate.of(1987, 9,1), LocalDate.of(1993, 7,1), "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", null, "Инженер (программист Fortran, C)");
        Organization organization14 = new Organization(LocalDate.of(1984, 9,1), LocalDate.of(1987, 6,1), "Заочная физико-техническая школа при МФТИ", null, "Закончил с отличием");


        OrganizationSection experience = new OrganizationSection(Arrays.asList(organization1, organization2, organization3, organization4, organization5, organization6, organization7, organization8));
        OrganizationSection education = new OrganizationSection(Arrays.asList(organization9, organization10, organization11, organization12, organization13, organization14));

        Resume testResume = new Resume(uuid, fullname);
        testResume.getContacts().put(ContactType.PHONE, "+7(921) 855-0482");
        testResume.getContacts().put(ContactType.SKYPE, "skype:grigory.kislin");
        testResume.getContacts().put(ContactType.EMAIL, "gkislin@yandex.ru");

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
