package playground.entityinheritance.creditcard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional
    public void save() {
        CreditCardPayment ccPayment = CreditCardPayment.builder()
                .amount(BigDecimal.valueOf(100.50))
                .cardNumber("1234-5678-9012-3456")
                .cardType("Visa")
                .build();
        log.info("Saved CreditCardPayment: {}", ccPayment);
        paymentRepository.save(ccPayment);

        CashPayment cashPayment = CashPayment.builder()
                .amount(BigDecimal.valueOf(50.00))
                .currency("USD")
                .build();
        paymentRepository.save(cashPayment);
        log.info("Saved CashPayment: {}", cashPayment);
    }

    @Transactional
    public CreditCardPayment saveCreditCardPayment(CreditCardPayment creditCardPayment) {
        CreditCardPayment saved = paymentRepository.save(creditCardPayment);
        log.info("Saving credit card payment with details: {}", creditCardPayment);
        return saved;
    }

    @Transactional
    public CashPayment saveCashPayment(CashPayment cashPayment) {
        CashPayment saved = paymentRepository.save(cashPayment);
        log.info("Saving cash payment with details: {}", cashPayment);
        return saved;
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();

    }

}
