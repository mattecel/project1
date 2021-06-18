package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.revature.beans.Author;
import com.revature.facilities.AuthorService;
import com.revature.facilities.AuthorServiceImpl;

public class FrontController extends HttpServlet {

	private AuthorService auths = new AuthorServiceImpl();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		class AuthorCred {
			public String user;
			public String pass;
		}
		
		
		String uri = request.getRequestURI();
		String loggedId;

		/*
		 * for GET requests:
		 * 
		 * localhost:8080/CatAppServlet/cats -> get all cats
		 * 
		 * /cats/<id> -> get a cat by id
		 * 
		 * for POST requests: /cats -> add cat
		 * 
		 */
		switch (uri) {

		case "/project1/author": {

			if (request.getMethod().equals("GET")) {
				System.out.println("Getting author....");
				Author author = auths.getAuthor("lulu1", "123");
				System.out.println(author);
				response.setHeader("Access-Control-Allow-Origin", "*");
				response.getWriter().append(gson.toJson(author));

				break;
			} else {
				System.out.println("Adding author...");
				Author author = auths.getAuthor("lulu1", "123");
				System.out.println(author);
				// response.setHeader("Access-Control-Allow-Origin","*");
				response.setHeader("Origin", "http://localhost:8080/project1/author/");
				response.getWriter().append(gson.toJson(author));
				break;
			}

		}
		case ("/project1/author-login"): {
			AuthorCred aci = this.gson.fromJson(request.getReader(), AuthorCred.class);
			Author a = auths.getAuthor(aci.user, aci.pass);
			session.setAttribute("logged_in", a)
		}

		default: {
			System.out.println("Reached the default case...");
			response.sendError(418, "BRB MAKING TEA");
		}

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().append("Post request");
	}

}
