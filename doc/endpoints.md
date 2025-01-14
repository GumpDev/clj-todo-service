# Endpoints

## Table of Contents

- [GET /todo](#get-todo)
- [GET /todo/:id](#get-todoid)
- [POST /todo](#post-todo)
- [PUT /todo/:id](#put-todoid)
- [PUT /todo/:id/done](#put-todoidstatus)
- [PUT /todo/:id/cancel](#delete-todoid)

## GET /todo

- Response: [ToDo](schemas.md#todo-schema)[]

Get all todos from the database and return

Response Example:
```
[
    {   
        "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
        "title":"test title 2",
        "description":"test description 2",
        "status":0
    }
]
```

## GET /todo/:id

- Response: [ToDo](schemas.md#todo-schema)

Get a specific ToDo by the id from database

Response Example:
```
{   
    "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
    "title":"test title 2",
    "description":"test description 2",
    "status":0
}
```

## POST /todo/

- Request Body: [ToDo](schemas.md#todo-schema) (without id and status)
- Response: [ToDo](schemas.md#todo-schema)

Create a new ToDo on database

Request Body Example:
```
{
    "title":"test title 2",
    "description":"test description 2",
}
```
Response Example:
```
{   
    "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
    "title":"test title 2",
    "description":"test description 2",
    "status":0
}
```

## PUT /todo/:id/done

- Response: [ToDo](schemas.md#todo-schema)

Updates a ToDo status to done on the database

Response Example:
```
{   
    "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
    "title":"test title 2",
    "description":"test description 2",
    "status":0
}
```

## PUT /todo/:id

- Request Body: { title: string, description: string }
- Response: [ToDo](schemas.md#todo-schema)

Updates a ToDo value on the database

Request Body Example:
```
{
    "title":"test title 3",
    "description":"test description 3",
}
```
Response Example:
```
{   
    "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
    "title":"test title 3",
    "description":"test description 3",
    "status":0
}
```

## PUT /todo/:id/cancel

- Response: [ToDo](schemas.md#todo-schema)

Updates cancel a todo on the database

Response Example:
```
{   
    "id":"5efaeac6-4841-464b-be49-68c88d903ce9",
    "title":"test title 2",
    "description":"test description 2",
    "status":2
}
```