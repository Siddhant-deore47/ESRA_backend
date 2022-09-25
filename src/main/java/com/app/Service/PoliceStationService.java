package com.app.Service;

import com.app.Repository.HospitalCoordinatesRepository;
import com.app.Repository.HospitalRepository;
import com.app.Repository.PoliceStationRepository;
import com.app.Repository.StationCoordinatesRepository;
import com.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoliceStationService {

    @Autowired
    private PoliceStationRepository policeStationRepository;
    @Autowired
    private StationCoordinatesRepository stationCoordinatesRepository;
    @Autowired
    private PoliceStationRepository stationRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private HospitalCoordinatesRepository hospitalCoordinatesRepository;

    public PoliceStation processFirstLogin(PoliceStation ps, byte[] imageFile, String cpassword,
                                    StationCoordinates coordinates) {
        PoliceStation p = policeStationRepository.findPoliceStationById(ps.getId());
        p.setImage(imageFile);
        p.setPassword(cpassword);
        p.setCoordinates(coordinates);
        p.setStatus("Active");
        return policeStationRepository.save(p);
    }

    public ResponseEntity<PoliceStation> updateStation(String station_name, String mobile, String alt_mobile, String email, Addresses address,
                                                       PoliceStation ps) {
        PoliceStation p = policeStationRepository.findPoliceStationById(ps.getId());
        p.setEmail(email);
        p.setAltMobileNo(alt_mobile);
        p.setMobileNo(mobile);
        p.setName(station_name);
        p.setPoliceStationAddress(address);
        return ResponseEntity.of(Optional.of(policeStationRepository.save(p))) ;
    }

    public static StationCoordinates findnearestStationCoordinates(double lat, List<StationCoordinates> list) {
        String latstring = "" + lat;
        for (StationCoordinates a : list) {
            String searchString = "" + a.getLatitude();
            if (searchString.equals(latstring))
                return a;
        }
        return null;
    }

    public static HospitalCoordinates findnearestHospitalCoordinates1(double lat, List<HospitalCoordinates> list) {
        String latstring = "" + lat;
        for (HospitalCoordinates a : list) {
            String searchString = "" + a.getLatitude();
            if (searchString.equals(latstring))
                return a;
        }
        return null;
    }

    public static Double findClosest(Double[] arr, Double target) {
        int n = arr.length;

        if (target <= arr[0])
            return arr[0];
        if (target >= arr[n - 1])
            return arr[n - 1];

        int i = 0, j = n, mid = 0;
        while (i < j) {
            mid = (i + j) / 2;

            if (Math.abs(arr[mid] - target) < 0.0001)
                return arr[mid];

            if (target < arr[mid]) {

                if (mid > 0 && target > arr[mid - 1])
                    return getClosest(arr[mid - 1], arr[mid], target);

                j = mid;
            }

            else {
                if (mid < n - 1 && target < arr[mid + 1])
                    return getClosest(arr[mid], arr[mid + 1], target);
                i = mid + 1;
            }
        }

        return arr[mid];
    }

    public static Double getClosest(Double val1, Double val2, Double target) {
        if (target - val1 >= val2 - target)
            return val2;
        else
            return val1;
    }

    public static Double[] convertarray(List<StationCoordinates> list) {
        int n = list.size();
        Double[] arrayLat = new Double[n];
        for (int i = 0; i < arrayLat.length; i++) {
            arrayLat[i] = list.get(i).getLatitude();
        }
        return arrayLat;
    }

    public static Double[] convertarray1(List<HospitalCoordinates> list1) {
        int n = list1.size();
        Double[] arrayLat = new Double[n];
        for (int i = 0; i < arrayLat.length; i++) {
            arrayLat[i] = list1.get(i).getLatitude();
        }
        return arrayLat;
    }

    public PoliceStation fetchPoliceStationByCoordinates(double latitude, double longitude) {

        List<StationCoordinates> pcList1 = stationCoordinatesRepository.findAll();
        System.out.println(pcList1);

        Double[] array = convertarray(pcList1);
        double nearestlatitude = findClosest(array, latitude);
        System.out.println(nearestlatitude);
        StationCoordinates nearestcordinates = findnearestStationCoordinates(nearestlatitude, pcList1);
        System.out.println(nearestcordinates);
        return stationRepository.findPoliceStationByCoordinates(nearestcordinates);
    }

    public List<Accidents> fetchAccidentbyID(PoliceStation p) {
        return policeStationRepository.findPoliceStationByNearestCoordinates(p);
    }

    public Hospital fetchHospitalByCoordinates(Double latitude, Double longitude) {

        List<HospitalCoordinates> pcList1 = hospitalCoordinatesRepository.findAll();
        System.out.println(pcList1);

        Double[] array = convertarray1(pcList1);
        double nearestlatitude = findClosest(array, latitude);
        System.out.println("hospital" + nearestlatitude);
        HospitalCoordinates nearestcordinates = findnearestHospitalCoordinates1(nearestlatitude, pcList1);
        System.out.println("hospital" + nearestcordinates);
        return hospitalRepository.findHospitalByCoordinates(nearestcordinates);
    }

    public PoliceStation fetchPoliceStationByEmail(String email) {
        return policeStationRepository.findPoliceStationByEmail(email);
    }
}
