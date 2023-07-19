package sg.edu.nus.iss.paf_day23.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.paf_day23.model.Loan;

@Repository
public class LoanRepo {
    @Autowired
    JdbcTemplate jdbc;

    private final String selectSQL = "select * from loan";

    private final String insertSQL = "insert into loan values (null, ?, ?, ?)";

    public List<Loan> findAllLoans(){
        return jdbc.query(selectSQL,BeanPropertyRowMapper.newInstance(Loan.class));
    }

    public Integer createLoan(Loan loan){
        KeyHolder generatedKey = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                PreparedStatement ps = con.prepareStatement(insertSQL, new String[] {"id"});
                ps.setInt(1, loan.getCustomerId());
                ps.setDate(2, loan.getLoanDate());
                ps.setDate(3, loan.getReturnDate());
                return ps;
            }
        };

        jdbc.update(psc,generatedKey);

        Integer iResult = generatedKey.getKey().intValue();
        return iResult;
    }

}
