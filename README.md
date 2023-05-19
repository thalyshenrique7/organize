# Project: Task List (Organize-App)

#### The Organize App, developed by me, is a task management project aimed at helping users effectively organize their daily routines. Built using Java and Spring Boot, this application incorporates Spring Security to ensure the utmost security for user data. Following the Model-View-Controller (MVC) architecture, it leverages MongoDB as the database, hosted on an AWS server.

#### To optimize task retrieval based on their status, I implemented the Chain of Responsibility design pattern. This allows for efficient searching and filtering of tasks. Additionally, I incorporated the Observer pattern to ensure real-time notifications whenever data is modified.

#### To ensure code quality, I implemented comprehensive testing using JUnit and Mockitos frameworks. This guarantees the reliability and stability of the application. Furthermore, Swagger integration enhances the documentation of API endpoints, providing a clear and user-friendly interface for developers and users alike.

#### The Organize App is a powerful tool that empowers users to streamline their tasks, manage their routines efficiently, and stay on top of their daily responsibilities. With its robust security measures, reliable functionality, and intuitive design, it offers a seamless user experience for optimizing productivity and organization.

> Status: Developing ⚠️

Entity UserModel:

![UML UserModel](https://github.com/thalyshenrique7/organize-app/assets/100730757/12be772a-1c0f-40ad-b492-72ebc0a2aa39)

#### Constraints in UserModel:
+ All fields is required
+ CPF valid is required
+ CPF is unique
+ Email valid is required

Entity TaskModel:

![UML TaskModel ](https://github.com/thalyshenrique7/organize-app/assets/100730757/115b91fd-dbfa-4dfe-beb7-3ae615e6c2a3)

+ I created the package "Impl" with <b>UserServiceImpl</b> and <b>TaskServiceImpl</b> for separate the interface definition from the implementation logic for when create Mocks for test class.

> Technologies Used:

<table>
<tr align="center">
<td>Java</td>
<td>Spring Boot (Web, Validation, Security)</td>
<td>MongoDB</td>
<td>Swagger</td>
<td>JUnit</td>
</tr>

<tr align="center">
<td>11</td>
<td>2.7.10</td>
<td>6.0.5</td>
<td>2.9.2</td>
<td>5</td>
</tr>
</table>

### Author
> Thalys Henrique

https://www.linkedin.com/in/thalyshenrique7/
