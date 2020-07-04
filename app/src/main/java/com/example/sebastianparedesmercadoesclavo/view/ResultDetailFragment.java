package com.example.sebastianparedesmercadoesclavo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sebastianparedesmercadoesclavo.R;
import com.example.sebastianparedesmercadoesclavo.controller.ItemController;
import com.example.sebastianparedesmercadoesclavo.databinding.FragmentResultDetailBinding;
import com.example.sebastianparedesmercadoesclavo.model.Attribute;
import com.example.sebastianparedesmercadoesclavo.model.Item;
import com.example.sebastianparedesmercadoesclavo.model.Result;
import com.example.sebastianparedesmercadoesclavo.util.ResultListener;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultDetailFragment extends Fragment {

    public static final String KEY_ID = "id";

    private FragmentResultDetailBinding binding;
    private Double lat;
    private Double lng;
    private ItemFragmentListener itemFragmentListener;
    private Item item;


    public ResultDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        itemFragmentListener = (ItemFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentResultDetailBinding.inflate(inflater,container, false);
        View view = binding.getRoot();

        Bundle bundle = getArguments();
        String id = bundle.getString(KEY_ID);

        ItemController itemController = new ItemController();
        itemController.getItem(new ResultListener<Item>() {
            @Override
            public void onFinish(Item result) {

                item = result;

                binding.fresultdetailtvtitle.setText(result.getTitle());
                binding.fresultdetailtvprice.setText("$" + result.getPrice());
                binding.fresultdetailtvaddress.setText(result.getSellerAddress().getCity().getName() + " - " +
                        result.getSellerAddress().getState().getName());
                if (result.getCondition() != null){
                    binding.fresultdetailtvcondition.setText("Condicion del item: " + result.getCondition());
                }
                if (result.getLocation().getNeighborhood() != null){
                    if (result.getLocation().getNeighborhood().getName() != null){
                        binding.fresultdetailtvneighborhood.setText("Barrio: " + result.getLocation().getNeighborhood().getName());
                    }
                }

                lat = Double.parseDouble(result.getSellerAddress().getLatitude());
                lng = Double.parseDouble(result.getSellerAddress().getLongitude());

                List<String> urls = new ArrayList<>();
                for (int i = 0; i < result.getPictures().size(); i++) {
                    String url = result.getPictures().get(i).getUrl();
                    urls.add(url);
                }

                List<Fragment> fragmentList = generarFragments(urls);

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(),fragmentList);
                binding.viewpager.setAdapter(viewPagerAdapter);

            }
        }, id);

        binding.verubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putDouble("lat", lat);
                bundle1.putDouble("lng", lng);
                itemFragmentListener.onClickUbicacion(bundle1);
            }
        });

        binding.addfavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemFragmentListener.onClickAddFav(item);
            }
        });




        return view;
    }


    private List<Fragment> generarFragments(List<String> urls){
        List<Fragment> fragments = new ArrayList<>();
        for (String url : urls) {
            Fragment fragment = ItemPictureFragment.crearItemPictureFragment(url);
            fragments.add(fragment);
        }
        return fragments;

    }

    public interface ItemFragmentListener{
        void onClickUbicacion(Bundle bundle1);
        void onClickAddFav(Item item);
    }

}