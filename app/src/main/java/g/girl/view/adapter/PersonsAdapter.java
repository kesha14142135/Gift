package g.girl.view.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import g.girl.constant.Constant;
import g.girl.R;
import g.girl.model.Person;
import g.girl.view.callBackActivity.CallBackPerson;

/**
 * Created by sergej on 09.02.17.
 */

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonViewHolder> {

    private List<Person> mPersons;
    private CallBackPerson mCallBack;
    private Context mContext;

    public PersonsAdapter(List<Person> persons, Context context, CallBackPerson callBack) {
        mPersons = persons;
        mContext = context;
        mCallBack = callBack;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_person,
                parent, false);
        return new PersonViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(PersonViewHolder holder, final int position) {
        holder.mTextViewName.setText(mPersons.get(position).getName());
        holder.mTextViewType.setText(Constant.GIRLS_TYPES[mPersons.get(position).getType()]);
        switch (mPersons.get(position).getType()) {
            case 0:
                holder.mCircleImageView.setImageResource(R.drawable.ic_girl);
                break;
            case 1:
                holder.mCircleImageView.setImageResource(R.drawable.ic_mother);
                break;
            case 2:
                holder.mCircleImageView.setImageResource(R.drawable.ic_daughter);
                break;
            case 3:
                holder.mCircleImageView.setImageResource(R.drawable.ic_grandfather);
                break;
            case 4:
                holder.mCircleImageView.setImageResource(R.drawable.ic_man);
                break;
        }
        if (mPersons.get(position).getPassedTheTest() == 1) {
            holder.mButtonTestPerson.setVisibility(View.INVISIBLE);
            GregorianCalendar birthday = (GregorianCalendar) GregorianCalendar.getInstance();

            birthday.set(
                    Calendar.YEAR,
                    mPersons.get(position).getMonth(),
                    mPersons.get(position).getDay()
            );
            if (mContext.getResources().getString(R.string.today) == dateOutput(birthday)){
                holder.mTextViewDay.setBackground(mContext.getResources().getDrawable(R.drawable.text_view_with_radius_background));
                holder.mTextViewDay.setHeight(50);
                holder.mTextViewDay.setWidth(180);
                holder.mTextViewDay.setTextColor(mContext.getResources().getColor(R.color.white));
            }
                holder.mTextViewDay.setText(dateOutput(birthday));

            holder.mCardViewPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.successUser(mPersons.get(position).getId(), mPersons.get(position).getType());
                }
            });
        } else {
            holder.mButtonTestPerson.setVisibility(View.VISIBLE);
            holder.mTextViewDay.setVisibility(View.INVISIBLE);
            holder.mButtonTestPerson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.successTest(mPersons.get(position).getId(), mPersons.get(position).getType());
                }
            });
        }
    }

    private String dateOutput(Calendar birthday) {
        String dateOutput;
        int currentDay = (GregorianCalendar.getInstance()).get(Calendar.DAY_OF_YEAR);
        int birthdayData = currentDay - birthday.get(Calendar.DAY_OF_YEAR);
        if (birthdayData < 0) {
            birthdayData = birthday.get(Calendar.DAY_OF_YEAR) - currentDay;
            dateOutput = mContext.getResources().getQuantityString(R.plurals.review_day_string, birthdayData, birthdayData);
        } else if (birthdayData == 0) {
            dateOutput = mContext.getResources().getString(R.string.today);
        } else {
            dateOutput = mContext.getResources().getQuantityString(R.plurals.review_day_string,
                    (birthday.getActualMaximum(Calendar.DAY_OF_YEAR) - birthdayData),
                    (birthday.getActualMaximum(Calendar.DAY_OF_YEAR) - birthdayData));
        }
        return dateOutput;
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        private CardView mCardViewPerson;
        private TextView mTextViewName;
        private TextView mTextViewType;
        private TextView mTextViewDay;
        private Button mButtonTestPerson;
        private CircleImageView mCircleImageView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            mCardViewPerson = (CardView) itemView.findViewById(R.id.card_view_recycler_person);
            mTextViewName = (TextView) itemView.findViewById(R.id.text_view_card_person_name);
            mTextViewType = (TextView) itemView.findViewById(R.id.text_view_card_person_type);
            mTextViewDay = (TextView) itemView.findViewById(R.id.text_view_card_person_day);
            mButtonTestPerson = (Button) itemView.findViewById(R.id.button_card_person_test);
            mCircleImageView = (CircleImageView) itemView.findViewById(R.id.image_card_view_person);
        }
    }
}
