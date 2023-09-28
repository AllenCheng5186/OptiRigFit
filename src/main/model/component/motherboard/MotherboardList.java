package model.component.motherboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.component.motherboard.FormSize.*;
import static model.component.motherboard.Socket.LGA1700;

public class MotherboardList {
    private List<Motherboard> listAllMotherboard;

    public MotherboardList() {
        listAllMotherboard = new ArrayList<>();
        addAllZ790Motherboard1(listAllMotherboard);
        addAllZ790Motherboard2(listAllMotherboard);
        addAllZ790Motherboard3(listAllMotherboard);
        addAllZ790Motherboard4(listAllMotherboard);
        addAllZ790Motherboard5(listAllMotherboard);
        addAllZ790Motherboard6(listAllMotherboard);
        Collections.sort(listAllMotherboard);
    }

    public void addAllZ790Motherboard1(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus ROG MAXIMUS Z690 APEX",
                        LGA1700, ATX, 96, 2, 1483.65),
                new Motherboard("Asus ROG MAXIMUS Z690 EXTREME",
                        LGA1700, EATX, 192, 4, 1402.64),
                new Motherboard("Asus ROG MAXIMUS Z690 FORMULA",
                        LGA1700, ATX, 192, 4, 1077.42),
                new Motherboard("Asus ROG MAXIMUS EVA-02 EDITION Z790 HERO",
                        LGA1700, ATX, 192, 4, 944.99),
                new Motherboard("Asus ProArt Z690-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 889.65),
                new Motherboard("Asus MAXIMUS Z690 HERO EVA",
                        LGA1700, ATX, 128, 4, 812.31),
                new Motherboard("Asus ROG MAXIMUS Z790 HERO",
                        LGA1700, ATX, 192, 4, 771.66),
                new Motherboard("Asus ROG STRIX Z790-E GAMING WIFI",
                        LGA1700, ATX, 192, 4, 607.49),
                new Motherboard("Asus ProArt Z790-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 593.99),
                new Motherboard("Asus ROG STRIX Z790-I GAMING WIFI",
                        LGA1700, ITX, 96, 2, 585.68),
                new Motherboard("Asus ROG STRIX Z790-F GAMING WIFI",
                        LGA1700, ATX, 192, 4, 499.49));
    }

    public void addAllZ790Motherboard2(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard,
                new Motherboard("Asus ROG STRIX Z790-A GAMING WIFI",
                        LGA1700, ATX, 192, 4, 458.99),
                new Motherboard("Asus TUF GAMING Z790-PLUS D4",
                        LGA1700, ATX, 128, 4, 447.78),
                new Motherboard("Asus ROG STRIX B660-G GAMING WIFI",
                        LGA1700, MATX, 128, 4, 440.10),
                new Motherboard("Asus ROG STRIX B660-I GAMING WIFI",
                        LGA1700, ITX, 96, 2, 427.95),
                new Motherboard("Asus ROG STRIX Z790-A GAMING D4",
                        LGA1700, ATX, 128, 4, 425.24),
                new Motherboard("Asus ROG STRIX Z690-G GAMING",
                        LGA1700, MATX, 128, 4, 404.30),
                new Motherboard("Asus PRIME Z790-A WIFI",
                        LGA1700, ATX, 192, 4, 392.84),
                new Motherboard("Asus TUF GAMING Z690-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 392.23),
                new Motherboard("Asus TUF GAMING Z690-PLUS D4",
                        LGA1700, ATX, 128, 4, 373.53),
                new Motherboard("Asus ROG STRIX Z790-H GAMING",
                        LGA1700, ATX, 192, 4, 370.83),
                new Motherboard("Asus ROG STRIX B660-F GAMING WIFI",
                        LGA1700, ATX, 192, 4, 368.50));
    }

    public void addAllZ790Motherboard3(List<Motherboard> listOfMotherboard) {
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
                        LGA1700, ATX, 192, 4, 323.99),
                new Motherboard("Asus ROG STRIX B660-A GAMING D4",
                        LGA1700, ATX, 128, 4, 318.59),
                new Motherboard("Asus TUF GAMING B660-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 314.63),
                new Motherboard("Asus TUF GAMING B660M-PLUS WIFI D4",
                        LGA1700, MATX, 192, 4, 311.80),
                new Motherboard("Asus ProArt B760-CREATOR WIFI",
                        LGA1700, ATX, 192, 4, 310.49),
                new Motherboard("Asus PRIME Z690-P",
                        LGA1700, ATX, 192, 4, 310.49));
    }

    public void addAllZ790Motherboard4(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus ROG STRIX B760-I GAMING",
                        LGA1700, ITX, 96, 2, 302.41),
                new Motherboard("Asus TUF GAMING B760-PLUS WIFI",
                        LGA1700, ATX, 192, 4, 298.34),
                new Motherboard("Asus TUF GAMING B790-PLUS WIFI D4",
                        LGA1700, ATX, 128, 4, 298.34),
                new Motherboard("Asus PRIME Z790-P WIFI D4",
                        LGA1700, ATX, 128, 4, 296.99),
                new Motherboard("Asus PRIME Z690-P D4",
                        LGA1700, ATX, 128, 4, 290.22),
                new Motherboard("Asus PRIME B660M-PLUS",
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

    public void addAllZ790Motherboard5(List<Motherboard> listOfMotherboard) {
        Collections.addAll(listOfMotherboard, new Motherboard("Asus PRIME Z790M-PLUS D4",
                        LGA1700, MATX, 128, 4, 260.55),
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

    public void addAllZ790Motherboard6(List<Motherboard> listOfMotherboard) {
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
                        LGA1700, ATX, 192, 4, 364.49),
                new Motherboard("Asus ROG MAXIMUS Z690 HERO",
                        LGA1700, ATX, 192, 4, 464.40),
                new Motherboard("Asus ROG STRIX Z690-A GAMING D4",
                        LGA1700, ATX, 128, 4, 477.20),
                new Motherboard("Asus ROG STRIX B760-A GAMING",
                        LGA1700, ATX, 192, 4, 303.74),
                new Motherboard("Asus PRIME Z790M-PLUS",
                        LGA1700, MATX, 192, 4, 263.24));
    }
}
