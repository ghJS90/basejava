package com.urise.webapp.storage.SerializeStrategy;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.*;

import java.io.*;
import java.time.Month;
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
                dos.writeUTF(entry.getKey().name());

                switch (st) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((StringSection) entry.getValue()).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> listSection = ((ListSection) entry.getValue()).getDescriptionList();
                        dos.writeInt(listSection.size());
                        for (String description : listSection) {
                            dos.writeUTF(description);
                        }
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> orgList = ((OrganizationSection) entry.getValue()).getOrganizations();
                        dos.writeInt(orgList.size());
                        for (Organization organization : orgList) {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl() == null ? "" : organization.getHomePage().getUrl());

                            List<Organization.Position> positions = organization.getPositions();
                            dos.writeInt(positions.size());
                            for (Organization.Position position : positions) {
                                dos.writeInt(position.getDateFrom().getYear());
                                dos.writeInt(position.getDateFrom().getMonthValue());
                                dos.writeInt(position.getDateTo().getYear());
                                dos.writeInt(position.getDateTo().getMonthValue());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription() == null ? "" : position.getDescription());
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
                    case PERSONAL:
                        StringSection testSection = new StringSection(dis.readUTF());
                        resume.getSections().put(st, testSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int listSize = dis.readInt();
                        ListSection testSection3 = new ListSection();
                        for (int j = 0; j < listSize; j++) {
                            testSection3.addStrings(dis.readUTF());
                        }
                        resume.getSections().put(st, testSection3);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        int orgList = dis.readInt();
                        OrganizationSection orgExpSection = new OrganizationSection();
                        for (int j = 0; j < orgList; j++) {
                            String homepage = dis.readUTF();
                            String url = dis.readUTF();
                            Organization organization = new Organization(homepage, url.equals("") ? null : url);
                            int orgCount = dis.readInt();
                            for (int k = 0; k < orgCount; k++) {
                                int startYear = dis.readInt();
                                Month startMonth = Month.of(dis.readInt());
                                int endYear = dis.readInt();
                                Month endMonth = Month.of(dis.readInt());
                                String title = dis.readUTF();
                                String position = dis.readUTF(); //.equals("HERE") ? null : dis.readUTF();
                                position = (position.equals("") ? null : position);
                                organization.addPosition(new Organization.Position(
                                        startYear, startMonth,
                                        endYear, endMonth,
                                        title, position
                                ));
                            }
                            orgExpSection.addOrganization(organization);

                        }
                        resume.getSections().put(st, orgExpSection);
                        break;
                }
            }
            System.out.println(resume.getContacts().values());
            System.out.println(resume.getSections().values());

            return resume;
        }
    }
}
















