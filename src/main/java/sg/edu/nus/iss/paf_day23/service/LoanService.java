package sg.edu.nus.iss.paf_day23.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.paf_day23.model.Customer;
import sg.edu.nus.iss.paf_day23.model.Loan;
import sg.edu.nus.iss.paf_day23.model.LoanDetails;
import sg.edu.nus.iss.paf_day23.model.Video;
import sg.edu.nus.iss.paf_day23.repository.CustomerRepo;
import sg.edu.nus.iss.paf_day23.repository.LoanDetailsRepo;
import sg.edu.nus.iss.paf_day23.repository.LoanRepo;
import sg.edu.nus.iss.paf_day23.repository.VideoRepo;

@Service
public class LoanService {
    @Autowired
    LoanRepo lRepo;

    @Autowired
    LoanDetailsRepo ldRepo;

    @Autowired
    CustomerRepo cRepo;

    @Autowired
    VideoRepo vRepo;

    @Transactional
    public Boolean loanVideo(Customer customer, List<Video> videos){
        Boolean bLoanSuccessful = false;

        //1. retrieve video records
        List<Video> allVideos = vRepo.findAll();

        //1b. check all videos available, count > 0
        Boolean bAvailable = true;

        for (Video video: videos){
            List<Video> filteredVideoList = allVideos.stream().filter(v -> v.getId().equals(video.getId()))
                                                    .collect(Collectors.toList());

            if (!filteredVideoList.isEmpty()){
                if (filteredVideoList.get(0).getAvailableCount()==0){
                    bAvailable = false;
                    throw new NoSuchElementException("Video not available");
                }else{
                    //reduce video quantity by 1 for loaning
                    Video updateVideoEnt = filteredVideoList.get(0);
                    updateVideoEnt.setAvailableCount(updateVideoEnt.getAvailableCount()-1);
                    vRepo.updateVideo(updateVideoEnt);
                }
            }
        }

        //can throw new exception here to test if transactional enforces rollback

        // 2. create loan record
        // 3. create loan details tying to the loan

        if (bAvailable){
            Loan loan = new Loan();
            loan.setCustomerId(customer.getId());
            loan.setLoanDate(Date.valueOf(LocalDate.now()));

            Integer createdLoanId = lRepo.createLoan(loan);

            //throw new exception to test if transactional enforces rollback

            for (Video video: videos){
                LoanDetails loanDetails = new LoanDetails();
                loanDetails.setLoanId(createdLoanId);
                loanDetails.setVideoId(video.getId());

                ldRepo.createLoanDetails(loanDetails);
            }
            // throw new exception to test if transactional enforces rollback
            bLoanSuccessful=true;


        }

        return bLoanSuccessful;

    }

}
