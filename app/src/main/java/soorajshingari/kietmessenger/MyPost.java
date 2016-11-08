package soorajshingari.kietmessenger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MyPost extends Fragment {
     RecyclerView recyclerView;
     View parentView;
     RecyclerView.LayoutManager layoutManager;
     RecyclerView_Adapter adapter;
    //public Button answer1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentView = inflater.inflate(R.layout.fragment_my_post, container, false);
        recyclerView = (RecyclerView) parentView.findViewById(R.id.post_cards);
        adapter = new RecyclerView_Adapter(parentView.getContext(),getData());
        // answer1=(Button)parentView.findViewById(R.id.answer);
        recyclerView.setAdapter(adapter);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return parentView;

    }

    public static List<singlePostCard> getData() {
        List<singlePostCard> card = new ArrayList<>();
        int[] icons={R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora,R.drawable.collegequora};
        String[] titles = {"Question 1", "Question 2", "Question 3", "Question 4", "Question 5", "Question 6", "Question 7", "Question 8"};
       // String[] ans = {"Answer1", "Answer2"};

        for(int i=0;i<titles.length;i++)
        {
            singlePostCard data=new singlePostCard();
            data.image=icons[i];
            data.question=titles[i];
            data.personName="Posted By : 1502913112";
            card.add(data);
        }
        return card;

    }

}
