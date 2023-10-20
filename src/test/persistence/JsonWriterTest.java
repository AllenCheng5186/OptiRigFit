package persistence;

import model.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @BeforeEach
    void setUp() {
        super.setUpBeforeEachTest();
    }


    @Test
    void testWriterInvalidFile() {
        try {
            List<Configuration> savedConfigs = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptySavedConfigs() {
        try {
            List<Configuration> savedConfigs = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(savedConfigs);
            writer.close();

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
            JsonWriter writer = new JsonWriter("./data/testWriterSavedListSingleConfig.json");
            writer.open();
            writer.write(savedConfigs);
            writer.close();

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
            JsonWriter writer = new JsonWriter("./data/testWriterSavedListMultipleConfig.json");
            writer.open();
            writer.write(savedConfigs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterSavedListMultipleConfig.json");
            List<Configuration> readSavedConfigs = reader.read();
            checkListOfConfigs(savedConfigs, readSavedConfigs);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
