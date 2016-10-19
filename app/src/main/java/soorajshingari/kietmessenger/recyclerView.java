package soorajshingari.kietmessenger;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shrey on 04-10-2016.
 */

class recyclerView extends android.support.v4.app.Fragment {

    private RecyclerView recyclerView;
    private View parentView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView_Adapter adapter;
    //public Button answer1;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.recycler_view_post, container, false);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.post_cards);
        adapter = new RecyclerView_Adapter(getContext(),getData());
        // answer1=(Button)parentView.findViewById(R.id.answer);
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return parentView;

    }

    public static List<singlePostCard> getData() {
        List<singlePostCard> card = new ArrayList<>();
        int[] icons={R.drawable.icon,R.drawable.icon};
        String[] titles = {"Question 1", "Question 2"};
        String[] ans = {"Answer1", "Answer2"};

        for(int i=0;i<titles.length;i++)
        {
            singlePostCard data=new singlePostCard();
            data.image=icons[i];
            data.question=titles[i];
            data.personName="Shrey";
            card.add(data);
        }
        return card;

    }
}
