package com.example.mapbuilder;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment
{

    private class MapDataVHolder extends RecyclerView.ViewHolder
    {
        private ImageView bigImageView;
        private ImageView tlImageView;
        private ImageView trImageView;
        private ImageView blImageView;
        private ImageView brImageView;

        public MapDataVHolder(LayoutInflater li,
                             ViewGroup parent)
        {
            super(li.inflate(R.layout.grid_cell, parent, false));
            bigImageView = itemView.findViewById(R.id.big_imageView);
            tlImageView = itemView.findViewById(R.id.tl_imageView);
            trImageView = itemView.findViewById(R.id.tr_imageView);
            blImageView = itemView.findViewById(R.id.bl_imageView);
            brImageView = itemView.findViewById(R.id.br_imageView);

            int size = parent.getMeasuredHeight() / MapData.HEIGHT + 1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;

        }

        public void bind(MapElement element) // Called by your adapter
        {
            tlImageView.setImageResource(element.getNorthWest());
            trImageView.setImageResource(element.getNorthEast());
            blImageView.setImageResource(element.getSouthWest());
            brImageView.setImageResource(element.getSouthEast());
        }
    }

    private class MapAdapter extends RecyclerView.Adapter<MapDataVHolder>
    {
        private List<MapElement> grid;

        public MapAdapter(MapElement[][] element)
        {
            grid = new ArrayList<MapElement>();

            for (int jj = 0; jj < 10; jj++)
            {
                for (int ii = 0; ii < 10; ii++)
                {
                    grid.add(element[ii][jj]);
                }
            }
        }

        @Override
        public int getItemCount()
        {
            return grid.size();
        }

        @Override
        public MapDataVHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType)
        {
            LayoutInflater li = LayoutInflater.from(getActivity());
            return new MapDataVHolder(li, parent);
        }

        @Override
        public void onBindViewHolder(MapDataVHolder vh, int ii)
        {
            vh.bind(grid.get(ii));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup ui, Bundle bundle)
    {

        MapData map = MapData.get();

        View view = inflater.inflate(
                R.layout.fragment_map, ui, false);

        // Obtain the RecyclerView UI element
        RecyclerView rv = view.findViewById(R.id.map_RecyclerView);

        // Specify how it should be laid out
        rv.setLayoutManager(new GridLayoutManager(
                getActivity(),
                MapData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false));

        // Have your data ready

        MapElement[][] grid = new MapElement[MapData.WIDTH][MapData.HEIGHT];


        for (int jj = 0; jj < 10; jj++)
        {
            for (int ii = 0; ii < 10; ii++)
            {
                grid[ii][jj] = map.get(ii, jj);
            }
        }

        // Create your adapter (see next slides)
        MapAdapter adapter = new MapAdapter(grid);

        // Hook it up
        rv.setAdapter(adapter);

        return view;
    }
}
