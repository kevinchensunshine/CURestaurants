package edu.illinois.cs.cs124.ay2021.mp.models;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatedRestaurants {
  private Map<String, Map<String, Integer>> restaurantRelationships = new HashMap<>();

  public RelatedRestaurants(final List<Restaurant> restaurants, final List<Preference> preferences) {
    for (Restaurant r: restaurants) {
      for (Preference p: preferences) {
        restaurantRelationships.put("String", getRelated(r.getId()));
      }
    }
  }

  public Map<String, Integer> getRelated(final String restaurantID) {
    return null;
  }
}
