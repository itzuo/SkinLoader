package com.zxj.skin;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.View;

import com.zxj.skin.core.SkinManager;
import com.zxj.skin.core.utils.SkinThemeUtils;
import com.zxj.skin.fragment.MusicFragment;
import com.zxj.skin.fragment.RadioFragment;
import com.zxj.skin.fragment.VideoFragment;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        test();

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        List<Fragment> list = new ArrayList<>();
        list.add(new MusicFragment());
        list.add(new VideoFragment());
        list.add(new RadioFragment());
        List<String> listTitle = new ArrayList<>();
        listTitle.add("音乐");
        listTitle.add("视频");
        listTitle.add("电台");
        MyFragmentPagerAdapter myFragmentPagerAdapter = new MyFragmentPagerAdapter
                (getSupportFragmentManager(), list, listTitle);
        viewPager.setAdapter(myFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //作用：避免退出应用后再次进入程序MyTabLayout换肤失败问题
        SkinManager.getInstance().updateSkin(this);

    }

    /**
     * 进入换肤
     *
     * @param view
     */
    public void skinSelect(View view) {
        startActivity(new Intent(this, SkinActivity.class));
    }

    private void test() {
        XmlResourceParser parser = getResources().getLayout(R.layout.test);
//        XmlPullParser parser = getResources().getXml(R.layout.test);
        AttributeSet attrs = Xml.asAttributeSet(parser);
        int type;

        try {
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }
            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription()
                        + ": No start tag found!");
            }
            parser.next();
            final String name = parser.getName();
            Log.e("zxj","name:"+name);
        }catch (Exception e){
        }

        int attributeCount = attrs.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            //获得属性名  mAttributes
            String attributeName = attrs.getAttributeName(i);
            String attributeValue = attrs.getAttributeValue(i);
            Log.e("zxj","attributeName:"+attributeName+",attributeValue:"+attributeValue);
        }
    }
}
