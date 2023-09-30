package model.component.motherboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.component.motherboard.FormSize.*;
import static model.component.motherboard.Socket.AM5;
import static model.component.motherboard.Socket.LGA1700;

public class MotherboardList {
    private List<Motherboard> listAllMotherboard;

    //EFFECTS: construct a MotherboardList object which contains all motherboard objects
    public MotherboardList() {
        listAllMotherboard = new ArrayList<>();
        addAllLGA1700Motherboard1(listAllMotherboard);
        addAllLGA1700Motherboard2(listAllMotherboard);
        addAllLGA1700Motherboard3(listAllMotherboard);
        addAllLGA1700Motherboard4(listAllMotherboard);
        addAllLGA1700Motherboard5(listAllMotherboard);
        addAllLGA1700Motherboard6(listAllMotherboard);
        addAllAM5Motherboards1(listAllMotherboard);
        addAllAM5Motherboards2(listAllMotherboard);

        sortMotherboardsByDecreasePrice(listAllMotherboard);
    }

    //MODIFIES: List<Motherboard>
    //EFFECTS: filter the given list of motherboards that only has right socket
    public List<Motherboard> filterRightSocketMotherboard(List<Motherboard> listOfMotherboard, Socket socket) {
        List<Motherboard> rightSocketMotherboards = new ArrayList<>();
        for (Motherboard motherboard : listOfMotherboard) {
            if (motherboard.getSocket() == socket) {
                rightSocketMotherboards.add(motherboard);
            }
        }
        return rightSocketMotherboards;
    }

    //MODIFIES: List<Motherboard>
    //EFFECTS: filter the given list of motherboards those are same as given form factor
    public List<Motherboard> filterRightFormSizeMotherboards(List<Motherboard> listOfMotherboard, FormSize formSize) {
        List<Motherboard> rightFromSizeMotherboards = new ArrayList<>();
        for (Motherboard motherboard : listOfMotherboard) {
            if (motherboard.getFormSize() == formSize) {
                rightFromSizeMotherboards.add(motherboard);
            }
        }
        return rightFromSizeMotherboards;
    }

    //MODIFIES: List<Motherboard>
    //EFFECTS: sort the given list of motherboard by price from high to low
    public void sortMotherboardsByDecreasePrice(List<Motherboard> listOfMotherboard) {
        Collections.sort(listOfMotherboard);
    }

    // getter
    public List<Motherboard> getListAllMotherboard() {
        return listAllMotherboard;
    }

    //MODIFIES: List<Motherboard>
    // EFFECTS: add all ASUS LGA1700 and AM5 Chipset Motherboards to given list
    public void addAllLGA1700Motherboard1(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                        LGA1700, ATX, 96, 2, 522.39),
                new Motherboard("Asus ROG MAXIMUS Z690 EXTREME",
                        LGA1700, EATX, 192, 4, 1329.00),
                new Motherboard("Asus ROG MAXIMUS Z690 FORMULA",
                        LGA1700, ATX, 192, 4, 693.39),
                new Motherboard("Asus ROG MAXIMUS EVA-02 EDITION Z790 HERO",
                        LGA1700, ATX, 192, 4, 944.99),
                new Motherboard("Asus ProArt Z690-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 584.99),
                new Motherboard("Asus MAXIMUS Z690 HERO EVA",
                        LGA1700, ATX, 128, 4, 749.00),
                new Motherboard("Asus ROG MAXIMUS Z790 HERO",
                        LGA1700, ATX, 192, 4, 789.98),
                new Motherboard("Asus ROG STRIX Z790-E GAMING WIFI",
                        LGA1700, ATX, 192, 4, 559.00),
                new Motherboard("Asus ProArt Z790-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 569.99),
                new Motherboard("Asus ROG STRIX Z790-I GAMING WIFI",
                        LGA1700, ITX, 96, 2, 555.10),
                new Motherboard("Asus ROG STRIX Z790-F GAMING WIFI",
                        LGA1700, ATX, 192, 4, 499.00));
    }

