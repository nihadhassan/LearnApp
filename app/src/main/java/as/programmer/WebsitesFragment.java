package as.programmer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class WebsitesFragment extends Fragment {

    private WebsiteClickListener websiteClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        websiteClickListener = (WebsiteClickListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_websites, container, false);

        String [] names = getResources().getStringArray(R.array.names);

        ArrayList<Website> websites = new ArrayList<>();

        for (int i = 0; i < names.length; i++){
            websites.add(i, new Website(Helper.images[i], names[i]));
        }

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), websites);
        GridView gridView = view.findViewById(R.id.gridView);

        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                websiteClickListener.onWebsiteClick(position);
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }

}