package sg.edu.nus.iss.paf_day23.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo {
    @Autowired
    JdbcTemplate jdbc;

    private final String findAllSql = "select id, first_name, last_name from customer";

    private final String findByIdSql = "select * from customer where id = ?";

    private final String insertSql = "insert into customer values (null, ?, ?)";

    private String updateSql = "update customer set first_name =?, last_name = ? where id = ?";

    

}
