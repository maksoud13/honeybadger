# Environment Configuration Guide

This project uses centralized environment variables for easy deployment and configuration management.

## Files Overview

- **`.env`** - Main environment configuration file (root directory)
- **`.env.example`** - Template for the `.env` file (reference only)
- **`front/.env.example`** - Template for frontend environment variables
- **`docker-compose.yml`** - Updated to use environment variables from `.env`
- **`backend/src/main/resources/application.properties`** - Updated to use environment variables

## Setup Instructions

### 1. Root `.env` File

The root `.env` file contains all configuration values for your entire stack:

```env
# Database Configuration
DB_HOST=db
DB_PORT=5432
DB_NAME=mydb
DB_USER=myuser
DB_PASSWORD=mypassword

# Backend Configuration
BACKEND_PORT=8080
SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_APPLICATION_NAME=honeybodger

# Frontend Configuration
FRONTEND_PORT=3000
NEXT_PUBLIC_API_URL=http://localhost:8080

# Docker Compose Ports
BACKEND_EXTERNAL_PORT=8080
FRONTEND_EXTERNAL_PORT=3001
```

**To change any value**, simply edit the `.env` file and restart your Docker containers.

### 2. Frontend `.env.local` File

For local development, create a `front/.env.local` file:

```env
NEXT_PUBLIC_API_URL=http://localhost:8080
```

This file is **already in `.gitignore`** and won't be committed.

### 3. Running with Docker Compose

The `docker-compose.yml` now automatically reads from the root `.env` file:

```bash
docker-compose up
```

All services (PostgreSQL, Backend, Frontend) will use the values from `.env`.

## Environment Variables Reference

| Variable | Service | Description | Default |
|----------|---------|-------------|---------|
| `DB_HOST` | PostgreSQL | Database host | `db` |
| `DB_PORT` | PostgreSQL | Database port | `5432` |
| `DB_NAME` | PostgreSQL | Database name | `mydb` |
| `DB_USER` | PostgreSQL | Database user | `myuser` |
| `DB_PASSWORD` | PostgreSQL | Database password | `mypassword` |
| `BACKEND_PORT` | Backend | Internal backend port | `8080` |
| `BACKEND_EXTERNAL_PORT` | Docker | External backend port | `8080` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Backend | Hibernate DDL mode | `update` |
| `SPRING_APPLICATION_NAME` | Backend | Application name | `honeybodger` |
| `FRONTEND_PORT` | Frontend | Internal frontend port | `3000` |
| `FRONTEND_EXTERNAL_PORT` | Docker | External frontend port | `3001` |
| `NEXT_PUBLIC_API_URL` | Frontend | Backend API URL | `http://localhost:8080` |

## Deployment Scenarios

### Local Development
- Use the default `.env` values
- Create `front/.env.local` for frontend-specific overrides

### Staging/Production
1. Copy `.env.example` to `.env`
2. Update all values for your environment:
   ```bash
   DB_PASSWORD=your_secure_password
   DB_HOST=your_db_host
   NEXT_PUBLIC_API_URL=https://api.yourdomain.com
   ```
3. Run: `docker-compose up`

### Environment-Specific Files (Optional)
You can create multiple `.env` files:
- `.env.development`
- `.env.staging`
- `.env.production`

Then run: `docker-compose --env-file .env.production up`

## Important Notes

- **Never commit `.env` files** with sensitive data to version control
- Always use `.env.example` as a template
- The `.env` file is already in `.gitignore` (if not, add it)
- Frontend `.env.local` is already in `.gitignore`
- All environment variables are automatically passed to Docker services
- Backend reads variables from both Docker environment and `application.properties`

## Quick Reference: Changing Values

To change any configuration:

1. Edit the root `.env` file
2. Save the file
3. Restart Docker: `docker-compose down && docker-compose up`

That's it! All services will use the new values automatically.
