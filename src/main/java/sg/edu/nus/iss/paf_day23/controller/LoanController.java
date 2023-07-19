package sg.edu.nus.iss.paf_day23.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.paf_day23.model.Customer;
import sg.edu.nus.iss.paf_day23.model.Video;
import sg.edu.nus.iss.paf_day23.repository.CustomerRepo;
import sg.edu.nus.iss.paf_day23.repository.LoanDetailsRepo;
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
    LoanDetailsRepo ldRepo;

    @Autowired
    LoanRepo lRepo;

    @Autowired
    VideoRepo vRepo;

    @PostMapping
    public Boolean loanTransaction(@RequestBody Map<String,Object> body){
        Boolean loanSuccess =  false;
        System.out.println(body.get("customer"));
        System.out.println(body.get("videos"));
        Customer customer = new Customer();
        customer.setId(body.get("customer").("id"));
        body.get("customer");
        Video video = body.get("videos");
        loanSuccess = loanSvc.loanVideo(body.get("customer"), body.get("videos"));

        return loanSuccess;
    }

}
