package programmingmagic.base;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public interface Service<T extends BaseEntity>  extends Serializable {

    T get(UUID uuid);

    List<T> getAll();

    T save(@Valid T entity);

    void delete(@NotNull T entity);
}
