package playground.entityinheritance.creditcard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/credit-card-payment")
    public ResponseEntity<CreditCardPayment> createCreditCardPayment(@RequestBody CreditCardPayment ccPayment) {
        paymentService.save();
        CreditCardPayment savedPayment = paymentService.saveCreditCardPayment(ccPayment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @PostMapping("/cash-payment")
    public ResponseEntity<CashPayment> createCashPayment(@RequestBody CashPayment cashPayment) {
        CashPayment savedPayment = paymentService.saveCashPayment(cashPayment);
        return new ResponseEntity<>(savedPayment, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Payment> getAllTablePerClassEntities() {
        return paymentService.findAllPayments();
    }
}
