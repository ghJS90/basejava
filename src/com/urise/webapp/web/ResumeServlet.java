package com.urise.webapp.web;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8 ");
        String name = request.getParameter("name");
        response.getWriter().write(name == null ? "Hello resumes" : "Hello " + name + "!");

        Storage storage = new SqlStorage("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        List<Resume> resumes = storage.getAllSorted();

        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Пример вывода List в браузер в виде таблицы</title>");
        sb.append("</head>");
        sb.append("<style>" + "table, th, td {" + "border:1px solid #020202;" + "}").append(sb.append("</style>"));
        sb.append("<body>");
        sb.append("<table>");
        sb.append("<tr><th>uuid</th><th>full_name</th></tr>");
        for (Resume resume : resumes) {
            sb.append("<tr>" + "<td>").append(resume.getUuid()).append("</td>");
            sb.append("<td>").append(resume.getFullName()).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");

        response.getWriter().write(sb.toString());


//        request.setAttribute("resumes", storage.getAllSorted());
//        request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
