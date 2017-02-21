// Generated code from Butter Knife. Do not modify!
package io.palliassist.palliassistmobile;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class ESASFragment$$ViewInjector<T extends io.palliassist.palliassistmobile.ESASFragment> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624080, "field '_submitButton'");
    target._submitButton = finder.castView(view, 2131624080, "field '_submitButton'");
  }

  @Override public void reset(T target) {
    target._submitButton = null;
  }
}
