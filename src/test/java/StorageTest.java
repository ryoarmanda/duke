import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {
    @Test
    public void read_fileNotFound_exceptionThrown() {
        Storage s = new Storage("not_a_file");
        assertThrows(DukeException.class, s::read);
    }
}
