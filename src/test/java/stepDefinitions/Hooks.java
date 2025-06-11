package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void checkPayLoad() throws IOException {
        StepDefinitions stepDefinitions = new StepDefinitions();

        if(StepDefinitions.getPlaceId() == null){
            String name = "Unmesh";
            stepDefinitions.addPlacePayload(name, "Marathi", "Dhankawadi");
            stepDefinitions.executeHttpRequest("addPlaceAPIResource","POST");
            stepDefinitions.validatePlaceId(name, "getPlaceAPIResource");
        }
    }
}
