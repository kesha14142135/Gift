package g.girl.contract;

import android.content.Context;

/**
 * Created by sergej on 08.02.17.
 */

public interface BaseContract {

    interface View {

        Context getContext();

        void showError(String message);

    }

    interface Presenter<V extends View> {

        void attachView(V view);

        void detachView();

    }

}
