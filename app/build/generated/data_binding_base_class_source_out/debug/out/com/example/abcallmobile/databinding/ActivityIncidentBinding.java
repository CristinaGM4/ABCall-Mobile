// Generated by view binder compiler. Do not edit!
package com.example.abcallmobile.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.abcallmobile.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityIncidentBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final BottomNavBarBinding bottomNav;

  @NonNull
  public final Button btnFAQ;

  @NonNull
  public final Button btnNuevoIncidente;

  @NonNull
  public final RelativeLayout layoutPrincipal;

  @NonNull
  public final RecyclerView recyclerIncidentes;

  @NonNull
  public final ToolbarAbcallBinding toolbar;

  private ActivityIncidentBinding(@NonNull RelativeLayout rootView,
      @NonNull BottomNavBarBinding bottomNav, @NonNull Button btnFAQ,
      @NonNull Button btnNuevoIncidente, @NonNull RelativeLayout layoutPrincipal,
      @NonNull RecyclerView recyclerIncidentes, @NonNull ToolbarAbcallBinding toolbar) {
    this.rootView = rootView;
    this.bottomNav = bottomNav;
    this.btnFAQ = btnFAQ;
    this.btnNuevoIncidente = btnNuevoIncidente;
    this.layoutPrincipal = layoutPrincipal;
    this.recyclerIncidentes = recyclerIncidentes;
    this.toolbar = toolbar;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityIncidentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityIncidentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_incident, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityIncidentBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.bottom_nav;
      View bottomNav = ViewBindings.findChildViewById(rootView, id);
      if (bottomNav == null) {
        break missingId;
      }
      BottomNavBarBinding binding_bottomNav = BottomNavBarBinding.bind(bottomNav);

      id = R.id.btnFAQ;
      Button btnFAQ = ViewBindings.findChildViewById(rootView, id);
      if (btnFAQ == null) {
        break missingId;
      }

      id = R.id.btnNuevoIncidente;
      Button btnNuevoIncidente = ViewBindings.findChildViewById(rootView, id);
      if (btnNuevoIncidente == null) {
        break missingId;
      }

      RelativeLayout layoutPrincipal = (RelativeLayout) rootView;

      id = R.id.recyclerIncidentes;
      RecyclerView recyclerIncidentes = ViewBindings.findChildViewById(rootView, id);
      if (recyclerIncidentes == null) {
        break missingId;
      }

      id = R.id.toolbar;
      View toolbar = ViewBindings.findChildViewById(rootView, id);
      if (toolbar == null) {
        break missingId;
      }
      ToolbarAbcallBinding binding_toolbar = ToolbarAbcallBinding.bind(toolbar);

      return new ActivityIncidentBinding((RelativeLayout) rootView, binding_bottomNav, btnFAQ,
          btnNuevoIncidente, layoutPrincipal, recyclerIncidentes, binding_toolbar);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
