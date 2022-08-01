package com.urise.webapp.storage.SerializeStrategy;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.urise.webapp.util.DateUtil.of;


public class DataStreamSerializer implements SerializeStrategy {


    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeWithException(contacts.entrySet(), dos, entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeWithException(r.getSections().entrySet(), dos, entry -> {
                SectionType st = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(st.name());
                switch (st) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((StringSection) section).getDescription());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeWithException(((ListSection) section).getDescriptionList(), dos, dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(((OrganizationSection) section).getOrganizations(), dos, organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl() == null ? "" : organization.getHomePage().getUrl());
                            writeWithException(organization.getPositions(), dos, position -> {
                                writeDate(dos, position.getDateFrom());
                                writeDate(dos, position.getDateTo());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription() == null ? "" : position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            read(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            read(dis, () -> {
                SectionType st = SectionType.valueOf(dis.readUTF());
                switch (st) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.getSections().put(st, new StringSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        ListSection ls = new ListSection();
                        read(dis, () -> ls.addStrings(dis.readUTF()));
                        resume.getSections().put(st, ls);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        OrganizationSection orgSection = new OrganizationSection(readList(dis, (MyListReader<Organization>) (list) -> {
                            String homepage = dis.readUTF();
                            String url = dis.readUTF();
                            Organization organization = new Organization(homepage, url.equals("") ? null : url);
                            DataStreamSerializer.this.read(dis, () -> {
                                LocalDate startDate = DataStreamSerializer.this.readDate(dis.readInt(), dis.readInt());
                                LocalDate endDate = DataStreamSerializer.this.readDate(dis.readInt(), dis.readInt());
                                String title = dis.readUTF();
                                String position = dis.readUTF();
                                position = (position.equals("") ? null : position);
                                organization.addPosition(new Organization.Position(
                                        startDate, endDate,
                                        title, position
                                ));
                            });
                            list.add(organization);
//                            orgSection.addOrganization(organization);
                        }));
                        resume.getSections().put(st, orgSection);
//
//                        OrganizationSection orgSection = new OrganizationSection();
//                        read(dis, () -> {
//                            String homepage = dis.readUTF();
//                            String url = dis.readUTF();
//                            Organization organization = new Organization(homepage, url.equals("") ? null : url);
//                            read(dis, () -> {
//                                LocalDate startDate = readDate(dis.readInt(), dis.readInt());
//                                LocalDate endDate = readDate(dis.readInt(), dis.readInt());
//                                String title = dis.readUTF();
//                                String position = dis.readUTF();
//                                position = (position.equals("") ? null : position);
//                                organization.addPosition(new Organization.Position(
//                                        startDate, endDate,
//                                        title, position
//                                ));
//                            });
//                            orgSection.addOrganization(organization);
//
//                        });
//                        resume.getSections().put(st, orgSection);

                        break;
                }
            });
            System.out.println(resume.getContacts().values());
            System.out.println(resume.getSections().values());

            return resume;
        }
    }

    private LocalDate readDate(int year, int month) {
        return of(year, Month.of(month));
    }

    private void writeDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
    }


    private <T> void writeWithException(Collection<T> c, DataOutputStream dos, MyWriter<T> testInterface) throws IOException {
        Objects.requireNonNull(c);
        dos.writeInt(c.size());
        for (T item : c) {
            testInterface.write(item);
        }
    }

    private void read(DataInputStream dis, MyReader testInterface) throws IOException {
        int collectionSize = dis.readInt();
        for (int i = 0; i < collectionSize; i++) {
            testInterface.read();
        }
    }

    private <T> List<T> readList(DataInputStream dis, MyListReader<T> listReader) throws IOException {
        int collectionSize = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < collectionSize; i++) {
            listReader.read(list);
        }
        return list;
    }

    @FunctionalInterface
    private interface MyWriter<T> {
        void write(T item) throws IOException;
    }

    @FunctionalInterface
    private interface MyReader {
        void read() throws IOException;
    }

    @FunctionalInterface
    private interface MyListReader<T> {
        void read(List<T> list) throws IOException;
    }
}
















