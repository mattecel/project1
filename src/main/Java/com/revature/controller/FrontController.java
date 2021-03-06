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
import com.revature.beans.Approval;
import com.revature.beans.Author;
import com.revature.beans.Employee;
import com.revature.beans.Status;
import com.revature.beans.Story;
import com.revature.facilities.ApprovalService;
import com.revature.facilities.ApprovalServiceImpl;
import com.revature.facilities.AuthorService;
import com.revature.facilities.AuthorServiceImpl;
import com.revature.facilities.EmployeeService;
import com.revature.facilities.EmployeeServiceImpl;
import com.revature.facilities.StatusService;
import com.revature.facilities.StatusServiceImpl;
import com.revature.facilities.StoryService;
import com.revature.facilities.StoryServiceImpl;

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

	class EmployeeCred {
		public String user;
		public String pass;

		public EmployeeCred(String user, String pass) {
			super();
			this.user = user;
			this.pass = pass;
		}

		@Override
		public String toString() {
			return "Employee [user=" + user + ", pass=" + pass + "]";
		}
	}
	
	class tempApp {
		public String approvalStatus;
		public Integer approvalNumber;
		public Integer statusId;
		
		public tempApp(String approvalStatus, Integer approvalNumber, Integer statusId) {
			super();
			this.approvalStatus = approvalStatus;
			this.approvalNumber = approvalNumber;
			this.statusId = statusId;
		}

		@Override
		public String toString() {
			return "tempApp [approvalStatus=" + approvalStatus + ", approvalNumber=" + approvalNumber + ", statusId="
					+ statusId + "]";
		}
		
		
	}
	
	class Aarp {
		public Integer storyId;
		public Integer authorPoints;
		public Aarp(Integer storyId, Integer authorPoints) {
			super();
			this.storyId = storyId;
			this.authorPoints = authorPoints;
		}
	}

	private ApprovalService apps = new ApprovalServiceImpl();
	private AuthorService auths = new AuthorServiceImpl();
	private EmployeeService ems = new EmployeeServiceImpl();
	private StatusService stas = new StatusServiceImpl();
	private StoryService stos = new StoryServiceImpl();

	private Gson gson = new Gson();
	public static HttpSession session;

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
			Author alogg = (Author) session.getAttribute("logged_in");
			Author loggedAuthor = auths.getAuthor(alogg.getAuthorId());
			response.getWriter().append(gson.toJson(loggedAuthor));
			System.out.println("Sent author object to front end");
			break;
		}

		case "employee-page": {
			Employee elogg = (Employee) session.getAttribute("logged_in");
			Employee loggedEmployee = ems.getEmployee(elogg.getEmployeeId());
			response.getWriter().append(gson.toJson(loggedEmployee));
			System.out.println("Sent employee object to front end");
			break;
		}
		
		case "all-employees": {
			response.getWriter().append(gson.toJson(ems.getAllEmployees()));
			System.out.println("Sending all the employees");
		}

		case "home": {
			response.getWriter().append("AuthorLogin.html");
			break;
		}

		case "logout": {
			System.out.println("Logging out");
			session.invalidate();
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
				System.out.println("Failed to login at author");
			}
			break;
		}
		
		case "employee-login": {
			EmployeeCred ec = gson.fromJson(request.getReader(), EmployeeCred.class);
			System.out.println(ec);
			Employee em = ems.getEmployee(ec.user, ec.pass);
			if (em != null) {
				session.setAttribute("logged_in", em);
				response.getWriter().append("EmployeePage.html");
				System.out.println("Employee log in g00d");
			} else {
				System.out.println("Failed to login at employee");
			}
			break;
		}
		
		case "add-story": {
			Story story = gson.fromJson(request.getReader(), Story.class);
			// Check if date format correct
			System.out.println(story.getCompletionDate());
			Author alogg = (Author) session.getAttribute("logged_in");
			System.out.println(story);
			Story addedStory = stos.addStory(story, alogg.getAuthorId());
			ems.addEmployeeToStory(addedStory);
			break;
		}
		
		case "update-author": {
			Author author = gson.fromJson(request.getReader(), Author.class);
			System.out.println(author);
			auths.updateAuthor(author);
			break;
		}
		
		case "update-employee": {
			Employee emp = gson.fromJson(request.getReader(), Employee.class);
			System.out.println(emp);
			ems.updateEmployee(emp);
			break;
		}
		
		case "update-story": {
			Story story = gson.fromJson(request.getReader(), Story.class);
			System.out.println(story);
			stos.updateStory(story);
			break;
		}
		
		case "approved-story": {
			Aarp aarp = gson.fromJson(request.getReader(), Aarp.class);
			stos.approveStory(aarp.storyId, aarp.authorPoints);
			break;
		}
		
		case "update-status": {
			System.out.println(request.getReader());
			Status status = gson.fromJson(request.getReader(), Status.class);
			System.out.println(status);
			stas.updateStatus(status);
			break;
		}
		
		case "add-approval": {
			tempApp tApp = gson.fromJson(request.getReader(), tempApp.class);
			Approval zApp = new Approval();
			zApp.setApprovalStatus("committee");
			zApp.setApprovalNumber(1);
			apps.addApproval(zApp, tApp.statusId);
			break;
		}
		
		case "update-approval": {
			Approval app = gson.fromJson(request.getReader(), Approval.class);
			System.out.println(app);
			apps.updateApproval(app);
			break;
		}
		
		default: {
			
			System.out.println("Reached the default case in post...");
			response.sendError(418, "BRB MAKING TEA");
		}

		}

	}

}
