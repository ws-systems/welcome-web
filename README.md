Welcome Web Interface
=====================

Web Interface for the Welcome visitor tracking and analytics system. While not required, it is recommended that the Web Server be run in conjunction with the Worker to ensure that jobs are scheduled and completed as necessary, and to prevent duplicate job execution.

## Dev DB
```bash
docker run --name postgres -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres
```