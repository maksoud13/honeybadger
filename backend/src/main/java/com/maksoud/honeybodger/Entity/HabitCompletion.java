package com.maksoud.honeybodger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "habit_completion", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"daily_progress_id", "habit_type"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabitCompletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "daily_progress_id", nullable = false)
    private DailyProgress dailyProgress;

    @Column(nullable = false, length = 50)
    private String habitType;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completed = false;

    @Column
    private OffsetDateTime completedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
