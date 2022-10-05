package br.edu.utfpr.pb.pw25s.Fynance;

import br.edu.utfpr.pb.pw25s.Fynance.dto.WalletDto;
import br.edu.utfpr.pb.pw25s.Fynance.model.Transaction;
import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.TransactionRepository;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class TransactionControllerTest {
    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    TransactionRepository transactionRepository;

    @BeforeEach()
    private void cleanup() {
        transactionRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    public void postTransaction_receiveCreated() {
        Transaction transaction = createValidEntryTransaction();

        ResponseEntity<Object> response =
                testRestTemplate.postForEntity("/transactions", transaction, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private Transaction createValidEntryTransaction() {

        User user = new User();
        user.setUsername("test-user");
        user.setEmail("test@test.com");
        user.setDisplayName("test-dislpay");
        user.setPassword("P4ssword");

        Wallet wallet = new Wallet();
        wallet.setName("Viagem pro Canada");
        wallet.setBalance(1000.00);
        wallet.setType("Investimento");
        wallet.setUser(user);

        Transaction transaction = new Transaction();
        transaction.setName("Compra de BPAC11");
        transaction.setWallet(wallet);
        transaction.setDateTransaction(new Date());
        transaction.setValueTransaction(1300.00);
        transaction.setES('E');
        transaction.setDescription("Compra de 10 lotes de BPAC11");
        transaction.setCategory("Ação");
        transaction.setFees(10.20);
        transaction.setTotal(1310.20);

        return transaction;
    }
}