package ui;

import model.component.cpu.CpuList;
import model.component.gpu.GpuList;
import model.component.motherboard.MotherboardList;
import model.component.psu.PowerSuppliesList;

public class SizeTest {
    public static void main(String[] args) {
        System.out.println(new PowerSuppliesList().getListAllPowerSupply().size());
    }
}
