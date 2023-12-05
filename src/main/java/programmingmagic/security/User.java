package programmingmagic.security;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import programmingmagic.base.BaseEntity;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Table(name = "app_user")
public class User extends BaseEntity {

    private String login;
    private String password;


    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "uuid"))
    @Column(name = "roles")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;
}
