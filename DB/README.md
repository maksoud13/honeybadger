# Database Initialization

This folder contains database initialization scripts for the Honeybadger project.

## Files

- **`init.sql`** - Main database initialization script that creates the schema, all tables, and seeds default data
- **`seed.sql`** - Separate seed data file (included in init.sql)

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

## Seed Data

The database is automatically seeded with default data on initialization:

### Habit Types
- **prayer** - Daily prayers completion (spiritual)
- **coding** - Java/Spring learning and coding (professional)
- **job_search** - LinkedIn and job applications (professional)
- **fitness** - 30-day fitness challenge (health)
- **reading** - Technical reading/studying (professional)

### User Settings
- **User ID 1** - Default user with timezone set to `Africa/Cairo`
- **Default Habits** - prayer, coding, job_search, fitness

## Notes

- The script includes indexes for optimal query performance
- All tables use timestamps with timezone support
- Foreign key constraints are properly configured with CASCADE delete
- The script is idempotent and safe to run multiple times
- Seed data uses `ON CONFLICT` clauses to prevent duplicate key errors on re-runs
