package com.donnfelker.fragmentsdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Demonstrates how to create a fragment which houses other fragments through a view pager.
 */
public class CarouselFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpCarousel();
    }

    private void setUpCarousel() {
        final ViewPager viewPager = (ViewPager) getView().findViewById(R.id.view_pager);

        viewPager.setAdapter(new NestedFragmentPagerAdapter(getChildFragmentManager()));
        viewPager.setCurrentItem(1); // Start with the center.
    }

    public static class NestedFragmentPagerAdapter extends FragmentPagerAdapter {

        public NestedFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return getCarouselItem("Left");
                case 1:
                    return getCarouselItem("Center");
                case 2:
                    return getCarouselItem("Right");
                default:
                    return null;
            }


        }

        private Fragment getCarouselItem(String sectionName) {
            Bundle bundle = new Bundle();
            CarouselItemFragment carouselItem = new CarouselItemFragment();
            bundle.putString(Constants.Extras.KEY_SECTION_NAME, sectionName);
            carouselItem.setArguments(bundle);
            return carouselItem;
        }

        @Override
        public int getCount() {
            return 3;
        }

    }
}