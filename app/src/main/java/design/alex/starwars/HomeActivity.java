package design.alex.starwars;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import design.alex.starwars.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity
        extends
        AppCompatActivity {

    FrameLayout mProgressLayout;
    FrameLayout mContentLayout;
    FrameLayout mErrorLayout;

    RecyclerView mRecyclerView;

    private HeroRecyclerAdapter mAdapter;
    private RecyclerScrollListener mScrollListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        bindViews();
        setupList();
        setupListener();
        showProgress();
        loadData();
    }

    private void startCardActivity(String name) {
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra(CardActivity.PARAM_NAME, name);
        startActivity(intent);
    }

    private void bindViews() {
        mProgressLayout = findViewById(R.id.progress);
        mContentLayout = findViewById(R.id.content);
        mErrorLayout = findViewById(R.id.error);
        mRecyclerView = findViewById(R.id.recycler_view);
    }

    private void setupList() {
        mAdapter = new HeroRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupListener() {
        mScrollListener = new RecyclerScrollListener();
        mRecyclerView.addOnScrollListener(mScrollListener);
    }

    private void showContent() {
        mContentLayout.setVisibility(View.VISIBLE);
        mProgressLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
    }

    private void showProgress() {
        mContentLayout.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.VISIBLE);
    }

    private void showError() {
        mErrorLayout.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
        mProgressLayout.setVisibility(View.GONE);
    }

    private void loadData() {
        ((App)getApplication()).getPeopleRestService()
                .getAllPeoples(1).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(@NonNull Call<Result> call, @NonNull Response<Result> response) {
                mScrollListener.setLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    mAdapter.addAll(response.body().getResults());
                    showContent();
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result> call, @NonNull Throwable t) {
                mScrollListener.setLoading(false);
                showError();
            }
        });
    }

    private void fetchData(Integer page) {
        ((App)getApplication()).getPeopleRestService().getAllPeoples(page).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                mScrollListener.setLoading(false);
                if (response.isSuccessful() && response.body() != null) {
                    mAdapter.addAll(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                mScrollListener.setLoading(false);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private class RecyclerScrollListener extends RecyclerView.OnScrollListener {

        private Integer mTotalCount;
        private Integer mLastItem;
        private Integer mThreshold = 5;
        private Boolean mIsLoading = false;

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (mRecyclerView.getLayoutManager() == null) {
                return;
            }
            mTotalCount = mRecyclerView.getLayoutManager().getItemCount();
            mLastItem = ((LinearLayoutManager)mRecyclerView.getLayoutManager())
                    .findLastVisibleItemPosition();
            if (!mIsLoading && mTotalCount < (mLastItem + mThreshold)) {
                fetchData((mTotalCount / 10) + 1);
                setLoading(true);
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        public void setLoading(Boolean loading) {
            mIsLoading = loading;
        }
    }
}
