package com.apple.stepDef;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public  void beforeScenario() throws FileNotFoundException {
        // execute this code only when place id is null
        // write a code that will give you place id

        StepDefinitions S = new StepDefinitions();
        if (StepDefinitions.place_id == null) {
            S.add_Place_Payload_with("jack", "eng", "89 fellsway");
            S.user_Calls_With_HttpRequest("AddPlaceAPI", "POST");
            S.verify_Place_Id_Created_Maps_To_Using("jack", "getPlaceAPI");
        }
        // place_id is static, call through class name
    }
}
