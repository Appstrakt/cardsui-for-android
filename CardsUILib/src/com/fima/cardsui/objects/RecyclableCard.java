package com.fima.cardsui.objects;

import android.view.View;

/**
 * A card that can be recycled when scrolled off screen.
 */
public abstract class RecyclableCard extends Card {

    public RecyclableCard() {
        super();
    }

    /**
     * Set the values of child views.  The view will not be null and
     * is guaranteed to have the layout of the root element equal to
     * the layout resource ID from getCardLayoutId().
     * @param convertView non-null view to modify
     */
    protected abstract void applyTo(View convertView);

    /**
     * Get the R.layout ID of the root element of the content of the card.
     * This value will be used to inflate the card and check whether an
     * old card's View can be recycled.
     * @return layout ID of the card content
     */
    protected abstract int getCardLayoutId();

    @Override
    public boolean convert(View convertCardView) {
        View view = convertCardView.findViewById(getCardLayoutId());
        if (view == null) {
            return false;
        }

        applyTo(view);
        return true;
    }

}