    public void addAllLGA1700Motherboard2(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard,
                new Motherboard("Asus ROG STRIX Z790-A GAMING WIFI",
                        LGA1700, ATX, 192, 4, 479.00),
                new Motherboard("Asus TUF GAMING Z790-PLUS D4",
                        LGA1700, ATX, 128, 4, 299.99),
                new Motherboard("Asus ROG STRIX B660-I GAMING WIFI",
                        LGA1700, ITX, 96, 2, 449.73),
                new Motherboard("Asus ROG STRIX Z790-A GAMING D4",
                        LGA1700, ATX, 128, 4, 425.24),
                new Motherboard("Asus ROG STRIX Z690-G GAMING",
                        LGA1700, MATX, 128, 4, 369.99),
                new Motherboard("Asus PRIME Z790-A WIFI",
                        LGA1700, ATX, 192, 4, 409.00),
                new Motherboard("Asus TUF GAMING Z690-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 249.00),
                new Motherboard("Asus TUF GAMING Z690-PLUS D4",
                        LGA1700, ATX, 128, 4, 373.53),
                new Motherboard("Asus ROG STRIX Z790-H GAMING",
                        LGA1700, ATX, 192, 4, 311.99),
                new Motherboard("Asus ROG STRIX B660-F GAMING WIFI",
                        LGA1700, ATX, 192, 4, 368.50));
    }

    public void addAllLGA1700Motherboard3(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus ROG STRIX B660-A GAMING WIFI",
                        LGA1700, ATX, 128, 4, 353.31),
                new Motherboard("Asus ROG STRIX Z690-I GAMING WIFI",
                        LGA1700, ITX, 96, 2, 350.99),
                new Motherboard("Asus ROG STRIX Z690-E GAMING WIFI",
                        LGA1700, ATX, 192, 4, 337.50),
                new Motherboard("Asus PRIME Z690M-PLUS D4",
                        LGA1700, MATX, 128, 4, 336.15),
                new Motherboard("Asus ROG STRIX Z690-F GAMING WIFI",
                        LGA1700, ATX, 192, 4, 325.35),
                new Motherboard("Asus PRIME Z790-V WIFI",
                        LGA1700, ATX, 192, 4, 421.99),
                new Motherboard("Asus ROG STRIX B660-A GAMING D4",
                        LGA1700, ATX, 128, 4, 270.74),
                new Motherboard("Asus TUF GAMING B660-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 229.99),
                new Motherboard("Asus ProArt B760-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 229.99),
                new Motherboard("Asus PRIME Z690-P",
                        LGA1700, ATX, 192, 4, 299.99));
    }

