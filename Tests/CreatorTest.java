import org.junit.Test;
import org.junit.Rule;
import org.junit.Assert.*;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.*;



public class CreatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none ();

    @Test
    public void createWorkOrder () throws Exception {
        thrown.expect(IOException.class);

    }

    @Test
    public void main () throws Exception {
    }

}