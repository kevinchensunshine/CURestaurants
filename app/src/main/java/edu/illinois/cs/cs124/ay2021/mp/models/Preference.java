package edu.illinois.cs.cs124.ay2021.mp.models;


//MP2 part 2
public final class Preference {
  private String id;
  private String[] restaurantIDs;
  private Preference() {
  }
  public String getId() {
    return id;
  }
  public String[] getRestaurantIDs() {
    return restaurantIDs;
  }
}
