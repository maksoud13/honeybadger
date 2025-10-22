-- Create database
CREATE DATABASE honeybadger_DB;

-- Connect to the database
\c honeybadger_DB;

-- Create tables
CREATE TABLE daily_progress (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL UNIQUE,
    user_id BIGINT NOT NULL DEFAULT 1,
    overall_status VARCHAR(20) NOT NULL DEFAULT 'MISSED',
    productivity_score INTEGER CHECK (productivity_score BETWEEN 1 AND 10),
    notes TEXT,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE habit_completion (
    id BIGSERIAL PRIMARY KEY,
    daily_progress_id BIGINT NOT NULL REFERENCES daily_progress(id) ON DELETE CASCADE,
    habit_type VARCHAR(50) NOT NULL,
    completed BOOLEAN NOT NULL DEFAULT false,
    completed_at TIMESTAMP WITH TIME ZONE,
    notes TEXT,
    UNIQUE(daily_progress_id, habit_type)
);

CREATE TABLE user_settings (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL DEFAULT 1,
    default_habits JSONB,
    timezone VARCHAR(50) DEFAULT 'UTC',
    week_start_day INTEGER DEFAULT 1,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE habit_types (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    category VARCHAR(20) NOT NULL,
    is_active BOOLEAN DEFAULT true
);

-- Create indexes for better query performance
CREATE INDEX idx_daily_progress_user_id ON daily_progress(user_id);
CREATE INDEX idx_daily_progress_date ON daily_progress(date);
CREATE INDEX idx_habit_completion_daily_progress_id ON habit_completion(daily_progress_id);
CREATE INDEX idx_user_settings_user_id ON user_settings(user_id);
CREATE INDEX idx_habit_types_name ON habit_types(name);

-- Seed data: Insert default habit types
INSERT INTO habit_types (name, description, category, is_active) VALUES
    ('prayer', 'Daily prayers completion', 'spiritual', true),
    ('coding', 'Java/Spring learning and coding', 'professional', true),
    ('job_search', 'LinkedIn and job applications', 'professional', true),
    ('fitness', '30-day fitness challenge', 'health', true),
    ('reading', 'Technical reading/studying', 'professional', true)
ON CONFLICT (name) DO NOTHING;

-- Seed data: Insert default user settings
INSERT INTO user_settings (user_id, default_habits, timezone) VALUES
    (1, '["prayer", "coding", "job_search", "fitness"]'::JSONB, 'Africa/Cairo')
ON CONFLICT (user_id) DO UPDATE SET
    default_habits = EXCLUDED.default_habits,
    timezone = EXCLUDED.timezone;
