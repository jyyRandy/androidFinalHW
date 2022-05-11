package com.bytedance.sjtu.news

import android.graphics.Color
import android.graphics.Color.parseColor
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.sjtu.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class NewsFragment : Fragment() {


    private val tabLayout: TabLayout by lazy { requireView().findViewById(R.id.tabLayout) }  //顶部导航栏
    private val viewPager2News: ViewPager2 by lazy { requireView().findViewById(R.id.viewPager2_news) }
    private val news1Fragment = News1Fragment()
    private val news2Fragment = News2Fragment()
    private val news3Fragment = News3Fragment()
    private val news4Fragment = News1Fragment()
    private val news5Fragment = News1Fragment()
    private val news6Fragment = News1Fragment()
    private val handler = Handler(Looper.getMainLooper())
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = mutableListOf(
            news1Fragment,
            news2Fragment,
            news3Fragment,
            news4Fragment,
            news5Fragment,
            news6Fragment
        )
        viewPager2News.offscreenPageLimit = 5  //设置viewPager2的缓存页面数量
        viewPager2News.adapter = NewsFragmentVP2Adapter(activity, fragments)  //绑定数据
        val runnable = Runnable {
            run {
                viewPager2News.currentItem = 1  //设置默认页面为第2页
            }
        }
        handler.post(runnable)

        TabLayoutMediator(tabLayout, viewPager2News) { tab: TabLayout.Tab, position: Int ->  //绑定TabLayout与viewPager2Video
            tab.customView = layoutInflater.inflate(R.layout.layout_news_tab, null)
            val textView = tab.view.findViewById<TextView>(R.id.tv_video_tab)
            when (position) {
                0 -> { textView.text = "关注" }
                1 -> { textView.text = "推荐" }
                2 -> { textView.text = "热榜" }
                3 -> { textView.text = "关注" }
                4 -> { textView.text = "推荐" }
                5 -> { textView.text = "热榜" }
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val textView = tab.customView!!.findViewById<TextView>(R.id.tv_video_tab)
                textView.setTextColor(parseColor("#e44845"))
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val textView = tab.customView!!.findViewById<TextView>(R.id.tv_video_tab)
                textView.setTextColor(Color.BLACK)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }

}
