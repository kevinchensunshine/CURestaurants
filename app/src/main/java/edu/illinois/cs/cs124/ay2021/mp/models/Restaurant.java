package edu.illinois.cs.cs124.ay2021.mp.models;

import androidx.annotation.NonNull;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
/*
 * Model storing information about a restaurant retrieved from the restaurant server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 * If your project builds successfully, you can safely ignore the warning about "Related problems" here.
 * It seems to be a bug in Android studio.
 */
@SuppressWarnings("unused")
public final class Restaurant implements SortedListAdapter.ViewModel {
  // Name of the restaurant
  private String name;

    // Getter for the name
  public String getName() {
    return name;
  }

  // You will need to add more fields here...
  // Name of the restaurant
  private String cuisine;

  // Getter for the name
  public String getCuisine() {
    return cuisine;
  }

  private String id;

  // Getter for the name
  public String getId() {
    return id;
  }

  public static List<Restaurant> search(final List<Restaurant> restaurants, final String search) {
    if (restaurants == null || search == null) {
      throw new IllegalArgumentException();
    }
    String searching = search.trim().toLowerCase();
    List<Restaurant> toReturn = new ArrayList<Restaurant>();
    if (searching.isEmpty()) {
      toReturn = new ArrayList<Restaurant>(restaurants);
      return toReturn;
    }
    for (Restaurant n : restaurants) {
      if (n.getCuisine() != null) {
        if (n.getCuisine().toLowerCase().equals(searching)) {
          toReturn.add(n);
        }
      }
    }
    if (toReturn.size() > 0) {
      return toReturn;
    }
    for (Restaurant a : restaurants) {
      if (a.getCuisine() != null) {
        String name = a.getName().toLowerCase();
        String cuisine = a.getCuisine().toLowerCase();
        if (name.contains(searching) || cuisine.contains(searching)) {
          toReturn.add(a);
        }
      }
    }
    return toReturn;
  }
  /*
   * The Jackson JSON serialization library we are using requires an empty constructor.
   * So don't remove this!
   */
  public Restaurant() {}

  /*
   * Function to compare Restaurant instances by name.
   * Currently this does not work, but you will need to implement it correctly for MP1.
   * Comparator is like Comparable, except it defines one possible ordering, not a canonical ordering for a class,
   * and so is implemented as a separate method rather than directly by the class as is done with Comparable.
   */
  public static final Comparator<Restaurant> SORT_BY_NAME = ((restaurant1, restaurant2) -> {
    boolean check = false;
    int i = 0;
    while (!check) {
      if (restaurant1.getName().charAt(i) > restaurant2.getName().charAt(i)) {
        check = true;
        return 1;
      } else if (restaurant1.getName().charAt(i) < restaurant2.getName().charAt(i)) {
        check = false;
        return -1;
      } else if (i == restaurant1.getName().length() - 1) {
        return 0;
      } else {
        i++;
      }
    }
    return 0;
  });
  @Override
  public boolean equals(final Object o) {
    if (!(o instanceof Restaurant)) {
      return false;
    }
    Restaurant r = (Restaurant) o;
    return id.equals(r.getId());
  }
  @Override
  public int hashCode() {
    return id.hashCode();
  }
  @Override
  public String toString() {
    return name;
  }
  // You should not need to modify this code, which is used by the list adapter component
  @Override
  public <T> boolean isSameModelAs(@NonNull final T model) {
    return equals(model);
  }

  // You should not need to modify this code, which is used by the list adapter component
  @Override
  public <T> boolean isContentTheSameAs(@NonNull final T model) {
    return equals(model);
  }
}
