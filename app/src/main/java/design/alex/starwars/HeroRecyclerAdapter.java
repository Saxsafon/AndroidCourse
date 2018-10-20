package design.alex.starwars;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import design.alex.starwars.model.People;

public class HeroRecyclerAdapter
        extends
        RecyclerView.Adapter<HeroRecyclerAdapter.HeroViewHolder> {

    private List<People> mPeoples = new ArrayList<>();

    public void addAll(List<People> peoples) {
        mPeoples.addAll(peoples);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_card_wrapper, viewGroup, false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder heroViewHolder, int position) {
        heroViewHolder.setPosition(position);
        People people = mPeoples.get(position);
        heroViewHolder.bind(people);
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    public static class HeroViewHolder
            extends
            RecyclerView.ViewHolder
            implements
            View.OnClickListener {

        private TextView mHeroNameTextView;
        private LinearLayout mContainer;

        private int mPosition;

        HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            mContainer = itemView.findViewById(R.id.container);
            mHeroNameTextView = itemView.findViewById(R.id.person_name_text_view);
            mContainer.setOnClickListener(this);
        }

        void setPosition(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View view) {
            Log.d("TAG", "mPosition: " + mPosition);
        }

        public void bind(People people) {
            mHeroNameTextView.setText(people.getName());
        }
    }
}
