package Accessories.accessinfo.models;

public class Rating {
    public String accessoryId;
    public int rating;

    public Rating(String accessoryId, int rating) {
        this.accessoryId = accessoryId;
        this.rating = rating;
    }

    public Rating() {

    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
