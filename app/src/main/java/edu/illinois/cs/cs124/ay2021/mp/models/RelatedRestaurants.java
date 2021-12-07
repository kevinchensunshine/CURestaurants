package edu.illinois.cs.cs124.ay2021.mp.models;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

public class RelatedRestaurants {
  private Map<String, Map<String, Integer>> restaurantRelationships = new HashMap<>();

  private final Map<String, Restaurant> restaurantMap = new HashMap();

  public RelatedRestaurants(final List<Restaurant> restaurants, final List<Preference> preferences) {
    Set<String> checkedRestaurants = new HashSet();
    for (Restaurant r : restaurants) {
      restaurantMap.put(r.getId(), r);
    }
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

  public List<Restaurant> getRelatedInOrder(final String id) {
    if (id == null || id.isEmpty() || restaurantMap.get(id) == null) {
      throw new IllegalArgumentException();
    }
    Map<String, Integer> relatedIDs = getRelated(id);
    relatedIDs.remove(id);
    List<String> ids = new ArrayList();
    for (Map.Entry<String, Integer> r : relatedIDs.entrySet()) {
      ids.add(r.getKey());
    }
    Comparator<String> sortAlphaLength = (s1, s2) -> {
      if (relatedIDs.get(s1) - relatedIDs.get(s2) == 0) {
        return restaurantMap.get(s1).getName().compareTo(restaurantMap.get(s2).getName());
      } else {
        return (relatedIDs.get(s2) - relatedIDs.get(s1));
      }
    };
    Collections.sort(ids, sortAlphaLength);
    List<Restaurant> stringToRestaurant = new ArrayList();
    for (String a : ids) {
      stringToRestaurant.add(restaurantMap.get(a));
    }
    return stringToRestaurant;
  }

  public Set<Restaurant> getConnectedTo(final String restaurantID) {
    if (restaurantID == null || restaurantID == "" || !restaurantMap.containsKey(restaurantID)) {
      throw new IllegalArgumentException();
    }
    Set<String> connectedSet = new HashSet<>();
    Set<String> returnedSet = helper(restaurantID, connectedSet);
    Set<Restaurant> restaurantSet = new HashSet<>();
    for (String i : returnedSet) {
      restaurantSet.add(restaurantMap.get(i));
    }
    return restaurantSet;
  }
  private Set<String> helper(final String id, final Set<String> ids) {
    Map<String, Integer> neighbors = getRelated(id);
    neighbors.remove(id);
    for (String i : neighbors.keySet()) {
      Map<String, Integer> twoStep = getRelated(i);
      twoStep.remove(i);
      if (neighbors.get(i) > 1 && !ids.contains(neighbors.get(i))) {
        ids.add(i);
        for (String j : twoStep.keySet()) {
          if (twoStep.get(j) > 1 && !ids.contains(twoStep.get(i))) {
            ids.add(j);
          }
        }
      }
    }
    ids.remove(id);
    return ids;
  }
}
