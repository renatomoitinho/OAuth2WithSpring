package br.inf.audasi.test;

import br.inf.audasi.domain.config.ApplicationConfig;
import br.inf.audasi.domain.entity.User;
import br.inf.audasi.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * @author renatomoitinhodias@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
public class RepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void connect() {
        assertThat(userRepository, notNullValue());
    }

    @Test @Transactional @Rollback(true)
    public void repositoryFlowTest() {
        User user1 = userRepository.save(createSingleUser());
        User user2 = userRepository.save(createSingleUser());

        assertThat(userRepository.findById(user1.getId()).isPresent(), is(true));
        assertThat(userRepository.findById(user2.getId()).isPresent(), is(true));
    }

    private User createSingleUser() {
        User user = new User();
        user.setEmail("renatomoitinho@gmail.com");
        user.setLogin("renato");
        user.setName("Renato Dias");
        user.setPassword("123456");
        return user;
    }
}
