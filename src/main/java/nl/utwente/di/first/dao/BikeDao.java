package nl.utwente.di.first.dao;

import nl.utwente.di.first.model.Bike;

import java.util.HashMap;
import java.util.Map;

public enum BikeDao {
    instance;

    private Map<String, Bike> contentProvider = new HashMap<>();

    private BikeDao(){
        Bike bike = new Bike("1", "Nathan", "Blue", "Male");
        contentProvider.put("1", bike);

    }

    public Map<String, Bike>getModel(){
        return contentProvider;
    }
}
