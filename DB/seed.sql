-- Seed data for Honeybadger Database
-- This file contains default data for habit types and user settings

-- Insert default habit types
INSERT INTO habit_types (name, description, category, is_active) VALUES
    ('prayer', 'Daily prayers completion', 'spiritual', true),
    ('coding', 'Java/Spring learning and coding', 'professional', true),
    ('job_search', 'LinkedIn and job applications', 'professional', true),
    ('fitness', '30-day fitness challenge', 'health', true),
    ('reading', 'Technical reading/studying', 'professional', true)
ON CONFLICT (name) DO NOTHING;

-- Insert default user settings
INSERT INTO user_settings (user_id, default_habits, timezone) VALUES
    (1, '["prayer", "coding", "job_search", "fitness"]'::JSONB, 'Africa/Cairo')
ON CONFLICT (user_id) DO UPDATE SET
    default_habits = EXCLUDED.default_habits,
    timezone = EXCLUDED.timezone;