    public void addAllLGA1700Motherboard4(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus ROG STRIX B760-I GAMING",
                        LGA1700, ITX, 96, 2, 289.99),
                new Motherboard("Asus TUF GAMING B760-PLUS WIFI",
                        LGA1700, ATX, 192, 4, 249.99),
                new Motherboard("Asus TUF GAMING B790-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 299.99),
                new Motherboard("Asus PRIME Z790-P WIFI D4",
                        LGA1700, ATX, 128, 4, 299.99),
                new Motherboard("Asus PRIME Z690-P D4",
                        LGA1700, ATX, 128, 4, 264.00),
                new Motherboard("Asus PRIME B660M-PLUS D4",
                        LGA1700, MATX, 128, 4, 286.93),
                new Motherboard("Asus PRIME B660M-A WIFI D4",
                        LGA1700, MATX, 128, 4, 283.50),
                new Motherboard("Asus TUF GAMING B660M-E D4",
                        LGA1700, MATX, 128, 4, 282.27),
                new Motherboard("Asus PRIME Z690-P WIFI",
                        LGA1700, ATX, 192, 4, 282.14),
                new Motherboard("Asus PRIME H670-PLUS D4",
                        LGA1700, ATX, 128, 4, 282.07),
                new Motherboard("Asus PRIME B760M-K D4",
                        LGA1700, MATX, 64, 2, 267.29));
    }

    public void addAllLGA1700Motherboard5(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus PRIME Z790M-PLUS D4",
                        LGA1700, MATX, 128, 4, 259.99),
                new Motherboard("Asus TUF GAMING B760M-PLUS WIFI",
                        LGA1700, MATX, 192, 4, 256.49),
                new Motherboard("Asus PRIME Z690-A",
                        LGA1700, ATX, 192, 4, 242.99),
                new Motherboard("Asus TUF GAMING H670-PRO WIFI",
                        LGA1700, ATX, 128, 4, 242.99),
                new Motherboard("Asus ProArt B760-CREATOR D4",
                        LGA1700, ATX, 128, 4, 242.99),
                new Motherboard("Asus PRIME B760M-A D4",
                        LGA1700, MATX, 128, 4, 229.49),
                new Motherboard("Asus PRIME B660M-A AC D4",
                        LGA1700, MATX, 128, 4, 202.49),
                new Motherboard("Asus PRIME B660M-A D4",
                        LGA1700, MATX, 128, 4, 188.99),
                new Motherboard("Asus Pro B760M-CT-CSM",
                        LGA1700, MATX, 192, 4, 188.99),
                new Motherboard("Asus PRIME B660M-K D4",
                        LGA1700, MATX, 64, 2, 186.31),
                new Motherboard("Asus PRIME H610M-E D4",
                        LGA1700, MATX, 64, 2, 180.23));
    }

    public void addAllLGA1700Motherboard6(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus PRIME H610M-D D4",
                        LGA1700, MATX, 64, 2, 165.44),
                new Motherboard("Asus PRIME H610I-PLUS D4-CSM",
                        LGA1700, ITX, 64, 2, 149.84),
                new Motherboard("Asus PRIME H610M-A D4-CSM",
                        LGA1700, MATX, 64, 2, 148.49),
                new Motherboard("Asus PRO H610M-CT D4-CSM",
                        LGA1700, MATX, 64, 2, 143.09),
                new Motherboard("Asus Pro B660M-C D4-CSM",
                        LGA1700, MATX, 128, 4, 134.99),
                new Motherboard("Asus ROG STRIX B760-F GAMING",
                        LGA1700, ATX, 192, 4, 364.99),
                new Motherboard("Asus ROG MAXIMUS Z690 HERO",
                        LGA1700, ATX, 192, 4, 519.87),
                new Motherboard("Asus ROG STRIX Z690-A GAMING D4",
                        LGA1700, ATX, 128, 4, 424.99),
                new Motherboard("Asus ROG STRIX B760-A GAMING",
                        LGA1700, ATX, 192, 4, 303.74),
                new Motherboard("Asus PRIME Z790M-PLUS",
                        LGA1700, MATX, 192, 4, 259.99));
    }

    public void addAllAM5Motherboards1(List<Motherboard> lisOfMotherboard) {
        Collections.addAll(lisOfMotherboard, new Motherboard("Asus ROG CROSSHAIR X670E HERO",
                AM5, ATX, 192, 4, 789.98),
                new Motherboard("Asus ROG CROSSHAIR X670E GENE", AM5, MATX,
                        96, 2, 799.99),
                new Motherboard("Asus ROG STRIX X670E-E GAMING WIFI", AM5, ATX,
                        192, 4, 638.98),
                new Motherboard("Asus ProArt X670E-CREATOR WIFI", AM5, ATX,
                        192, 4, 559.99),
                new Motherboard("Asus ROG STRIX X670E-I GAMING WIFI", AM5, ITX,
                        96, 2, 579.99),
                new Motherboard("Asus ROG STRIX X670E-F GAMING WIFI", AM5, ATX,
                        192, 4, 594.99),
                new Motherboard("Asus ROG STRIX X670E-A GAMING WIFI", AM5, ATX,
                        192,4, 529.00),
                new Motherboard("Asus TUF GAMING X670E-PLUS WIFI", AM5, ATX,
                        192, 4, 407.55),
                new Motherboard("Asus ROG STRIX B650E-E GAMING WIFI", AM5, ATX,
                        192, 4, 449.99),
                new Motherboard("Asus PRIME X670E-PRO WIFI", AM5, ATX,
                        192,4, 469.00),
                new Motherboard("Asus ROG STRIX B650E-I GAMING WIFI", AM5, ITX,
                        96, 2,429.00));
    }

    public void addAllAM5Motherboards2(List<Motherboard> lisOfMotherboard) {
        Collections.addAll(lisOfMotherboard, new Motherboard("Asus TUF GAMING X670E-PLUS WIFI", AM5, ATX,
                        192, 4, 429.00),
                new Motherboard("Asus ROG STRIX B650-A GAMING WIFI", AM5, ATX,
                        192, 4, 299.99),
                new Motherboard("Asus PRIME X670-P WIFI", AM5, ATX,
                        192, 4, 394.99),
                new Motherboard("Asus ROG STRIX B650E-F GAMING WIFI", AM5, ATX,
                        192, 4, 379.99),
                new Motherboard("Asus TUF GAMING B650-PLUS WIFI", AM5, ATX,
                        128, 4, 275.49),
                new Motherboard("Asus PRIME B650M-A AX", AM5, MATX, 128, 4, 232.16));
    }
}
