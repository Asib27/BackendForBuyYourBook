package com.asib27.authentication.Transaction;

import com.asib27.authentication.Book.Book;
import com.asib27.authentication.Book.BookService;
import com.asib27.authentication.Locations.Location;
import com.asib27.authentication.Locations.LocationService;
import com.asib27.authentication.UserCloned.UserCloned;
import com.asib27.authentication.UserCloned.UserClonedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    @Autowired
    BookService bookService;
    @Autowired
    UserClonedService userClonedService;
    @Autowired
    LocationService locationService;

    @PostMapping("/add")
    public String addNewTransaction(@RequestBody Transaction transaction){
        transactionService.addTransaction(transaction);
        return "New transaction added!!";
    }

    @DeleteMapping("/delete/{tx_id}")
    public String deleteTransaction(@PathVariable Long id){
        transactionService.deleteTransaction(id);
        return " transaction deleted!!";
    }

    @GetMapping("/get/{tx_id}")
    public Transaction getTransactionById(@PathVariable Long tx_id){
        return transactionService.findTransactionByID(tx_id);
    }

    @GetMapping("/get")
    public List<Transaction> getAll(){
        return transactionService.getAllTransactions();
    }

    @GetMapping("/get/latest")
    public Transaction getLatest(){
        return transactionService.getLatestTransaction();
    }


    @PostMapping("/add/{tx_id}/user/{u_id}")
    public Transaction addUserToTransaction(@PathVariable Long tx_id,@PathVariable Long u_id){
        UserCloned user = userClonedService.getAnUser(u_id);
        Transaction transaction = transactionService.findTransactionByID(tx_id);
        transaction.setUser(user);
        return transactionService.addTransaction(transaction);
    }


    @PostMapping("/add/{tx_id}/location/{u_id}")
    public Transaction addLocationOfTransaction(@PathVariable Long tx_id,@PathVariable Long u_id){
        Location location = locationService.getALocation(u_id);
        Transaction transaction = transactionService.findTransactionByID(tx_id);
        transaction.setLocation(location);
        return transactionService.addTransaction(transaction);
    }





}