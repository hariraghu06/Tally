package accounts.sheet.tally.controller;

import accounts.sheet.tally.model.ApiTransactionResponse;
import accounts.sheet.tally.model.ExpenseData;
import accounts.sheet.tally.service.TallyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tally")
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class TallyController {

    @Autowired
    TallyService service;

    @PostMapping("/addExpense")
    public ApiTransactionResponse addExpense(@RequestBody ExpenseData expenseData) {
        log.info("TallyController --> Adding expense");
        return service.addExpense(expenseData);
    }


    @GetMapping("/fetchTransData/{account}")
    public ApiTransactionResponse fetchTransactionDetails(@PathVariable String account) {
        log.info("TallyController --> Fetching transaction data for {}",account);
        return service.fetchTransactionDetails(account);
    }

    @GetMapping("/accountList")
    public ApiTransactionResponse fetchAccountList(){
        log.info("TallyController --> Fetching account list");
        return service.fetchAccountList();
    }

}
