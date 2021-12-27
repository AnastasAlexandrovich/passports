package com.example.passports;

import com.example.passports.Controller.PassportController;
import com.example.passports.Entity.Passport;
import com.example.passports.Entity.ViewActivePassports;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration(initializers = {PeopleControllerTest.Initializer.class})
@AutoConfigureMockMvc
public class PeopleControllerTest {

    private static PostgreSQLContainer sqlContainer;

    static {
        sqlContainer = new PostgreSQLContainer("postgres")
                .withDatabaseName("passport")
                .withUsername("postgres")
                .withPassword("password");
        sqlContainer.start();
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + sqlContainer.getJdbcUrl(),
                    "spring.datasource.username=" + sqlContainer.getUsername(),
                    "spring.datasource.password=" + sqlContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @BeforeAll
    static void runDB1Migrations() {
        sqlContainer.withDatabaseName("passport");
        var flyway = Flyway.configure()
                .locations(new Location("classpath:db/migration"))
                .schemas("public")
                .dataSource(sqlContainer.getJdbcUrl(), sqlContainer.getUsername(), sqlContainer.getPassword())
                .load();
        flyway.info();
        flyway.migrate();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PassportController passportController;

    @Test
    public void testView() throws Exception {
        this.mockMvc.perform(get("/passport/activate"))
                .andDo(print())
                .andExpect(status().isOk());

        List<ViewActivePassports> viewActivePassports = passportController.getAllViewActivePassports();
        List<Passport> passportList = passportRepository.findAllByStatus("ACTIVE");

        if (viewActivePassports.size()!=passportList.size()){
            throw new Exception("Представлении данные отличаются от фактического!");
        }

    }

    @Test
    public void testTrigger() throws Exception{

        this.mockMvc.perform(delete("/people/delete/5"))
                .andDo(print())
                .andExpect(status().isOk());

        if (peopleRepository.existsById(5L)){
            throw new Exception("people с id = 5 не удалился!");
        }
        else if (!passportRepository.findAllByPeople_Id(5L).isEmpty()){
            throw new Exception("паспортные данные people id = 5 не удалены!");
        }
    }


}
