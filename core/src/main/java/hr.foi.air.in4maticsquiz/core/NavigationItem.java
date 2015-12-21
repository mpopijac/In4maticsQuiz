package hr.foi.air.in4maticsquiz.core;

/**
 * Created by Anabel Li on 14.11.2015..
 */
import android.app.Fragment;

public interface NavigationItem {
    public String getItemName();
    public int getPosition();
    public void setPosition(int position);
    public Fragment getFragment();
}