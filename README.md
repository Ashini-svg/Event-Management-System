# Event Management System

## Overview

The **Event Management System** is a Java-based desktop application developed as a group project to provide an organized way of managing events, venues, attendees, and bookings.

The system was designed to demonstrate the practical application of **Object-Oriented Programming, data structures and algorithms, database management, and collaborative software development**.

The application combines a Java-based interface with a relational database and a range of custom data structures and algorithms to store, search, sort, organize, and process information efficiently.

The project was developed collaboratively using **Git and GitHub**, with team members contributing to different areas of the system.

---

## Key Features

The Event Management System provides functionality for:

* Event management
* Venue management
* Attendee management
* Booking management
* Event and venue information handling
* Database-backed data storage
* Searching and sorting of data
* Graph-based data representation
* Breadth-First Search (BFS)
* Depth-First Search (DFS)
* Hash-based data management
* Linear data structures
* Binary Search Tree (BST) and AVL Tree implementations
* Waiting-list management
* Activity-history management
* Input validation and error handling
* Modular and structured application design

---

## Technologies Used

| Technology                            | Purpose                                                                        |
| ------------------------------------- | ------------------------------------------------------------------------------ |
| **Java**                              | Main programming language used to develop the application                      |
| **Eclipse IDE**                       | Used for development, debugging, and testing                                   |
| **SQL**                               | Used to define and manage the relational database                              |
| **JDBC**                              | Used to connect the Java application with the database                         |
| **Relational Database**               | Used for persistent storage and management of application data                 |
| **Git**                               | Version control and source code management                                     |
| **GitHub**                            | Collaborative development, branch management, and code integration             |
| **Object-Oriented Programming (OOP)** | Used to design and organize application classes and components                 |
| **Data Structures & Algorithms**      | Used for efficient data storage, searching, sorting, traversal, and processing |

---

## Data Structures & Algorithms

Data structures and algorithms are a major part of the Event Management System. Different structures and algorithms were implemented based on the requirements of the application to improve data organization, searching, sorting, and processing.

### Searching and Sorting Algorithms

The project includes:

* **Bubble Sort**
* **Insertion Sort**
* **Merge Sort**
* **Quick Sort**
* **Selection Sort**
* **Linear Search**

These algorithms demonstrate different approaches to sorting and searching application data.

### Graph Data Structures and Algorithms

The project includes:

* **Graph**
* **GraphNode**
* **Breadth-First Search (BFS)**
* **Depth-First Search (DFS)**
* **VenueGraphManager**

Graphs are used to represent relationships and connections within the system, while BFS and DFS provide methods for traversing and exploring those connections.

### Hashing

The system includes custom hashing implementations such as:

* **HashTable**
* **HashNode**
* **AttendeeHashManager**
* **SetADT**

These structures provide efficient approaches to storing, managing, and retrieving information.

### Linear Data Structures

The following linear data structures and supporting classes were implemented:

* **LinkedList**
* **ListNode**
* **Stack**
* **Queue**
* **CategoryArray**
* **ActivityHistory**
* **AttendeeManager**
* **WaitingListManager**

These structures are used in different parts of the application to manage ordered data, activity records, attendees, and waiting lists.

### Tree Data Structures

The project also includes tree-based structures:

* **Binary Search Tree (BST)**
* **BSTNode**
* **AVL Tree**
* **AVLNode**
* **EventBSTManager**
* **EventAVLManager**

These structures demonstrate different approaches to organizing and efficiently accessing event-related data.

Overall, the implementation of these structures and algorithms demonstrates how core computer science concepts can be applied to a practical software application.

---

## Database

The Event Management System uses a **relational database** to provide persistent storage for application data.

Database operations are organized using **Data Access Object (DAO)** classes, keeping database-related operations separate from the rest of the application.

The database layer includes:

* **DBConnection.java** – Establishes the connection between the Java application and the database.
* **AttendeeDAO.java** – Handles attendee-related database operations.
* **BookingDAO.java** – Handles booking-related database operations.
* **EventDAO.java** – Handles event-related database operations.
* **VenueDAO.java** – Handles venue-related database operations.
* **schema.sql** – Contains the database schema required to create the necessary database structures.

This approach allows the system to store and retrieve application data while keeping the database layer organized and maintainable.

---

## Project Structure

The project follows a modular package structure, with each package responsible for a specific part of the application.

