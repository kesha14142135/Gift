package g.girl.model;

/**
 * Created by sergej on 25.02.17.
 */

public class ManSize {

    private int mHeight;
    private String mStomach;
    private String mHips;
    private String mShoeSize;

    public ManSize() {
        this(0, "", "", "");
    }

    public ManSize(int height, String stomach, String hips, String shoeSize) {
        mHeight = height;
        mStomach = stomach;
        mHips = hips;
        mShoeSize = shoeSize;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public String getStomach() {
        return mStomach;
    }

    public void setStomach(String stomach) {
        mStomach = stomach;
    }

    public String getHips() {
        return mHips;
    }

    public void setHips(String hips) {
        mHips = hips;
    }

    public String getShoeSize() {
        return mShoeSize;
    }

    public void setShoeSize(String shoeSize) {
        mShoeSize = shoeSize;
    }

}
