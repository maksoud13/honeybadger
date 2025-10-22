# Database Initialization

This folder contains database initialization scripts for the Honeybadger project.

## Files

- **`init.sql`** - Main database initialization script that creates the schema and all tables

## Database Configuration

- **Database Name**: `honeybadger_DB`
- **Username**: `maksoud`
- **Password**: `123456`

## Tables Created

1. **daily_progress** - Tracks daily productivity and status
2. **habit_completion** - Tracks completion of individual habits
3. **user_settings** - Stores user preferences and settings
4. **habit_types** - Defines available habit types

## Usage

### First Time Setup

1. Ensure the `DB_INIT_FIRST_RUN` flag is set to `true` in `.env`
2. Run Docker Compose:
   ```bash
   docker-compose up
   ```
3. The database will be automatically initialized with all tables

### Manual Initialization

If you need to manually run the initialization script:

```bash
# Connect to PostgreSQL container
docker exec -it honeybadger-db psql -U postgres

# Then run:
\i /docker-entrypoint-initdb.d/init.sql
```

## Notes

- The script includes indexes for optimal query performance
- All tables use timestamps with timezone support
- Foreign key constraints are properly configured with CASCADE delete
- The script is idempotent and safe to run multiple times
