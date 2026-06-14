package com.apple.Utilities;

import com.apple.POJO.AddPlaceMaps;
import com.apple.POJO.LocationMaps;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuilder {

    public AddPlaceMaps addPlacePayload(String name , String language, String address){
        AddPlaceMaps p =new AddPlaceMaps();

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("https://rahulshettyacademy.com");
        p.setName(name);

        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        LocationMaps l =new LocationMaps();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return  p;
    }

    public String deletePLacePayload( String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}\r\n";

    }
}
