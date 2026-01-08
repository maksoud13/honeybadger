package com.maksoud.honeybodger.Entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "user_settings", uniqueConstraints = {
    @UniqueConstraint(columnNames = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Builder.Default
    private Long userId = 1L;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<String> defaultHabits;

    @Column(length = 50)
    @Builder.Default
    private String timezone = "UTC";

    @Column
    @Builder.Default
    private Integer weekStartDay = 1;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    public void addDefaultHabit(String habit) {
        if (defaultHabits == null) {
            defaultHabits = new java.util.ArrayList<>();
        }
        if (!defaultHabits.contains(habit)) {
            defaultHabits.add(habit);
        }
    }

    public void removeDefaultHabit(String habit) {
        if (defaultHabits != null) {
            defaultHabits.remove(habit);
        }
    }

    public boolean hasDefaultHabit(String habit) {
        return defaultHabits != null && defaultHabits.contains(habit);
    }
}
