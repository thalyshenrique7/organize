# Project: Task List (Organize-App)

#### The Organize App, developed by me, is a task management project aimed at helping users effectively organize their daily routines. Built using Java and Spring Boot, this application incorporates Spring Security to ensure the utmost security for user data. Following the Model-View-Controller (MVC) architecture, it leverages MongoDB as the database, hosted on an AWS server.

#### To optimize task retrieval based on their status, I implemented the Chain of Responsibility design pattern. This allows for efficient searching and filtering of tasks. Additionally, I incorporated the Observer pattern to ensure real-time notifications whenever data is modified.

#### To ensure code quality, I implemented comprehensive testing using JUnit and Mockitos frameworks. This guarantees the reliability and stability of the application. Furthermore, Swagger integration enhances the documentation of API endpoints, providing a clear and user-friendly interface for developers and users alike.

#### The Organize App is a powerful tool that empowers users to streamline their tasks, manage their routines efficiently, and stay on top of their daily responsibilities. With its robust security measures, reliable functionality, and intuitive design, it offers a seamless user experience for optimizing productivity and organization.

> Status: Developing ⚠️

Entity UserModel:

![UML UserModel](https://github.com/thalyshenrique7/organize-app/assets/100730757/e07f7d07-7108-4402-8e65-dc424b5eb29c)

#### Constraint in UserModel: All fields is required

Entity TaskModel:

![UML TaskModel ](https://github.com/thalyshenrique7/organize-app/assets/100730757/4175cf09-0045-4db1-8dee-59054b333ebc)

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
