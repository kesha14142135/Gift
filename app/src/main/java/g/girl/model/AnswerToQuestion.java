package g.girl.model;

/**
 * Created by sergej on 07.03.17.
 */

public class AnswerToQuestion extends Question {

    private int mIdUser;
    private boolean mSelected;

    public AnswerToQuestion() {
        this("", "", 0, "", 0, false);
    }

    public AnswerToQuestion(String name, String hashtag, int idUser, String url, int typeUser, boolean selected) {
        super(name, hashtag, typeUser, url);
        mSelected = selected;
        mIdUser = idUser;
    }

    public AnswerToQuestion(Question question, int idUser, boolean selected) {
        super(question.getName(), question.getHashtag(), question.getTypeUser(), question.getUrl());
        mSelected = selected;
        mIdUser = idUser;
    }

    public boolean getSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        mSelected = selected;
    }

    public int getIdUser() {
        return mIdUser;
    }

    public void setIdUser(int idUser) {
        mIdUser = idUser;
    }
}
