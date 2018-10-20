package design.alex.starwars.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("count")
    private Integer mCount;

    @SerializedName("results")
    private List<People> mResults;

    public Integer getCount() {
        return mCount;
    }

    public List<People> getResults() {
        return mResults;
    }
}
