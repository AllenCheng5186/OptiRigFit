package model.component.gpu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//represent a list of gpus, which have all method that works on a list of gpus it contains
public class GpuList {
    private List<Gpu> listAllGpu;

    //EFFECTS: construct a GpuList object to process List<Gpu>, it has a list of gpus with all model on sale
    // and were sorted by benchmark from high to low
    public GpuList() {
        this.listAllGpu = new ArrayList<>();
        addAllNvidia3040Gpu();
        addAllAMD7000Gpu();
        Collections.sort(listAllGpu);
    }

    //EFFECTS: return a list of gpus which price in given budget interval
    public List<Gpu> filterGpusInPriceInterval(List<Gpu> listOfGpu, double upperPrice, double downPrice) {
        List<Gpu> withinBudgetGpus = new ArrayList<>();
        for (Gpu gpu : listOfGpu) {
            if (gpu.getPrice() >= downPrice && gpu.getPrice() <= upperPrice) {
                withinBudgetGpus.add(gpu);
            }
        }
        return withinBudgetGpus;
    }

    // getter

    public List<Gpu> getListAllGpu() {
        return listAllGpu;
    }

    //EFFECTS: add all gpu to the collection
    public void addAllNvidia3040Gpu() {
        Collections.addAll(listAllGpu, new Gpu("GeForce RTX 4090", 450, 2298.98,
                        GpuMfr.NVIDIA, 38929),
                new Gpu("GeForce RTX 4080", 320, 1599.99, GpuMfr.NVIDIA, 34820),
                new Gpu("GeForce RTX 4070TI", 285, 1139.99, GpuMfr.NVIDIA, 31633),
                new Gpu("GeForce RTX 4070", 200, 1195.00, GpuMfr.NVIDIA, 26863),
                new Gpu("GeForce RTX 4060TI 16GB", 160, 675.99, GpuMfr.NVIDIA, 22712),
                new Gpu("GeForce RTX 4060TI 8GB", 160, 539.99, GpuMfr.NVIDIA, 22496),
                new Gpu("GeForce RTX 4060", 160, 399.99, GpuMfr.NVIDIA, 19403),
                new Gpu("GeForce RTX 3090TI", 450, 2118.44, GpuMfr.NVIDIA, 29810),
                new Gpu("GeForce RTX 3090", 350, 2099.00, GpuMfr.NVIDIA, 26917),
                new Gpu("GeForce RTX 3080 12GB", 320, 1891.48, GpuMfr.NVIDIA, 26750),
                new Gpu("GeForce RTX 3080", 320, 1418.49, GpuMfr.NVIDIA, 25367),
                new Gpu("GeForce RTX 3070TI", 290, 999.00, GpuMfr.NVIDIA, 19403),
                new Gpu("GeForce RTX 3070", 220, 649.99, GpuMfr.NVIDIA, 22482),
                new Gpu("GeForce RTX 3060 12GB", 170, 459.99, GpuMfr.NVIDIA, 17134),
                new Gpu("GeForce RTX 3060 8GB", 150, 388.98, GpuMfr.NVIDIA, 17134));

    }

    public void addAllAMD7000Gpu() {
        Collections.addAll(listAllGpu, new Gpu("Radeon RX 7900 XTX", 355,
                        1348.98, GpuMfr.AMD, 31142),
                new Gpu("Radeon RX 7900 XT", 315, 1145.61, GpuMfr.AMD, 29040),
                new Gpu("Radeon RX 6950 XT", 300, 999.99, GpuMfr.AMD, 28627),
                new Gpu("Radeon RX 6900 XT", 300, 868.98, GpuMfr.AMD, 26963),
                new Gpu("Radeon RX 7800 XT", 295, 729.99, GpuMfr.AMD, 24461),
                new Gpu("Radeon RX 6800", 250, 629.29, GpuMfr.AMD, 22369),
                new Gpu("Radeon RX 6750 XT", 250, 437.99, GpuMfr.AMD, 21089),
                new Gpu("Radeon RX 6700 XT", 230, 579.99, GpuMfr.AMD, 19883),
                new Gpu("Radeon RX 6700 ", 175, 408.98, GpuMfr.AMD, 18832));
    }
}
