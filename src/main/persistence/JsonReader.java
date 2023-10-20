package persistence;

import model.Configuration;
import model.component.cpu.Cpu;
import model.component.cpu.CpuMfr;
import model.component.gpu.Gpu;
import model.component.gpu.GpuMfr;
import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;
import model.component.motherboard.Socket;
import model.component.psu.PowerSupply;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
// reference: course handout, JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads list of configs from file and returns it;
    // throws IOException if an error occurs reading data from file
    public List<Configuration> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseConfigsList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses configuration list from JSON object and returns it
    private List<Configuration> parseConfigsList(JSONObject jsonObject) {
        List<Configuration> savedConfigs = new ArrayList<>();
        addThingies(savedConfigs, jsonObject);
        return savedConfigs;
    }

    // MODIFIES: savedConfigs
    // EFFECTS: parses configurations from JSON object and adds them to workroom
    private void addThingies(List<Configuration> savedConfigs, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("savedConfig");
        for (Object json : jsonArray) {
            JSONObject nextConfig = (JSONObject) json;
            addConfig(savedConfigs, nextConfig);
        }
    }

    // MODIFIES: savedConfigs
    // EFFECTS: parses configuration from JSON object and adds it to the given list
    private void addConfig(List<Configuration> savedConfigs, JSONObject jsonObject) {
        double configRamBudget = jsonObject.getDouble("ramBudget");
        JSONArray jsonArrayComponent = jsonObject.getJSONArray("components");
        JSONObject compJsonObject = (JSONObject) jsonArrayComponent.get(0);
        Cpu configCpu = readCpuJsonObject(compJsonObject);
        if (jsonArrayComponent.length() == 4) {
            compJsonObject = (JSONObject) jsonArrayComponent.get(1);
            Gpu configGpu = readGpuJsonObject(compJsonObject);
            compJsonObject = (JSONObject) jsonArrayComponent.get(2);
            Motherboard configMB = readMbJson(compJsonObject);
            compJsonObject = (JSONObject) jsonArrayComponent.get(3);
            PowerSupply configPsu = readPsuJsonObject(compJsonObject);
            savedConfigs.add(new Configuration(configCpu, configMB, configGpu, configPsu, configRamBudget));
        } else {
            compJsonObject = (JSONObject) jsonArrayComponent.get(1);
            Motherboard configMB = readMbJson(compJsonObject);
            compJsonObject = (JSONObject) jsonArrayComponent.get(2);
            PowerSupply configPsu = readPsuJsonObject(compJsonObject);
            savedConfigs.add(new Configuration(configCpu, configMB, null, configPsu, configRamBudget));
        }
    }

    // EFFECTS: return a power supply object which parsed from JSON object
    private PowerSupply readPsuJsonObject(JSONObject compJsonObject) {
        int psuWatt = compJsonObject.getInt("wattage");
        double psuPrice = compJsonObject.getDouble("price");
        String psuModel = compJsonObject.getString("model");
        boolean psuModular = compJsonObject.getBoolean("isModular");
        FormSize psuSize = FormSize.valueOf(compJsonObject.getString("formSize"));
        return new PowerSupply(psuModel, psuSize, psuWatt, psuModular, psuPrice);
    }

    // EFFECTS: return a cpu object which parsed from JSON object
    private Cpu readCpuJsonObject(JSONObject compJsonObject) {
        CpuMfr cpuMfr = CpuMfr.valueOf(compJsonObject.getString("cpuMfr"));
        int cpuBasePower = compJsonObject.getInt("basePower");
        double cpuPrice = compJsonObject.getDouble("price");
        int cpuBenchMark = compJsonObject.getInt("benchMark");
        String cpuModel = compJsonObject.getString("model");
        boolean cpuIG = compJsonObject.getBoolean("integratedGraphics");
        return new Cpu(cpuModel, cpuBasePower, cpuPrice, cpuMfr, cpuBenchMark);
    }

    // EFFECTS: return a gpu object which parsed from JSON object (if possible)
    private Gpu readGpuJsonObject(JSONObject compJsonObject) {
        int gpuBasePower = compJsonObject.getInt("basePower");
        double gpuPrice = compJsonObject.getDouble("price");
        int gpuBenchMark = compJsonObject.getInt("bench mark");
        String gpuModel = compJsonObject.getString("model");
        GpuMfr gpuMfr = GpuMfr.valueOf(compJsonObject.getString("gpuMfr"));
        return new Gpu(gpuModel, gpuBasePower, gpuPrice, gpuMfr, gpuBenchMark);
    }

    // EFFECTS: return  a motherboard object which parsed from JSON object
    private Motherboard readMbJson(JSONObject compJsonObject) {
        int mbMaxRam = compJsonObject.getInt("maxRam");
        double mbPrice = compJsonObject.getDouble("price");
        String mbModel = compJsonObject.getString("model");
        Socket mbSocket = Socket.valueOf(compJsonObject.getString("socket"));
        FormSize mbFromSie = FormSize.valueOf(compJsonObject.getString("formSize"));
        int mbRamSlot = compJsonObject.getInt("ramSlot");
        return new Motherboard(mbModel, mbSocket, mbFromSie, mbMaxRam, mbRamSlot, mbPrice);
    }

}
