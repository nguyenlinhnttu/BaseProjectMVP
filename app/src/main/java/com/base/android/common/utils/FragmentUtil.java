package com.base.android.common.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.base.android.R;

/**
 * Created by nguyenvanlinh on 6/21/18.
 * Project: BaseProject
 */
public class FragmentUtil {

    private final static FragmentUtil instance = new FragmentUtil();

    /**
     * Get instance fragment util.
     *
     * @return the fragment util
     */
    public static FragmentUtil getInstance() {
        return instance;
    }

    /**
     * Replace fragment.
     * @param fragment      the fragment
     * @param viewContainer the view container
     */
    public void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {

        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_in,R.anim.slide_right_out,R.anim.slide_right_out)
                .replace(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                .commitAllowingStateLoss();
    }

    /**
     * Replace fragment without ad to back stack.
     * @param fragment      the fragment
     * @param viewContainer the view container
     */
    public void replaceFragmentWithoutAdToBackStack(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {
        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        fragmentManager.beginTransaction()
                .replace(viewContainer, fragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Add fragment.
     * @param fragment      the fragment
     * @param viewContainer the view container
     */
    public void addFragment(FragmentManager fragmentManager, Fragment fragment, int viewContainer) {

        String FRAGMENT_TAG = fragment.getClass().getSimpleName();

        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_in,R.anim.slide_right_out,R.anim.slide_right_out)
                .add(viewContainer, fragment, FRAGMENT_TAG)
                .addToBackStack(FRAGMENT_TAG)
                .commit();
    }

    public void addFragmentWithoutAddToBackStack(FragmentTransaction transaction, Fragment fragment, int viewContainer) {

        String FRAGMENT_TAG = fragment.getClass().getSimpleName();
        transaction.add(viewContainer, fragment, FRAGMENT_TAG)
                .setCustomAnimations(R.anim.slide_right_in, R.anim.stay)
                .commit();
    }


    /**
     * Replace child fragment.
     *
     * @param parentFragment the parent fragment
     * @param childFragment  the child fragment
     * @param viewContainer  the view container
     */
    public void replaceChildFragment(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(FRAGMENT_TAG)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void replaceChildFragmentBonus(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment.getChildFragmentManager().beginTransaction()
                .addToBackStack(FRAGMENT_TAG)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void addChildFragment(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(FRAGMENT_TAG)
                .add(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    /**
     * Replace child fragment without add to back stack.
     *
     * @param parentFragment the parent fragment
     * @param childFragment  the child fragment
     * @param viewContainer  the view container
     */
    public void replaceChildFragmentWithoutAddToBackStack(Fragment parentFragment, Fragment childFragment, int viewContainer) {

        String FRAGMENT_TAG = childFragment.getClass().getSimpleName();

        parentFragment.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(viewContainer, childFragment, FRAGMENT_TAG)
                .commit();
    }

    public void onBackPressJumpToFragmentWithName(FragmentManager fragmentManager, String fragmentName) {
        while (fragmentManager.getBackStackEntryCount() > 1) {
            if (fragmentManager.getBackStackEntryAt(
                    fragmentManager.getBackStackEntryCount() - 1).getName().equals(fragmentName)) {
                return;
            }
            try {
                fragmentManager.popBackStackImmediate();
            } catch (IllegalStateException ex) {
                ex.getMessage();
            }
        }
    }

    public Fragment getFragmentBackStack(FragmentManager fragmentManager, int index) {
        String FRAGMENT_TAG = fragmentManager.getBackStackEntryAt(index).getName();
        return fragmentManager.findFragmentByTag(FRAGMENT_TAG);
    }

    public void replaceFragmentAfterResetBackstack(FragmentManager fragmentManager, Fragment fragment, int viewContainer){
        while (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStackImmediate();
        }
        replaceFragment(fragmentManager, fragment, viewContainer);
    }

    public void resetBackStack(FragmentManager fragmentManager){
        while (fragmentManager.getBackStackEntryCount() > 0){
            fragmentManager.popBackStackImmediate();
        }
    }

    public void backStackToMain(FragmentManager fragmentManager){
        for(int i = 0; i < fragmentManager.getBackStackEntryCount() - 1; ++i) {
            fragmentManager.popBackStack();
        }
    }
}

