package sg.edu.ntu.simple_crm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class DemoServiceTest {

    DemoService demoService;

    @BeforeEach 
    public void init()
    {
        demoService = new DemoService();
    }

    @Test
    public void calculateAge_validYear_returnsCorrectAge() {
        // 1: ARRANGE
       
        int expectedAge = 35;

        // 2: ACT

        int actualAge = demoService.calculateAge(1990, 2025);

        // 3: ASSERTT

        assertEquals(expectedAge, actualAge, " Age should be current age - year of birth");

    }

    @ParameterizedTest 
    @CsvSource ({
    "1990, 2025, 35",
    "1965, 2025, 60",
    "2000, 2025, 25",
    "2025, 2025, 0"
})
public void calculateAge_variousYears_returnsCorrectAge(int yearOfBirth, int currentYear , int expectedAge)
{
    // 1: ACT
    int actualAge = demoService.calculateAge(yearOfBirth, currentYear);

    //2: ASSERT
    assertEquals(expectedAge, actualAge);
}

@Test 
public void formatFullName_validNames_returnsFullName()
{
    //1: ARRANGE
    String expectedFullName = "Clint Barton";

    // ACT
    String actualFullName = demoService.formatFullName("Clint", "Barton");

    // 3: ASSERT
    assertEquals(expectedFullName, actualFullName ,"Full name should be first name and last name");
}


}
