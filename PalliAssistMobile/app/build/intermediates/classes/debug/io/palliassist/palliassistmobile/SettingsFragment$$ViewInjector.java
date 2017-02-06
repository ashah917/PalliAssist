// Generated code from Butter Knife. Do not modify!
package io.palliassist.palliassistmobile;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class SettingsFragment$$ViewInjector<T extends io.palliassist.palliassistmobile.SettingsFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624171, "field '_logoutButton'");
    target._logoutButton = finder.castView(view, 2131624171, "field '_logoutButton'");
  }

  @Override public void reset(T target) {
    target._logoutButton = null;
  }
}
