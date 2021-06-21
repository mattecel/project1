package com.revature.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.revature.beans.Author;
import com.revature.beans.Story;
import com.revature.facilities.AuthorService;
import com.revature.facilities.AuthorServiceImpl;

public class FrontController extends HttpServlet {

	class AuthorCred {
		public String user;
		public String pass;
		
		public AuthorCred(String user, String pass) {
			super();
			this.user = user;
			this.pass = pass;
		}

		@Override
		public String toString() {
			return "AuthorCred [user=" + user + ", pass=" + pass + "]";
		}

	}

	private AuthorService auths = new AuthorServiceImpl();
	private Gson gson = new Gson();
	public static HttpSession session;
	public JsonParser json = new JsonParser();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, IllegalStateException, JsonSyntaxException {

		String uri = request.getRequestURI();

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");

		session = request.getSession();

		uri = uri.substring("/project1/controller/".length());

		switch (uri) {
		
		case "author-page": {
			System.out.println("Author Page loading");
			Author alogg = (Author) session.getAttribute("logged_in");
			Author loggedAuthor = auths.getAuthor(alogg.getAuthorId());
			System.out.println(loggedAuthor);
			response.getWriter().append(gson.toJson(loggedAuthor));
			System.out.println("Sent author object to front end");
		}
		
		

		case "home": {
			response.getWriter().append("AuthorLogin.html");
			break;
		}

		default: {
			System.out.println("Reached the default case...");
			response.sendError(418, "BRB MAKING TEA");
		}

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String uri = request.getRequestURI();
		System.out.println(json);

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "application/json");

		session = request.getSession();

		uri = uri.substring("/project1/controller/".length());
		
		switch (uri) {

		case "author-login": {
				AuthorCred ac = gson.fromJson(request.getReader(), AuthorCred.class);
				System.out.println(ac);
				Author a = auths.getAuthor(ac.user, ac.pass);
				if (a != null) {
					session.setAttribute("logged_in", a);
					response.getWriter().append("AuthorPage.html");
					System.out.println("Author log in g00d");
				} else {
					System.out.println("Failed to login");
				}
			break;
		}
		}
		
	}

}
