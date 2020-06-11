package net.avalith.carDriver.services;

import net.avalith.carDriver.models.Mishap;
import net.avalith.carDriver.models.Ride;
import net.avalith.carDriver.repositories.MishapRepository;
import net.avalith.carDriver.repositories.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MishapService {

    @Autowired
    private MishapRepository mishapRepository;

    @Autowired
    private RideRepository rideRepository;

    public List<Mishap> getAll(){
        return mishapRepository.findAll();
    }

    public Mishap save(Mishap mishap, Long id_ride){
        Ride rideSerach= rideRepository.findById(id_ride)
                .orElseThrow(RuntimeException::new);
        mishap.setRide(rideSerach);
        return mishapRepository.save(mishap);
    }
}
