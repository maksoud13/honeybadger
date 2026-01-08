# Entity Package Documentation

This package contains all JPA entity classes that map to the database tables in the Honeybadger application.

## Entities Overview

### 1. HabitType
Defines the types of habits that users can track.

**Fields:**
- `id` - Primary key (auto-generated)
- `name` - Unique habit name (e.g., "prayer", "coding")
- `description` - Description of the habit
- `category` - Category classification (e.g., "spiritual", "professional", "health")
- `isActive` - Boolean flag to enable/disable habit type

**Key Features:**
- Unique constraint on `name`
- Default `isActive` value is `true`

**Example:**
```java
HabitType prayer = HabitType.builder()
    .name("prayer")
    .description("Daily prayers completion")
    .category("spiritual")
    .isActive(true)
    .build();
```

---

### 2. DailyProgress
Tracks the overall productivity and status for each day.

**Fields:**
- `id` - Primary key (auto-generated)
- `date` - Date of the progress record (unique)
- `userId` - User ID (default: 1)
- `overallStatus` - Daily status (e.g., "MISSED", "PARTIAL", "COMPLETE")
- `productivityScore` - Score between 1-10
- `notes` - Additional notes
- `createdAt` - Timestamp of creation (auto-set)
- `updatedAt` - Timestamp of last update (auto-set)
- `habitCompletions` - One-to-many relationship with HabitCompletion

**Key Features:**
- Unique constraint on `date`
- Automatic timestamp management
- Cascade delete for related habit completions
- Helper methods: `addHabitCompletion()`, `removeHabitCompletion()`

**Example:**
```java
DailyProgress today = DailyProgress.builder()
    .date(LocalDate.now())
    .userId(1L)
    .overallStatus("PARTIAL")
    .productivityScore(7)
    .notes("Good progress today")
    .build();
```

---

### 3. HabitCompletion
Records the completion status of individual habits for a specific day.

**Fields:**
- `id` - Primary key (auto-generated)
- `dailyProgress` - Foreign key to DailyProgress (many-to-one)
- `habitType` - Name of the habit (e.g., "prayer", "coding")
- `completed` - Boolean flag indicating completion
- `completedAt` - Timestamp when the habit was completed
- `notes` - Additional notes about the completion
- `createdAt` - Timestamp of creation (auto-set)

**Key Features:**
- Unique constraint on (`daily_progress_id`, `habit_type`)
- Lazy loading of DailyProgress
- Cascade delete from parent DailyProgress
- Automatic timestamp management

**Example:**
```java
HabitCompletion prayerCompletion = HabitCompletion.builder()
    .dailyProgress(today)
    .habitType("prayer")
    .completed(true)
    .completedAt(OffsetDateTime.now())
    .notes("Morning prayers completed")
    .build();
```

---

### 4. UserSettings
Stores user preferences and configuration.

**Fields:**
- `id` - Primary key (auto-generated)
- `userId` - User ID (unique, default: 1)
- `defaultHabits` - JSONB array of default habit names
- `timezone` - User's timezone (default: "UTC")
- `weekStartDay` - Day of week when week starts (default: 1 for Monday)
- `createdAt` - Timestamp of creation (auto-set)

**Key Features:**
- JSONB support for flexible habit storage
- Unique constraint on `userId`
- Helper methods:
  - `addDefaultHabit(String habit)` - Add a habit to defaults
  - `removeDefaultHabit(String habit)` - Remove a habit from defaults
  - `hasDefaultHabit(String habit)` - Check if habit exists in defaults

**Example:**
```java
UserSettings settings = UserSettings.builder()
    .userId(1L)
    .defaultHabits(Arrays.asList("prayer", "coding", "job_search", "fitness"))
    .timezone("Africa/Cairo")
    .weekStartDay(1)
    .build();
```

---

## Relationships

```
UserSettings (1)
    ↓
    └─→ User (1:1)

HabitType (1)
    ↓
    └─→ HabitCompletion (1:N)

DailyProgress (1)
    ↓
    └─→ HabitCompletion (1:N)
```

## Annotations Used

- `@Entity` - Marks class as JPA entity
- `@Table` - Specifies table name and constraints
- `@Id` - Primary key
- `@GeneratedValue` - Auto-generation strategy
- `@Column` - Column configuration
- `@ManyToOne` - Many-to-one relationship
- `@OneToMany` - One-to-many relationship
- `@JoinColumn` - Foreign key column
- `@CreationTimestamp` - Auto-set on creation
- `@UpdateTimestamp` - Auto-set on update
- `@Type(JsonBinaryType.class)` - JSONB support
- `@UniqueConstraint` - Unique constraints
- `@Data` - Lombok annotation for getters/setters
- `@Builder` - Lombok builder pattern
- `@NoArgsConstructor` - Lombok no-arg constructor
- `@AllArgsConstructor` - Lombok all-args constructor

## Dependencies

- **Lombok** - For reducing boilerplate code
- **Hibernate** - JPA implementation
- **hypersistence-utils** - For JSONB support

## Usage in Repositories and Services

These entities are used with Spring Data JPA repositories:

```java
@Repository
public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {
    Optional<DailyProgress> findByDate(LocalDate date);
    List<DailyProgress> findByUserIdOrderByDateDesc(Long userId);
}
```

## Best Practices

1. **Always use builder pattern** for creating entities
2. **Lazy load** relationships to avoid N+1 queries
3. **Use cascade delete** carefully to prevent unintended deletions
4. **Validate data** in service layer before persisting
5. **Use DTOs** for API responses instead of entities directly
6. **Handle timestamps** automatically with annotations
