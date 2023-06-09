package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        PlaceValidationsSteps m = new PlaceValidationsSteps();
        if (PlaceValidationsSteps.placeId == null) {
            m.add_place_payload("Hsn", "FRA", "Europe");
            m.user_calls_with_http_request("addPlaceAPI", "POST");
            m.verify_placeId_with_name("Hsn", "getPlaceAPI");
        }
    }
}