```text
EventManagementSystem/
│
├── src/
│   │
│   ├── com.eventmgmt/
│   │   ├── MainApplication.java
│   │   └── SubMenuHandler.java
│   │
│   ├── com.eventmgmt.algorithms/
│   │   ├── BubbleSort.java
│   │   ├── InsertionSort.java
│   │   ├── LinearSearch.java
│   │   ├── MergeSort.java
│   │   ├── QuickSort.java
│   │   └── SelectionSort.java
│   │
│   ├── com.eventmgmt.database/
│   │   ├── AttendeeDAO.java
│   │   ├── BookingDAO.java
│   │   ├── DBConnection.java
│   │   ├── EventDAO.java
│   │   └── VenueDAO.java
│   │
│   ├── com.eventmgmt.model/
│   │   ├── Attendee.java
│   │   ├── Booking.java
│   │   ├── Event.java
│   │   └── Venue.java
│   │
│   ├── com.eventmgmt.structures.graphs/
│   │   ├── BFS.java
│   │   ├── DFS.java
│   │   ├── Graph.java
│   │   ├── GraphNode.java
│   │   └── VenueGraphManager.java
│   │
│   ├── com.eventmgmt.structures.hashing/
│   │   ├── AttendeeHashManager.java
│   │   ├── HashNode.java
│   │   ├── HashTable.java
│   │   └── SetADT.java
│   │
│   ├── com.eventmgmt.structures.linear/
│   │   ├── ActivityHistory.java
│   │   ├── AttendeeManager.java
│   │   ├── CategoryArray.java
│   │   ├── LinkedList.java
│   │   ├── ListNode.java
│   │   ├── Queue.java
│   │   ├── Stack.java
│   │   └── WaitingListManager.java
│   │
│   └── com.eventmgmt.structures.trees/
│       ├── AVLNode.java
│       ├── AVLTree.java
│       ├── BST.java
│       ├── BSTNode.java
│       ├── EventAVLManager.java
│       └── EventBSTManager.java
│
└── schema.sql
```

### Package Description

| Package                              | Description                                                                                       |
| ------------------------------------ | ------------------------------------------------------------------------------------------------- |
| **com.eventmgmt**                    | Contains the main application entry point and menu handling.                                      |
| **com.eventmgmt.algorithms**         | Contains searching and sorting algorithm implementations.                                         |
| **com.eventmgmt.database**           | Contains database connectivity and DAO classes for database operations.                           |
| **com.eventmgmt.model**              | Contains the main entities used by the system, including events, venues, attendees, and bookings. |
| **com.eventmgmt.structures.graphs**  | Contains graph structures, graph managers, and BFS/DFS traversal algorithms.                      |
| **com.eventmgmt.structures.hashing** | Contains hashing data structures and attendee hash management.                                    |
| **com.eventmgmt.structures.linear**  | Contains linear data structures and supporting management classes.                                |
| **com.eventmgmt.structures.trees**   | Contains BST and AVL tree implementations and event management classes.                           |
| **schema.sql**                       | Defines the database schema used by the application.                                              |

---

## Team Collaboration

The project was developed collaboratively using **Git and GitHub**.

Git branches and pull requests were used to manage individual work, track changes, review contributions, and integrate completed components into the main project.

Working with GitHub also provided practical experience in collaborative software development, version control, and managing a shared codebase.

---

## Project Outcomes

The development of this project provided practical experience in:

* Java application development
* Object-Oriented Programming
* Database connectivity and management
* Data Access Object (DAO) design
* Searching and sorting algorithms
* Graph implementation and traversal
* Hashing
* Linear data structures
* Binary Search Trees and AVL Trees
* Software structure and modular design
* Git and GitHub collaboration
* Debugging and testing
* Team-based software development

---

## Future Enhancements

Although the current system has been completed, possible future enhancements include:

* Improving the graphical user interface
* Expanding database functionality
* Adding user authentication and role-based access
* Providing advanced event filtering and reporting
* Adding additional analytics features
* Developing a web-based or mobile version of the system

---

## Project Status

**Completed ✅**

The Event Management System has been successfully developed and completed as a university group project.

---

## Contributors

This project was developed collaboratively by **Group 16**.

* **Himasha Widhanapathirana**
* **Lehan Luchitha**
* **Dhamsana Dissanayeka**
* **Ashini Samarasingha**
* **Oshan Priyawansha**
* **Apoorwa Chandrarathna**


---

## License

This project was developed for **academic purposes** as part of university coursework. It is not intended for commercial use.

