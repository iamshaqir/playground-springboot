package playground.entityinheritance.vehicle;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return service.addVehicle(vehicle);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vehicle> findAll() {
        return service.findAll();
    }
}
