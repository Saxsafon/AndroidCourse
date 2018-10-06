package design.alex.starwars;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    List<TextView> mPersonsNamesViews;
    List<LinearLayout> mCardLayoutViews;
    FrameLayout mProgressLayout;
    FrameLayout mContentLayout;


    private List<String> mNames;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        bindViews();
        showProgress();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_1_wrapper:
                startCardActivity(mNames.get(0));
                break;
            case R.id.card_2_wrapper:
                startCardActivity(mNames.get(1));
                break;
            case R.id.card_3_wrapper:
                startCardActivity(mNames.get(2));
                break;
            case R.id.card_4_wrapper:
                startCardActivity(mNames.get(3));
                break;
            case R.id.card_5_wrapper:
                startCardActivity(mNames.get(4));
                break;
            case R.id.action_load_data:
                loadData();
                showContent();
                break;
        }
    }

    private void startCardActivity(String name) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra(CardActivity.PARAM_NAME, name);
        startActivity(intent);
    }

    private void bindViews() {
        mPersonsNamesViews = new ArrayList<>();
        mCardLayoutViews = new ArrayList<>();
        mCardLayoutViews.add((LinearLayout) findViewById(R.id.card_1_wrapper));
        mCardLayoutViews.add((LinearLayout) findViewById(R.id.card_2_wrapper));
        mCardLayoutViews.add((LinearLayout) findViewById(R.id.card_3_wrapper));
        mCardLayoutViews.add((LinearLayout) findViewById(R.id.card_4_wrapper));
        mCardLayoutViews.add((LinearLayout) findViewById(R.id.card_5_wrapper));
        mPersonsNamesViews.add((TextView) mCardLayoutViews.get(0).findViewById(R.id.person_name_text_view));
        mPersonsNamesViews.add((TextView) mCardLayoutViews.get(1).findViewById(R.id.person_name_text_view));
        mPersonsNamesViews.add((TextView) mCardLayoutViews.get(2).findViewById(R.id.person_name_text_view));
        mPersonsNamesViews.add((TextView) mCardLayoutViews.get(3).findViewById(R.id.person_name_text_view));
        mPersonsNamesViews.add((TextView) mCardLayoutViews.get(4).findViewById(R.id.person_name_text_view));

        mProgressLayout = findViewById(R.id.progress);
        mContentLayout = findViewById(R.id.content);

        Button actionLoadData = findViewById(R.id.action_load_data);

        actionLoadData.setOnClickListener(this);
        for (LinearLayout layout : mCardLayoutViews) {
            layout.setOnClickListener(this);
        }
    }

    private void showContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressLayout.setVisibility(View.GONE);
    }

    private void showProgress() {
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.VISIBLE);
    }


    private void loadData() {
        mNames = new ArrayList<>();
        String[] names = getResources().getStringArray(R.array.persons);
        for (String name : names) {
            mNames.add(name);
        }

        for (int index = 0; index < mNames.size(); index++) {
            if (mPersonsNamesViews.size() > index) {
                mPersonsNamesViews.get(index).setText(mNames.get(index));
            }
        }
    }
}
