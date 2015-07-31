package hackny.checkin;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hackny.checkin.models.User;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = "MainActivityFragment";

    private ArrayList<User> objects;
    private ListView listView;
    private CheckInAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) view.findViewById(R.id.listview);
        objects = new ArrayList<User>();
        adapter = new CheckInAdapter(getActivity(),objects);

        listView.setAdapter(adapter);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://my.mlh.io")
                .build();

        CheckInService service = restAdapter.create(CheckInService.class);

        Callback callback = new Callback<List<User>>() {

            @Override
            public void success(List<User> users, Response response) {
                Log.d(TAG, users.toString());
                for (User user : users) {
                  objects.add(user);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.toString());
            }
        };

        service.listUsers(BuildConfig.HACKNY_APP_ID, BuildConfig.HACKNY_APP_SECRET, callback);
        return view;
    }

    public class CheckInAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<User> objects;

        public CheckInAdapter(Context context, ArrayList<User> objects){
            mInflater = LayoutInflater.from(context);
            this.objects = objects;
        }

        @Override
        public int getCount() {
            return objects.size();
        }

        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if (convertView == null){
                view = mInflater.inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder(view);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }

            User user = objects.get(position);

            holder.name.setText(user.getFull_name());
            holder.school.setText(user.getSchool().getName());
            holder.shirt.setText(user.getShirt_size());

            return view;
        }

        private class ViewHolder {

            public CheckBox checkBox;
            public TextView name;
            public TextView school;
            public TextView shirt;

            public ViewHolder(View view){
                checkBox = (CheckBox) view.findViewById(R.id.checkin);
                name     = (TextView) view.findViewById(R.id.name);
                school   = (TextView) view.findViewById(R.id.school);
                shirt    = (TextView) view.findViewById(R.id.shirt_size);
            }
        }
    }
}
