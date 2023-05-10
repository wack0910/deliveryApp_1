package deliveryservice.infra;

import deliveryservice.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping(value="/restaurants")
@Transactional
public class RestaurantController {

    @Autowired
    RestaurantRepository restaurantRepository;

    @RequestMapping(
        value = "restaurants/{id}/select",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant select(
        @PathVariable(value = "id") Long id,
        @RequestBody SelectCommand selectCommand,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/select  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.select(selectCommand);

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "restaurants/{id}/start",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant start(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/start  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.start();

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @RequestMapping(
        value = "restaurants/{id}/finish",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Restaurant finish(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /restaurant/finish  called #####");
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(
            id
        );

        optionalRestaurant.orElseThrow(() -> new Exception("No Entity Found"));
        Restaurant restaurant = optionalRestaurant.get();
        restaurant.finish();

        restaurantRepository.save(restaurant);
        return restaurant;
    }
}
