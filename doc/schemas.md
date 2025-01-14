# Schemas

## Table of Contents

- [ToDo Status](#todo-status)
- [ToDo Schema](#todo-schema)

## ToDo Status
- 0 - To do
- 1 - Done
- 2 - Cancelled

## ToDo Schema

- id - String -> ToDo identification, a UUID parsed to String
- title - String -> ToDo title
- description - String -> ToDo description (optional)
- status - [ToDo Status](#todo-status) -> ToDo Status