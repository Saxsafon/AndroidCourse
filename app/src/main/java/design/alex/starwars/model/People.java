package design.alex.starwars.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.regex.Matcher;

import design.alex.starwars.R;

public class People {

    @SerializedName("name")
    private String mName;

    @SerializedName("height")
    private String mHeight;

    @SerializedName("mass")
    private String mMass;

    @SerializedName("hair_color")
    private String mHairColor;

    @SerializedName("skin_color")
    private String mSkinColor;

    @SerializedName("eye_color")
    private String mEyeColor;

    @SerializedName("birth_year")
    private String mBirthYear;

    @SerializedName("gender")
    private String mGender;

    @SerializedName("homeworld")
    private String mHoumeWorld;

    public String getName() {
        return mName;

    }
}


