Story Pitch System - README
Matthew Ecelbarger - 2105 Java w/MSA - Project 1
This Project was completed to fulfill the requirements of Project 1 furing Training at Revature LLC.

The client was looking for a Web Application with which Authors could create and pitch story ideas and employees could send said pitches down the approval pipeline.

Package Structure
Models: Java Beans that represent tables in the Oracle SQL Database
Repositories: Data Access Objects used to query the Database
Services: Any necessary business logic - in this application, the Service Layer simply calls the Repository Layer
Controllers: Application logic for handling HttpRequests and formatting HttpResponses
Servlets: HttpServlet to listen for incoming requests and send corresponding responses
Utilities: JDBC Connection Class to configure credentials and make connections to the AWS RDS instance

Features
Session Tracking
HttpSession objects used to store and access session information (such as current logged in user and the professional development resource in question)
AJAX Calls
JavaScript is implemented for frent end design in order to send and receive asynchronous HTTP Requests and Responses
HTML Design
Bootstrap used for CSS
JavaScript used for DOM Manipulation
Testing
JUnit

JUnit
Some test cases implemented to ensure proper database connection and retrieval of information.

Further Development
Some appropriate imrpovements would be to implement a messaging service tied to employees, supervisors, and their development resources.
Implementation of file uploading is not functional yet.
Archiving of closed requests, while a table exists in the database, is not yet implemented.

Required Dependencies
The below dependencies are required in the pom.xml file in order to ensure proper execution.
Testing
JUnit v.4
Database Access
Java Servlet
Postgreql JDBC Driver
Other
Google Gson (for formatting/sending/receiving HttpRequest and HttpResponses
