package model.component.psu;

import model.component.motherboard.FormSize;
import model.component.motherboard.Motherboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PowerSuppliesList {
    private List<PowerSupply> powerSuppliesOnSale;

    //EFFECTS: construct a PowerSuppliesList object with full List
    // and sort by price
    public PowerSuppliesList() {
        this.powerSuppliesOnSale = new ArrayList<>();
        addPowerSuppliesToList();
        powerSuppliesOnSale = sortByWatt(powerSuppliesOnSale);
    }

    //REQUIRE: listOfPowerSupply.size() > 1
    //MODIFIES: none
    //EFFECTS: return a sorted list of power supplies by watt
    public List<PowerSupply> sortByWatt(List<PowerSupply> listOfPowerSupply) {
        List<PowerSupply> sortedList = new ArrayList<>(listOfPowerSupply);
        Collections.sort(sortedList);
        return sortedList;
    }

    //REQUIRE: listOfPowerSupply.size() > 1
    //MODIFIES:none
    //EFFECTS: return a list of power supplies which form size satisfy given form size
    public List<PowerSupply> filterFormSizePSUs(FormSize formSize, List<PowerSupply> listOfPowerSupply) {
        List<PowerSupply> formSizePSUs = new ArrayList<>();
        for (PowerSupply psu : listOfPowerSupply) {
            if (psu.getFormSize() == formSize) {
                formSizePSUs.add(psu);
            }
        }
        return formSizePSUs;
    }

    //REQUIRE: minWatt > 0
    //MODIFIES: none
    //EFFECTS: return a list of power supplies which watt is greater than given minimum value
    public List<PowerSupply> filterGreaterWattPSUs(int minWatt, List<PowerSupply> listOfPowerSupply) {
        List<PowerSupply> greaterWattPSUs = new ArrayList<>();
        for (PowerSupply psu : listOfPowerSupply) {
            if (psu.getWattage() >= minWatt) {
                greaterWattPSUs.add(psu);
            }
        }
        return greaterWattPSUs;
    }

    public List<PowerSupply> filterPowerSupplyInPriceInterval(List<PowerSupply> listOfPowerSupplies,
                                                               double upperPrice, double downPrice) {
        List<PowerSupply> withinBudgetPowerSupplies = new ArrayList<>();
        for (PowerSupply mb : listOfPowerSupplies) {
            if (mb.getPrice() >= downPrice && mb.getPrice() <= upperPrice) {
                withinBudgetPowerSupplies.add(mb);
            }
        }
        return withinBudgetPowerSupplies;
    }


    //getter

    public List<PowerSupply> getListAllPowerSupply() {
        return powerSuppliesOnSale;
    }

    //MODIFIES: this
    //EFFECTS: add all power supplies on sale online to the list of power supplies
    @SuppressWarnings("methodlength")
    public void addPowerSuppliesToList() {
        Collections.addAll(powerSuppliesOnSale, new PowerSupply("Cooler Master Elite 400W Ver.3",
                        FormSize.ATX, 400, false, 89.99),
                new PowerSupply("Corsair VS400", FormSize.ATX, 400, false, 162.23),
                new PowerSupply("Corsair CX430", FormSize.ATX, 430, false, 128.68),
                new PowerSupply("Corsair CX430M", FormSize.ATX, 430, false, 95.94),
                new PowerSupply("Corsair CV450", FormSize.ATX, 450, false, 69.99),
                new PowerSupply("Corsair CX450M", FormSize.ATX, 450, false,139.99),
                new PowerSupply("Corsair SF450", FormSize.ITX, 450, true, 228.00),
                new PowerSupply("Asus TUF Gaming B", FormSize.ATX, 450, false,89.95),
                new PowerSupply("Corsair CX450 (2017)", FormSize.ATX, 450, false,139.99),
                new PowerSupply("Corsair CX500", FormSize.ATX, 500, false, 249.99),
                new PowerSupply("Corsair VS500", FormSize.ATX, 500, false, 82.23),
                new PowerSupply("Corsair VS500", FormSize.ATX, 500, false, 82.23),
                new PowerSupply("Cooler Master MPX-500", FormSize.ATX, 500, false, 99.97),
                new PowerSupply("Cooler Master MWE 550", FormSize.MATX, 550, false, 92.31),
                new PowerSupply("Cooler Master MPX-550", FormSize.ATX, 550, false, 97.97),
                new PowerSupply("Cooler Master MasterWatt 650", FormSize.ATX, 650,
                        false, 95.99),
                new PowerSupply("Cooler Master MasterWatt 750", FormSize.ATX, 750,
                        false, 120.00),
                new PowerSupply("Cooler Master V850 SFX", FormSize.ITX, 850,
                        true, 188.98),
                new PowerSupply("Cooler Master V850 SFX", FormSize.ITX, 850,
                        true, 188.98),
                new PowerSupply("Cooler Master MWE Gold 850 V2", FormSize.ATX, 850,
                        true, 155.99),
                new PowerSupply("Cooler Master MWE Gold 750 V2", FormSize.ATX, 750,
                        true, 132.99),
                new PowerSupply("Cooler Master MWE White 650", FormSize.ATX, 650,
                        true, 85.00),
                new PowerSupply("Cooler Master V1100 SFX", FormSize.ITX, 1100,
                        true, 314.99),
                new PowerSupply("Cooler Master MWE Gold 1050 V2", FormSize.ATX, 1050,
                        true, 245.89),
                new PowerSupply("Cooler Master MWE Gold 1050 V2", FormSize.ATX, 1050,
                        true, 245.89),
                new PowerSupply("Cooler Master G700 Gold", FormSize.ATX, 800,
                        true, 196.48),
                new PowerSupply("CoolerMaster MWE 750 White V2", FormSize.ATX, 750,
                        false, 85.00),
                new PowerSupply("Cooler Master XG650 Plus", FormSize.ATX, 650,
                        true, 258.08),
                new PowerSupply("Cooler Master MWE Gold 1250 V2", FormSize.ATX, 1250,
                        true, 295.49),
                new PowerSupply("Cooler Master XG750 Plus", FormSize.ATX, 750,
                        true, 278.72),
                new PowerSupply("CORSAIR RM550x", FormSize.ATX, 550, true, 150),
                new PowerSupply("CORSAIR RM650x", FormSize.ATX, 650, true, 177.52),
                new PowerSupply("CORSAIR RM750x", FormSize.ATX, 750, true, 178.99),
                new PowerSupply("CORSAIR RM850x", FormSize.ATX, 850, true, 155.98),
                new PowerSupply("CORSAIR RM1000x", FormSize.ATX, 1000, true, 219.99),
                new PowerSupply("Corsair RM1000x Shift", FormSize.ATX, 1000, true, 249.99),
                new PowerSupply("Corsair RM1200x Shift", FormSize.ATX, 1200, true, 269.99),
                new PowerSupply("Corsair RM650 (2021)", FormSize.ATX, 650, true, 179.58),
                new PowerSupply("Corsair RM750 (2021)", FormSize.ATX, 750, true, 179.41),
                new PowerSupply("Corsair RM850 (2021)", FormSize.ATX, 850, true, 119.99),
                new PowerSupply("Corsair RM750e (2022)", FormSize.ATX, 750, true, 183.85),
                new PowerSupply("Corsair RM850e (2022)", FormSize.ATX, 850, true, 184.88),
                new PowerSupply("Corsair RM1000e (2022)", FormSize.ATX, 1000,
                        true, 235.00),
                new PowerSupply("Corsair RM750e (2023)", FormSize.ATX, 750, true, 148.37),
                new PowerSupply("Corsair RM850e (2023)", FormSize.ATX, 850, true, 169.99),
                new PowerSupply("Corsair RM1000e (2023)", FormSize.ATX, 1000,
                        true, 244.99),
                new PowerSupply("Corsair SF750", FormSize.ITX, 750, true, 206.36),
                new PowerSupply("Corsair SF850L", FormSize.ITX, 850, true, 234.99),
                new PowerSupply("Corsair SF1000L ", FormSize.ITX, 1000, true, 269.99),
                new PowerSupply("Corsair HX1000 ", FormSize.ATX, 1000, true, 259.99),
                new PowerSupply("Corsair HX1200 ", FormSize.ATX, 1200, true, 325.06),
                new PowerSupply("Corsair HX1200i ", FormSize.ATX, 1200, true, 400),
                new PowerSupply("Corsair HX1000i ", FormSize.ATX, 1000, true, 319.99),
                new PowerSupply("Corsair TX550M ", FormSize.ATX, 550, true, 169.99),
                new PowerSupply("EVGA Supernova 1300 G+", FormSize.ATX, 1300,
                        true, 449.99),
                new PowerSupply("EVGA 600W", FormSize.ATX, 600, false, 74.99),
                new PowerSupply("EVGA 700 BR", FormSize.ATX, 700, false, 99.99),
                new PowerSupply("EVGA 650 N1", FormSize.ATX, 650, false, 74.99),
                new PowerSupply("EVGA 400 N1", FormSize.ATX, 400, false, 49.99),
                new PowerSupply("EVGA 750 N1", FormSize.ATX, 750, false, 88.39),
                new PowerSupply("EVGA SuperNOVA 1000 G5", FormSize.ATX, 1000,
                        true, 249.99),
                new PowerSupply("EVGA Supernova 1300 GT", FormSize.ATX, 1300,
                        true, 299.99),
                new PowerSupply("EVGA Supernova 750 GM", FormSize.ATX, 750,
                        true, 159.99),
                new PowerSupply("EVGA 550 BP", FormSize.ATX, 550, true, 117.38),
                new PowerSupply("EVGA Supernova 850 G7", FormSize.ATX, 850, true, 289.35),
                new PowerSupply("EVGA Supernova 850 G6", FormSize.ATX, 850, true, 230.72),
                new PowerSupply("EVGA Supernova 850 GM", FormSize.ATX, 850, true, 207.11),
                new PowerSupply("ASUS ROG Strix 1000W", FormSize.ATX, 1000, true, 229.99),
                new PowerSupply("ASUS ROG Strix 850W", FormSize.ATX, 850, true, 255.42),
                new PowerSupply("ASUS TUF Gaming 750W", FormSize.ATX, 750, false, 130.00),
                new PowerSupply("ASUS ROG Strix 750", FormSize.ATX, 750, true, 159.99),
                new PowerSupply("ASUS ROG Strix 650", FormSize.ATX, 650, true, 115.00),
                new PowerSupply("ASUS ROG Strix 850W White", FormSize.ATX, 850,
                        true, 237.62),
                new PowerSupply("ASUS ROG Loki SFX-L 850W", FormSize.ATX, 850,
                        true, 319.99));
    }
}
