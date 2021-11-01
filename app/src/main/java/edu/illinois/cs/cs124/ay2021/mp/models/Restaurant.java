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
 *
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

  public static List<Restaurant> search(final List<Restaurant> restaurants, final String bar) {
    if (restaurants == null || bar == null) {
      throw new IllegalArgumentException();
    }
    String searching = bar.toLowerCase();
    List<Restaurant> toReturn = new ArrayList<>();
    for (int i = 0; i < restaurants.size(); i++) {
      String cuisine = restaurants.get(i).getCuisine().trim().toLowerCase();
      String name = restaurants.get(i).getName().trim().toLowerCase();
      if (cuisine == searching) {
        toReturn.add(restaurants.get(i));
      } else if (name.contains(searching)) {
        toReturn.add(restaurants.get(i));
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
