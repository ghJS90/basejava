package com.urise.webapp.storage.SerializeStrategy;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializeStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, AbstractSection> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
                SectionType st = entry.getKey();
                switch (st) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF((entry.getValue()).toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> listSection = ((ListSection) entry.getValue()).getDescriptionList();
                        int listSize = listSection.size();
                        dos.writeUTF(entry.getKey().name());
                        dos.writeInt(listSize);
                        for (int i = 0; i < listSize; i++) {
                            dos.writeUTF(((ListSection) entry.getValue()).getDescriptionList().get(i));
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        dos.writeUTF(entry.getKey().name());
                        List<Organization> orgList = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(orgList.size());
                        for (Organization organization : orgList) {
                            dos.writeUTF(organization.getHomePage().getName());
                            if (organization.getHomePage().getUrl() == null) {
                                dos.writeUTF("");
                            } else {
                                dos.writeUTF(organization.getHomePage().getUrl());
                            }
                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            if (organization.getPositions().size() != 0) {
                                for (Organization.Position position : positions) {
                                    dos.writeUTF(position.getDateFrom().format(DateTimeFormatter.ISO_LOCAL_DATE));
                                    dos.writeUTF(position.getDateTo().format(DateTimeFormatter.ISO_LOCAL_DATE));
                                    String title = position.getTitle();
                                    String posDescription = position.getDescription();
                                    if (title == null) {
                                        dos.writeUTF("");
                                    } else {
                                        dos.writeUTF(title);
                                    }
                                    if (posDescription == null) {
                                        dos.writeUTF("");
                                    } else {
                                        dos.writeUTF(posDescription);
                                    }
                                }
                            } else {
                                dos.writeUTF("");
                            }
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int contactSize = dis.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                String disAtNow = dis.readUTF();
                SectionType st = SectionType.valueOf(disAtNow);
                switch (st) {
                    case OBJECTIVE:
                        StringSection testSection = new StringSection(dis.readUTF());
                        resume.getSections().put(SectionType.OBJECTIVE, testSection);
                        break;
                    case PERSONAL:
                        StringSection testSection2 = new StringSection(dis.readUTF());
                        resume.getSections().put(SectionType.PERSONAL, testSection2);
                        break;
                    case ACHIEVEMENT:
                        int achListSize = dis.readInt();
                        ListSection testSection3 = new ListSection();
                        for (int j = 0; j < achListSize; j++) {
                            testSection3.addStrings(dis.readUTF());
                        }
                        resume.getSections().put(SectionType.ACHIEVEMENT, testSection3);
                        break;
                    case QUALIFICATIONS:
                        int qualListSize = dis.readInt();
                        ListSection testSection4 = new ListSection();
                        for (int j = 0; j < qualListSize; j++) {
                            testSection4.addStrings(dis.readUTF());
                        }
                        resume.getSections().put(SectionType.QUALIFICATIONS, testSection4);
                        break;
                    case EXPERIENCE:
                        int orgExpList = dis.readInt();
                        OrganizationSection orgExpSection = new OrganizationSection();
                        for (int j = 0; j < orgExpList; j++) {
                            Organization organization = new Organization(dis.readUTF(), dis.readUTF());
                            int orgCount = dis.readInt();
                            for (int k = 0; k < orgCount; k++) {
                                organization.addPosition(new Organization.Position(
                                        LocalDate.parse(dis.readUTF(), DateTimeFormatter.ISO_LOCAL_DATE),
                                        LocalDate.parse(dis.readUTF(), DateTimeFormatter.ISO_LOCAL_DATE),
                                        dis.readUTF(),
                                        dis.readUTF()
                                ));
                                orgExpSection.addOrganization(organization);
                            }
                        }
                        resume.getSections().put(SectionType.EXPERIENCE, orgExpSection);
                        break;
                    case EDUCATION:
                        int orgList = dis.readInt();
                        OrganizationSection orgEducationSection = new OrganizationSection();
                        for (int j = 0; j < orgList; j++) {
                            String orgName = dis.readUTF();
                            String orgUrl;
                            if (dis.readUTF().equals("")) {
                                orgUrl = null;
                            } else {
                                orgUrl = dis.readUTF();
                            }
                            Organization organization = new Organization(orgName, orgUrl);
                            int orgCount = dis.readInt();
                            if (!(orgCount == 0)) {
                                for (int k = 0; k < orgCount; k++) {
                                    LocalDate dateFrom = LocalDate.parse(dis.readUTF(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    LocalDate dateTo = LocalDate.parse(dis.readUTF(), DateTimeFormatter.ISO_LOCAL_DATE);
                                    String title;
                                    String position;
                                    if (!dis.readUTF().equals("")) {
                                        title = dis.readUTF();
                                    } else {
                                        title = null;
                                    }
                                    if (!dis.readUTF().equals("")) {
                                        position = dis.readUTF();
                                    } else {
                                        position = null;
                                    }
                                    organization.addPosition(new Organization.Position(dateFrom, dateTo, title, position));
                                }
                                orgEducationSection.addOrganization(organization);
                            }
                        }
                        resume.getSections().put(SectionType.EDUCATION, orgEducationSection);
                        break;
                }
            }
            System.out.println(resume.getContacts().values());
            System.out.println(resume.getSections().values());

            return resume;
        }
    }
}
















