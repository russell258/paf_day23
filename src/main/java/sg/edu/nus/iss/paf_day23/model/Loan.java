package sg.edu.nus.iss.paf_day23.model;

import java.sql.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    private Integer id;

    private Integer customerId;

    @FutureOrPresent(message = "borrow date must be current or future date")
    private Date loanDate;

    @Future(message = "return date must be future date")
    private Date returnDate;
}
