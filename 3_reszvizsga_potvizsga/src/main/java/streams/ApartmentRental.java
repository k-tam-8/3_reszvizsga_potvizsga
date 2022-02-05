package streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApartmentRental {
    private List<Apartment> apartmentList = new ArrayList<>();

    public void addApartment(Apartment apartment) {
        apartmentList.add(apartment);
    }

    public List<Apartment> findApartmentByLocation(String city) {
        return apartmentList.stream().filter(a->a.getLocation().equals(city)).toList();
    }

    public List<Apartment> findApartmentByExtras(String... name) {
        return apartmentList.stream().filter(a->a.getExtras().containsAll(Arrays.stream(name).toList())).toList();
    }

    public boolean isThereApartmentWithBathroomType(BathRoomType brt) {
        return apartmentList.stream().anyMatch(apartment -> apartment.getBathRoomType().equals(brt));
    }

    public List<Integer> findApartmentsSize() {
        return apartmentList.stream().map(a->a.getSize()).toList();
    }




}
