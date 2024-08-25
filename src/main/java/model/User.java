package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Represents a user in the system.
 * <p>
 * The {@code User} class is used to model a user entity with fields for user ID, username, password, and role.
 * Lombok annotations are used to automatically generate boilerplate code such as getters, setters, constructors, and
 * methods like {@code toString()}, {@code equals()}, and {@code hashCode()}.
 * </p>
 *
 * @see lombok.Data
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public User(String role, String password, String userName) {
        this.role = role;
        this.password = password;
        this.userName = userName;
    }

    /**
     * The unique identifier for the user.
     * <p>
     * Initialized to {@code 0} by default.
     * </p>
     */
    private Integer id = 0;

    /**
     * The username of the user.
     * <p>
     * Represents the user's login name.
     * </p>
     */
    private String userName;

    /**
     * The password of the user.
     * <p>
     * Should be stored securely, ideally hashed for security reasons.
     * </p>
     */
    private String password;

    /**
     * The role assigned to the user.
     * <p>
     * Determines the user's permissions or access level (e.g., "admin", "user").
     * </p>
     */
    private String role;
}
