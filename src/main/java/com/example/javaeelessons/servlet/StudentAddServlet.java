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

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "addStudentServlet", value = "/addStudent")
public class StudentAddServlet extends HttpServlet {

    private static final Gson GSON = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            resp.sendError(500);
        }
        boolean b = FileUtil.saveOrUpdate(UUIDGenerator.randomUUID(), requestBody.toString());
        resp.getWriter().write(GSON.toJson(new ResponseObject(b ? "created" : "failed")));

    }
}
