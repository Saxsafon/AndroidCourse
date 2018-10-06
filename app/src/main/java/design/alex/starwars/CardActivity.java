package design.alex.starwars;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CardActivity extends AppCompatActivity {

    public static final String PARAM_NAME = "star.wars.card.param.id";

    TextView mPersonNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity);
        mPersonNameTextView = findViewById(R.id.person_name_text_view);
        setData();
    }

    private void setData() {
        if (getIntent().hasExtra(PARAM_NAME)) {
            mPersonNameTextView.setText(getIntent().getStringExtra(PARAM_NAME));
        }
    }
}
