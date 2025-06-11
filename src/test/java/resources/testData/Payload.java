package resources.testData;

import POJO.AddPlaceRequestBody;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class Payload {
    public AddPlaceRequestBody addPlaceRequestBody(String name, String language, String address) {
        Location location = new Location();
        location.setLat(-38);
        location.setLng(30);

        List<String> types = new ArrayList<>();
        types.add("bla1");
        types.add("bla2");
        types.add("bla3");

        AddPlaceRequestBody addPlaceRequestBody = new AddPlaceRequestBody();
        addPlaceRequestBody.setLocation(location);
        addPlaceRequestBody.setAccuracy(50);
        addPlaceRequestBody.setName(name);
        addPlaceRequestBody.setPhone_number("900 000 0000");
        addPlaceRequestBody.setAddress(address);
        addPlaceRequestBody.setTypes(types);
        addPlaceRequestBody.setWebsite("google");
        addPlaceRequestBody.setLanguage(language);
        return addPlaceRequestBody;
    }

}
