package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.section.AbstractSection;
import com.urise.webapp.model.section.ListSection;
import com.urise.webapp.model.section.StringSection;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM section");
        sqlHelper.execute("DELETE FROM contact");
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("" +
                        "    SELECT r.uuid, r.full_name, c.resume_uuid, c.type c_type, c.value c_value, s.type s_type, s.value s_value FROM resume r " +
                        " LEFT JOIN contact c " +
                        "        ON r.uuid = c.resume_uuid " +
                        " LEFT JOIN section s" +
                        "        ON r.uuid = s.resume_uuid" +
                        "     WHERE r.uuid =? ",
                ps -> {
                    ps.setString(1, uuid);
                    ResultSet rs = ps.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name").trim());
                    do {
                        String contactValue = rs.getString("c_value");

                        if (contactValue != null) {
                            ContactType type = ContactType.valueOf(rs.getString("c_type"));
                            r.addContact(type, contactValue);
                        }
                        if (rs.getString("s_type") != null) {
                            SectionType sectionType = SectionType.valueOf(rs.getString("s_type"));
                            if (sectionType.equals(SectionType.PERSONAL) || sectionType.equals(SectionType.OBJECTIVE)) {
                                StringSection stringSection = new StringSection(rs.getString("s_value"));
                                r.addSection(sectionType, stringSection);
                            } else if (sectionType.equals(SectionType.ACHIEVEMENT) || sectionType.equals(SectionType.QUALIFICATIONS)) {
                                ListSection listSection = new ListSection(rs.getString("s_value").split("\n"));
                                r.addSection(sectionType, listSection);
                            } //дописать логику работы с прочими типами секций
                        }
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid = ?")) {
                ps.setString(1, r.getUuid());
                ps.execute();
            }
            return insertContacts(r, conn);
        });
    }

    private Object insertContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return null;
    }

    private Object insertSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                if (e.getKey().equals(SectionType.PERSONAL) || e.getKey().equals(SectionType.OBJECTIVE)) {
                    ps.setString(3, e.getValue().toString());
                    System.out.println(e.getValue().toString());

                } else if (e.getKey().equals(SectionType.ACHIEVEMENT) || e.getKey().equals(SectionType.QUALIFICATIONS)) {
                    ps.setString(3, String.join("\n", ((ListSection) e.getValue()).getDescriptionList()));
                    System.out.println(((ListSection) e.getValue()).getDescriptionList());
                }
                ps.addBatch();
            }
            ps.executeBatch();
        }
        return null;
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    insertContacts(r, conn);
                    insertSections(r, conn);

                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("" +
                "DELETE FROM section WHERE resume_uuid=?;" +
                "DELETE FROM contact WHERE resume_uuid=?;" +
                "DELETE FROM resume WHERE uuid=?", ps -> {
            ps.setString(1, uuid);
            ps.setString(2, uuid);
            ps.setString(3, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("" +
                "SELECT * FROM resume r " +
                "     ORDER BY full_name,uuid", ps -> {
            ResultSet rs = ps.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            Map<String, Map<ContactType, String>> contactsMap = getAllContacts();
            Map<String, Map<SectionType, AbstractSection>> sectionsMap = getAllSections();

            while (rs.next()) {
                Resume tempResume = new Resume(rs.getString("uuid").trim(), rs.getString("full_name").trim());

                tempResume.setContacts(contactsMap.get(tempResume.getUuid()));
                tempResume.setSections(sectionsMap.get(tempResume.getUuid()));

                resumes.add(tempResume);
                System.out.println(resumes);
                System.out.println(resumes.get(resumes.size() - 1).getContacts());
            }
            return resumes;
        });
    }


    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) FROM resume", st -> {
            ResultSet rs = st.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private Map<String, Map<ContactType, String>> getAllContacts() {
        return sqlHelper.execute("SELECT * FROM contact c order by type, id"
                , ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Map<ContactType, String>> resumeIdToContactsMap = new HashMap<>();

                    while (rs.next()) {
                        String resumeId = rs.getString("resume_uuid").trim();
                        ContactType contactType = ContactType.valueOf(rs.getString("type").trim());
                        String contactValue = rs.getString("value").trim();

                        if (resumeIdToContactsMap.containsKey(resumeId)) {
                            resumeIdToContactsMap.get(resumeId)
                                    .put(contactType, contactValue);
                        } else {
                            Map<ContactType, String> tempContactsMap = new EnumMap<>(ContactType.class);
                            tempContactsMap.put(contactType, contactValue);
                            resumeIdToContactsMap.put(resumeId, tempContactsMap);
                        }
                    }
                    System.out.println(resumeIdToContactsMap); //для проверки. к удалению.
                    return resumeIdToContactsMap;
                });
    }

    private Map<String, Map<SectionType, AbstractSection>> getAllSections() {
        return sqlHelper.execute("SELECT * FROM section s order by type, id"
                , ps -> {
                    ResultSet rs = ps.executeQuery();
                    Map<String, Map<SectionType, AbstractSection>> resumeIdToSectionsMap = new HashMap<>();
                    Map<SectionType, AbstractSection> sectionsValue = new EnumMap<>(SectionType.class);

                    while (rs.next()) {
                        String resumeId = rs.getString("resume_uuid").trim();
                        SectionType sectionType = SectionType.valueOf(rs.getString("type").trim());

                        if (sectionType.equals(SectionType.PERSONAL) || sectionType.equals(SectionType.OBJECTIVE)) {
                            StringSection stringSection = new StringSection(rs.getString("value"));
                            sectionsValue.put(sectionType, stringSection);

                            resumeIdToSectionsMap.put(resumeId, sectionsValue);
                        } else if (sectionType.name().equals("ACHIEVEMENT") || sectionType.name().equals("QUALIFICATIONS")) {
                            ListSection listSection = new ListSection(rs.getString("value").split("\n"));
                            sectionsValue.put(sectionType, listSection);

                            resumeIdToSectionsMap.put(resumeId, sectionsValue);
                        }
                    }
                    System.out.println(resumeIdToSectionsMap); //для проверки. к удалению.
                    return resumeIdToSectionsMap;
                });
    }
}
