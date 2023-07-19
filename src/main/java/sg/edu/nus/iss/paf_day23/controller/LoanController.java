package sg.edu.nus.iss.paf_day23.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day23.model.Customer;
import sg.edu.nus.iss.paf_day23.model.LoanDetails;
import sg.edu.nus.iss.paf_day23.model.Video;
import sg.edu.nus.iss.paf_day23.repository.CustomerRepo;
import sg.edu.nus.iss.paf_day23.repository.LoanRepo;
import sg.edu.nus.iss.paf_day23.repository.VideoRepo;
import sg.edu.nus.iss.paf_day23.service.LoanService;

@RestController
@RequestMapping("/api/loan")
public class LoanController {
    @Autowired
    LoanService loanSvc;
    
    @Autowired
    CustomerRepo cRepo;

    @Autowired
    LoanDetails ldRepo;

    @Autowired
    LoanRepo lRepo;

    @Autowired
    VideoRepo vRepo;

    @PostMapping
    public ResponseEntity<Boolean> loanTransaction(@RequestBody Customer customer, @RequestBody List<Video> videos){
        Boolean loanSuccess =  false;
        loanSuccess = loanSvc.loanVideo(customer, videos);

        if (loanSuccess){
            return ResponseEntity.ok().body(loanSuccess);
        }

        return new ResponseEntity<>(loanSuccess, HttpStatus.NOT_FOUND);
    }

}
