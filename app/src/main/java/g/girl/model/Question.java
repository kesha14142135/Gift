package g.girl.model;

/**
 * Created by sergej on 07.03.17.
 */

public class Question {
    private String mName;
    private String mHashtag;
    private int mTypeUser;
    private String mUrl;

    public Question() {
        this("", "", 0, "");
    }

    public Question(String name, String hashtag, int typeUser, String url) {
        mName = name;
        mHashtag = hashtag;
        mTypeUser = typeUser;
        mUrl = url;
    }

    public String getHashtag() {
        return mHashtag;
    }

    public void setHashtag(String hashtag) {
        mHashtag = hashtag;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getTypeUser() {
        return mTypeUser;
    }

    public void setTypeUser(int typeUser) {
        mTypeUser = typeUser;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
