package com.example.javaeelessons.servlet;

import com.example.javaeelessons.model.ResponseObject;
import com.example.javaeelessons.util.FileUtil;
import com.example.javaeelessons.util.UUIDGenerator;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "deleteStudentServlet", value = "/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {

    private static final Gson GSON = new Gson();

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        if (id == null) {
            resp.sendError(400, "Id not provided");
        }
        boolean delete = FileUtil.delete(id);
        resp.getWriter().write(GSON.toJson(new ResponseObject(delete?"deleted":"filed")));

    }
}
