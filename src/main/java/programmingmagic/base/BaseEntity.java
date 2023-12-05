package programmingmagic.base;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {
    @Id
    @Builder.Default
    protected UUID uuid = UUID.randomUUID();

    @Builder.Default
    protected LocalDateTime entityCreationDate = LocalDateTime.now();
    @Builder.Default
    protected LocalDateTime entityModificationDate = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        return uuid != null && uuid.equals(((BaseEntity) o).uuid);
    }

    public UUID getUuid() {
        return uuid;
    }
}


