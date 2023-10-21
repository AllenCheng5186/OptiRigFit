package persistence;

import model.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testWriterEmptySavedConfigs() {
        try {
            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            List<Configuration> savedConfigsRead = reader.read();
            assertEquals(0, savedConfigsRead.size());
            assertTrue(savedConfigsRead.isEmpty());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSavedListSingleConfig() {
        try {
            List<Configuration> savedConfigs = new ArrayList<>();
            savedConfigs.add(testConfiguration);

            JsonReader reader = new JsonReader("./data/testWriterSavedListSingleConfig.json");
            List<Configuration> readSavedConfigs = reader.read();
            checkListOfConfigs(savedConfigs, readSavedConfigs);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterSavedListMultipleConfig() {
        try {
            List<Configuration> savedConfigs = new ArrayList<>();
            savedConfigs.add(testConfiguration);
            savedConfigs.add(testConfigurationRamD4);
            savedConfigs.add(testConfigurationWithoutGpu);

            JsonReader reader = new JsonReader("./data/testWriterSavedListMultipleConfig.json");
            List<Configuration> readSavedConfigs = reader.read();
            checkListOfConfigs(savedConfigs, readSavedConfigs);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
