package br.edu.utfpr.pb.pw25s.Fynance;

import br.edu.utfpr.pb.pw25s.Fynance.model.User;
import br.edu.utfpr.pb.pw25s.Fynance.model.Wallet;
import br.edu.utfpr.pb.pw25s.Fynance.repository.WalletRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class WalletControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;
    @Autowired
    WalletRepository walletRepository;

    @BeforeEach()
    private void cleanup() {
        walletRepository.deleteAll();
        testRestTemplate.getRestTemplate().getInterceptors().clear();
    }

    @Test
    public void postWallet_receiveCreated() {
        Wallet wallet = createValidWallet();

        ResponseEntity<Object> response =
                testRestTemplate.postForEntity("/wallets", wallet, Object.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private Wallet createValidWallet() {

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

        return wallet;
    }
}