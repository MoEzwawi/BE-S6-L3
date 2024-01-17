package MoEzwawi.BES6L2.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private LocalDate dateOfBirth;
    private String avatarUrl;
}
