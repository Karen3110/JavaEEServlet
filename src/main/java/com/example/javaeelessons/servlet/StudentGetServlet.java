package com.example.javaeelessons.servlet;

import com.example.javaeelessons.util.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "getStudentServlet", value = "/getStudent")
public class StudentGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String id = req.getParameter("id");
        if (id == null) {
            resp.sendError(400, "Id not provided");
        }

        String studentContent = FileUtil.readFromFile(id);
        if(studentContent!=null){

        resp.getWriter().write(studentContent);
        }else {
            resp.sendError(204,"Student content is empty");
        }

    }
}
