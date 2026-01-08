package com.maksoud.honeybodger.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "daily_progress", uniqueConstraints = {
    @UniqueConstraint(columnNames = "date")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate date;

    @Column(nullable = false)
    @Builder.Default
    private Long userId = 1L;

    @Column(nullable = false, length = 20)
    @Builder.Default
    private String overallStatus = "MISSED";

    @Column
    private Integer productivityScore;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "dailyProgress", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<HabitCompletion> habitCompletions = new HashSet<>();

    public void addHabitCompletion(HabitCompletion habitCompletion) {
        habitCompletions.add(habitCompletion);
        habitCompletion.setDailyProgress(this);
    }

    public void removeHabitCompletion(HabitCompletion habitCompletion) {
        habitCompletions.remove(habitCompletion);
        habitCompletion.setDailyProgress(null);
    }
}
