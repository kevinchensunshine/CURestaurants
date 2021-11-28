package edu.illinois.cs.cs124.ay2021.mp.models;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class RelatedRestaurants {
  private Map<String, Map<String, Integer>> restaurantRelationships = new HashMap<>();

  public RelatedRestaurants(final List<Restaurant> restaurants, final List<Preference> preferences) {
    Set<String> checkedRestaurants = new HashSet();
    for (Restaurant r : restaurants) {
      checkedRestaurants.add(r.getId());
    }
    for (Restaurant r: restaurants) {
      Map<String, Integer> related = new HashMap<>();
      for (Preference p : preferences) {
        for (String g : p.getRestaurantIDs()) {
          if (r.getId().equals(g)) {
            for (String m : p.getRestaurantIDs()) {
              if (related.containsKey(m) && m != r.getId() && checkedRestaurants.contains(m)) {
                related.put(m, related.get(m) + 1);
              } else if (m != r.getId() && checkedRestaurants.contains(m)) {
                related.put(m, 1);
              }
            }
          }
        }
      }
      restaurantRelationships.put(r.getId(), related);
    }
  }
  public Map<String, Integer> getRelated(final String restaurantID) {
    Map<String, Integer> returnMap = restaurantRelationships.get(restaurantID);
    if (returnMap == null) {
      return new HashMap<>();
    } else {
      return returnMap;
    }
  }
}
