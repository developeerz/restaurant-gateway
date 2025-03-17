package ru.developeerz.gateway.api.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.developeerz.gateway.api.ApiPaths;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantFacade restaurantFacade;

    @GetMapping(ApiPaths.MENU)
    public ResponseEntity<?> getMenu() {
        return restaurantFacade.getMenu();
    }

    @PostMapping(ApiPaths.MENU)
    public ResponseEntity<?> updateMenu(/* menu entity */) {
        return restaurantFacade.updateMenu();
    }

    @DeleteMapping(ApiPaths.MENU)
    public ResponseEntity<?> deleteMenu() {
        return restaurantFacade.deleteMenu();
    }

}
