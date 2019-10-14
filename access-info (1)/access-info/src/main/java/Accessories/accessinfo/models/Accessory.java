package Accessories.accessinfo.models;

public class Accessory {
    public String accessoryId;
    public String name;

    public Accessory(){

    }
    public Accessory(String accessoryId, String name) {
        this.accessoryId = accessoryId;
        this.name = name;
    }

    public String getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(String accessoryId) {
        this.accessoryId = accessoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
