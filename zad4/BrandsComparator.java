import java.util.Comparator;

public class BrandsComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        int carBrandComparison = car1.brand.compareTo(car2.brand);
        if(carBrandComparison != 0)
            return carBrandComparison;
        return car1.model.compareTo(car2.model);
    }
}
