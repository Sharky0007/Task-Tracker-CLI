# Task-Tracker-CLI

## About this Project:
This is a java based CLI project to manage tasks. User can perform CRUD operation from terminal.

## Features:
1. User can add a task.
2. User can update a task.
3. User can update the status("IN-PROGRESS" or "DONE") of a task.
4. User can delete a task.
5. User can get list of tasks.

## How to use it:
1. Clone the project.
```bash
git clone https://github.com/Sharky0007/Task-Tracker-CLI.git
 ```
2. Compile the code.
```bash
 javac *.java
 ```
3. run the commands to get the result.

## Commands:
```bash
# Add task:
  java TaskCLI.java add "add grocery4"

# Update task:
  java TaskCLI.java update 1 "Grocery done"

# Delete task:
  java TaskCLI.java delete 1

# Update status of task:
  java TaskCLI.java updateStatus 2 DONE

# Get all tasks:
  java TaskCLI.java list
```

# TODO:
Get List of task by status.
