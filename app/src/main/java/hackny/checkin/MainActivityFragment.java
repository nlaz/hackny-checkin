package hackny.checkin;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private ArrayList<String> objects;
    private ListView listView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) view.findViewById(R.id.listview);


        return view;
    }

    public class CheckInAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<String> objects;


        public CheckInAdapter(Context context, ArrayList<String> objects){
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

            //TODO get User and set fields;
            
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
