package sg.edu.nus.iss.paf_day23.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Integer id;

    @NotEmpty(message="First Name is mandatory")
    private String firstName;

    @NotEmpty(message="Last Name is mandatory")
    private String lastName;
}
