package com.fima.cardsui.objects;

import android.content.Context;
import android.view.View;

public abstract class AbstractCard implements IAbstractCard {

    protected Boolean hasOverflow, isClickable;

    public abstract View getView(Context context);

    public abstract View getView(Context context, boolean swipable);

    public Boolean getHasOverflow() {
        return hasOverflow;
    }

    public Boolean getIsClickable() {
        return isClickable;
    }

}
