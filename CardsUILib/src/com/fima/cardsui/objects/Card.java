package com.fima.cardsui.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.appstrakt.android.core.data.adapters.IBindable;
import com.appstrakt.android.core.helper2.LogcatHelper;
import com.appstrakt.android.core.view.IBindableView;
import com.fima.cardsui.R;
import com.fima.cardsui.Utils;

public abstract class Card extends AbstractCard implements IBindable {

    private final static String TAG = "appstrakt_card";

    protected View mCardLayout;
    private OnCardSwiped onCardSwipedListener;
    private OnClickListener mListener;


    @Override
    public View getView(Context context, boolean swipable) {
        return getView(context, false);
    }

    @Override
    public View getView(Context context) {

        View view = LayoutInflater.from(context).inflate(getCardLayout(), null);

        mCardLayout = view;

        // ((TextView) view.findViewById(R.id.title)).setText(this.title);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int bottom = Utils.convertDpToPixelInt(context, 12);
        lp.setMargins(0, 0, 0, bottom);

        view.setLayoutParams(lp);

        bindView(view);

        return view;
    }

    public View getViewLast(Context context) {

        View view = LayoutInflater.from(context).inflate(getLastCardLayout(),
                null);

        mCardLayout = view;

        // ((TextView) view.findViewById(R.id.title)).setText(this.title);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int bottom = Utils.convertDpToPixelInt(context, 12);
        lp.setMargins(0, 0, 0, bottom);

        view.setLayoutParams(lp);

        bindView(view);

        return view;
    }

    public View getViewFirst(Context context) {

        View view = LayoutInflater.from(context).inflate(getFirstCardLayout(),
                null);

        mCardLayout = view;

        // ((TextView) view.findViewById(R.id.title)).setText(this.title);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int bottom = Utils.convertDpToPixelInt(context, 12);
        lp.setMargins(0, 0, 0, bottom);

        view.setLayoutParams(lp);

        bindView(view);

        return view;
    }

    private void bindView(View v) {
        if (v != null && v instanceof IBindableView) {
            ((IBindableView) v).bind(this,-1);
        } else {
            LogcatHelper.get().d(TAG, "Card view is null or not bindable ... FIX IT !! " + v == null ? "" : v.getClass().getSimpleName());
        }
    }

    public OnClickListener getClickListener() {
        return mListener;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public void OnSwipeCard() {
        if (onCardSwipedListener != null)
            onCardSwipedListener.onCardSwiped(this, mCardLayout);
        // TODO: find better implementation to get card-object's used content
        // layout (=> implementing getCardContent());
    }

    public OnCardSwiped getOnCardSwipedListener() {
        return onCardSwipedListener;
    }

    public void setOnCardSwipedListener(OnCardSwiped onEpisodeSwipedListener) {
        this.onCardSwipedListener = onEpisodeSwipedListener;
    }

    protected int getLayoutId() {
        return R.id.cardContent;
    }

    protected int getLastCardLayout() {
        return R.layout.item_card_empty_last;
    }

    protected int getFirstCardLayout() {
        return R.layout.item_play_card_empty_first;
    }

    public interface OnCardSwiped {
        public void onCardSwiped(Card card, View layout);
    }

    /**
     * Attempt to reuse convertCardView.  Should not modify convertCardView if it's
     * not compatible.  The implementer should check the card content part and
     * verify that it matches.
     *
     * @param convertCardView the view to convert, with root Id equal to Card.getId()
     * @return true on success, false if not compatible
     */
    public abstract boolean convert(View convertCardView);

}