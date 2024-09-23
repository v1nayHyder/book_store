package tech.harry.online_book_strore.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tech.harry.online_book_strore.utils.BaseEntity;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String  email;

    @Column(name = "password")
    private String password;

    @Column(name="user_role")
    private String role="USER";

    @Column(name="is_active")
    private boolean isActive=true;

}
