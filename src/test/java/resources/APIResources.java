package resources;

public enum APIResources {

    addPlaceAPIResource("/maps/api/place/add/json"),
    getPlaceAPIResource("/maps/api/place/get/json"),
    deletePlaceAPIResource("/maps/api/place/delete/json");

    private String apiResource;

    APIResources(String apiResource) {
        this.apiResource = apiResource;
    }

    public String getResource() {
        return apiResource;
    }
}
