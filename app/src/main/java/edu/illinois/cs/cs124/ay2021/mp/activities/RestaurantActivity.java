package edu.illinois.cs.cs124.ay2021.mp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.illinois.cs.cs124.ay2021.mp.R;
import edu.illinois.cs.cs124.ay2021.mp.application.EatableApplication;
import edu.illinois.cs.cs124.ay2021.mp.databinding.ActivityRestaurantBinding;
import edu.illinois.cs.cs124.ay2021.mp.models.RelatedRestaurants;
import edu.illinois.cs.cs124.ay2021.mp.models.Restaurant;
//mp part 2 activity display

public class RestaurantActivity extends AppCompatActivity {
  //binding to layout
  private ActivityRestaurantBinding binding;

  private EatableApplication application;

  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent startedIntent = getIntent();
    startedIntent.getExtras();
    binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant);
    application = (EatableApplication) getApplication();
    Restaurant r = application.getClient().getRestaurantMapID(startedIntent.getStringExtra("id"));
    RelatedRestaurants rR = application.getClient().getRelated();
    //getting the data
    Restaurant first = new Restaurant();
    if (rR.getRelatedInOrder(startedIntent.getStringExtra("id")).size() != 0) {
      first = rR.getRelatedInOrder(startedIntent.getStringExtra("id")).get(0);
    }
    int count = rR.getConnectedTo(startedIntent.getStringExtra("id")).size();
    binding.name.setText(r.getName() + " " + r.getCuisine() + " " + first.getName() + " " + String.valueOf(count));
  }
}
