package com.asib27.authentication.Payment;


import com.asib27.authentication.Transaction.Transaction;
import com.asib27.authentication.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CheckoutController {

    @Autowired
    TransactionService transactionService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        Transaction transaction = transactionService.getLatestTransaction();
        model.addAttribute("amount", transaction.getTotal_price() * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkout";
    }
}