package com.example.javaeelessons.servlet;

import com.example.javaeelessons.model.ResponseObject;
import com.example.javaeelessons.util.FileUtil;
import com.example.javaeelessons.util.UUIDGenerator;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "updateStudentServlet", value = "/updateStudent")
public class StudentUpdateServlet extends HttpServlet {

    private static final Gson GSON = new Gson();


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        StringBuilder reqbody = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                reqbody.append(line);
            }
        } catch (IOException e) {
            resp.sendError(500);
        }

        JsonObject requestBody = new JsonParser().parse(reqbody.toString()).getAsJsonObject();
        JsonElement id = requestBody.get("id");
        if(id==null || id.isJsonNull()){
            resp.sendError(400,"Id not found");
        }else {
            boolean b = FileUtil.saveOrUpdate(id.getAsString(), requestBody.toString());
            resp.getWriter().write(GSON.toJson(new ResponseObject(b ? "updated" : "failed")));
        }




    }
}
