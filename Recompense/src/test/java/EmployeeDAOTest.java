import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.models.Employee;

import org.junit.jupiter.api.Test;

public class EmployeeDAOTest{
    @Test
    void testMethod1()
    {
        Employee bob = new Employee(1, "job", "bob", "tubo", 1, true);
        assertEquals("bob",bob.getFirstName());
        // System.out.println("Testing...");
    }
}