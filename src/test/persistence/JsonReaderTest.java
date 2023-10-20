package persistence;

import model.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @BeforeEach
    void setUp() {
        super.setUpBeforeEachTest();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            List<Configuration> readSavedConfigs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }
}
